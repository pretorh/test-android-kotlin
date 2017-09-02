package org.pretorh.example.kotlintest

import io.realm.Realm
import org.pretorh.example.kotlintest.service.Post

class PostRepository(private val realm: Realm) {
    fun load() : List<Post> {
        val realmResults = realm.where(Post::class.java).findAll()
        return realm.copyFromRealm(realmResults)
    }

    fun persist(posts: List<Post>) {
        realm.beginTransaction()
        try {
            realm.copyToRealmOrUpdate(posts)
            realm.commitTransaction()
        } catch (ignored: Throwable) {
            realm.cancelTransaction()
        }
    }
}
