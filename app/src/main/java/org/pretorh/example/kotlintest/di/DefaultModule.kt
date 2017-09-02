package org.pretorh.example.kotlintest.di

import android.content.Context
import dagger.Module
import dagger.Provides
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
}
