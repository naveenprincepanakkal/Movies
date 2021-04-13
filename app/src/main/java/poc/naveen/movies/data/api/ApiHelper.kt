package poc.naveen.movies.data.api

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import poc.naveen.movies.data.model.Movies
import poc.naveen.movies.utils.Helper

class ApiHelper {

    private val moviesDataUrl: String = "CONTENTLISTINGPAGE-PAGE1.json"

    suspend fun fetchMovieData(pageNum: Int): Movies {
        return withContext(Dispatchers.IO) {
            val jsonFileString = Helper.getJsonDataFromAsset("CONTENTLISTINGPAGE-PAGE$pageNum.json")
            Gson().fromJson(jsonFileString, Movies::class.java)
        }
    }
}