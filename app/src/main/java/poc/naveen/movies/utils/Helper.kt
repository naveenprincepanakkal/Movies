package poc.naveen.movies.utils

import android.content.Context
import poc.naveen.movies.MainApplication

class Helper {

    companion object {

        fun getJsonDataFromAsset(fileName: String): String? {
            val jsonString: String
            try {
                jsonString = MainApplication.applicationContext().assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (exception: Exception) {
                exception.printStackTrace()
                return null
            }
            return jsonString
        }

        fun getDrawableFromString(context: Context, fileName: String): Int {
            val resId: Int = context.resources.getIdentifier(fileName, "drawable", context.packageName)
            return if (resId == 0) -1 else resId
        }


    }
}