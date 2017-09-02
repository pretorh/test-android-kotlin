package org.pretorh.example.kotlintest.service

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Post(
        @PrimaryKey var id: Int = 0,
        var title: String = ""
) : RealmObject()
