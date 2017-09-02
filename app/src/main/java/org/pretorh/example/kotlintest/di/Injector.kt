package org.pretorh.example.kotlintest.di

import dagger.Component
import org.pretorh.example.kotlintest.CommentsActivity
import org.pretorh.example.kotlintest.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(DefaultModule::class))
interface Injector {
    fun inject(activity: MainActivity)
    fun inject(activity: CommentsActivity)
}
