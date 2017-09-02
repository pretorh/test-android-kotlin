package org.pretorh.example.kotlintest.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(DefaultModule::class))
interface Injector {
}
