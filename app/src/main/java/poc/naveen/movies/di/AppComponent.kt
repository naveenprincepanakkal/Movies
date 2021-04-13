package poc.naveen.movies.di

import dagger.Component
import poc.naveen.movies.data.repository.MainRepository
import poc.naveen.movies.ui.activity.MainActivity
import poc.naveen.movies.ui.fragment.MainFragment
import javax.inject.Singleton

/**
 * Class for Dagger Component
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(repository: MainRepository)
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
}