package com.plcoding.composepaging3caching.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.google.api.Page
import com.plcoding.composepaging3caching.data.local.MovieDatabase
import com.plcoding.composepaging3caching.data.local.MovieEntity
import com.plcoding.composepaging3caching.data.mappers.mapToEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val universityDb: MovieDatabase,
    private val universityApi: MovieApi
): RemoteMediator<Int, MovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    Log.d("BeerMediator", "lastItem = $lastItem")
                    if(lastItem == null) {
                        1
                    } else {
                        val currentPage = state.pages.find { it.data.contains(lastItem) }?.prevKey ?: 1
//                        Log.d("BeerMediator", "current page = ${currentPage+1}")
                        currentPage + 1
                    }
                }
            }
            val beers = universityApi.getMovies(
                apiKey = "e5ea3092880f4f3c25fbc537e9b37dc1",
                pageNumber = loadKey,
            )
//            Log.d("BeerMediator", "list = ${beers.results}")
//            if(beers.results != null){
//                for(beer in beers.results){
//                    Log.d("BeerMediator", "list = $beer")
//                }
//            }
            universityDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    universityDb.dao.clearAll()
                }
                val beerEntities = beers.results!!.mapToEntity()
                universityDb.dao.upsertAll(beerEntities)
            }
            //Log.d("Page", "Page no : ${beers.page}")
            MediatorResult.Success(
                endOfPaginationReached = beers.results!!.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}