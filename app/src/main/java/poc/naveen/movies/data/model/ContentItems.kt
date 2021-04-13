package poc.naveen.movies.data.model


import com.google.gson.annotations.SerializedName

data class ContentItems(
    @SerializedName("content")
    val content: ArrayList<Content> = arrayListOf()
)