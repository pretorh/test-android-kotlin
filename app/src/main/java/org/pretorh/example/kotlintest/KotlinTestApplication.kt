package org.pretorh.example.kotlintest

import android.app.Application
import io.realm.Realm
import org.pretorh.example.kotlintest.di.DaggerInjector
import org.pretorh.example.kotlintest.di.DefaultModule
import org.pretorh.example.kotlintest.di.Injector

class KotlinTestApplication : Application() {
    lateinit var injector: Injector

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        injector = DaggerInjector.builder()
                .defaultModule(DefaultModule(this))
                .build()
    }
}
