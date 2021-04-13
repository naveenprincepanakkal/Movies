package poc.naveen.movies.di

import dagger.Module
import dagger.Provides
import poc.naveen.movies.data.repository.MainRepository
import javax.inject.Singleton

/**
 * Class for Dagger Modules
 *
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun providesRepository(): MainRepository {
        return MainRepository()
    }

}