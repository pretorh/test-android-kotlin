package org.pretorh.example.kotlintest

import android.app.Application
import org.pretorh.example.kotlintest.di.DaggerInjector
import org.pretorh.example.kotlintest.di.DefaultModule
import org.pretorh.example.kotlintest.di.Injector

class KotlinTestApplication : Application() {
    private var injector: Injector? = null

    override fun onCreate() {
        super.onCreate()
        injector = DaggerInjector.builder()
                .defaultModule(DefaultModule(this))
                .build()
    }
}
