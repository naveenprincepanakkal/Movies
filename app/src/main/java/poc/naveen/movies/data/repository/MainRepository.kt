package poc.naveen.movies.data.repository

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import poc.naveen.movies.MainApplication
import poc.naveen.movies.data.api.ApiHelper
import poc.naveen.movies.data.model.Movies
import poc.naveen.movies.utils.Helper

class MainRepository() {

    init {
        MainApplication.appComponent.inject(this)
    }

    suspend fun getMovies(pageNum: Int) = ApiHelper().fetchMovieData(pageNum)

}