package poc.naveen.movies.data.model


import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("page")
    val page: Page = Page()
)