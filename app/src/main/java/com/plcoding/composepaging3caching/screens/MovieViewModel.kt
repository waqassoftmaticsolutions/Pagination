package com.plcoding.composepaging3caching.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.plcoding.composepaging3caching.data.local.MovieEntity
import com.plcoding.composepaging3caching.data.mappers.toMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    pager: Pager<Int, MovieEntity>
): ViewModel() {
    val beerPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toMovie() }
        }
        .cachedIn(viewModelScope)
}