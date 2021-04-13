package poc.naveen.movies

import android.app.Application
import android.content.Context
import poc.naveen.movies.di.AppComponent
import poc.naveen.movies.di.DaggerAppComponent

class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var appComponent: AppComponent

        private lateinit var instance: MainApplication
        fun applicationContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }

}