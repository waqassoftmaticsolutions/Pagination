package com.plcoding.composepaging3caching.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.plcoding.composepaging3caching.data.local.MovieDatabase
import com.plcoding.composepaging3caching.data.local.MovieEntity
import com.plcoding.composepaging3caching.data.remote.MovieRemoteMediator
import com.plcoding.composepaging3caching.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "moviestable.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(MovieApi.API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBeerPager(universityDb: MovieDatabase, universityApi: MovieApi): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MovieRemoteMediator(
                universityDb = universityDb,
                universityApi = universityApi
            ),
            pagingSourceFactory = {
                universityDb.dao.pagingSource()
            }
        )
    }
}

