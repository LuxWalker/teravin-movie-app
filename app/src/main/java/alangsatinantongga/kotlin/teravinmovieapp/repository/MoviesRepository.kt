package alangsatinantongga.kotlin.teravinmovieapp.repository

import alangsatinantongga.kotlin.teravinmovieapp.api.ApiService
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun moviesListCall(page: Int) = apiService.getMovies(page)
}