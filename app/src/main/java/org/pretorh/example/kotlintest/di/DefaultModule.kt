package org.pretorh.example.kotlintest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import org.pretorh.example.kotlintest.PostRepository
import org.pretorh.example.kotlintest.R
import org.pretorh.example.kotlintest.service.JsonPlaceholderService
import javax.inject.Named

@Module
class DefaultModule(private val context: Context) {
    @Provides
    fun provideJsonPlaceholderService(@Named("baseUrl") baseUrl: String) : JsonPlaceholderService {
        return JsonPlaceholderService(baseUrl)
    }

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl() : String = context.getString(R.string.baseUrl)

    @Provides
    fun provideRealm(realmConfiguration: RealmConfiguration) : Realm {
        return Realm.getInstance(realmConfiguration)
    }

    @Provides
    fun provideRealmConfiguration() : RealmConfiguration {
        return RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
    }

    @Provides
    fun providePostRepository(realm: Realm) : PostRepository {
        return PostRepository(realm)
    }
}
