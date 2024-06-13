package com.example.mob_sae401.data.implementation

import com.example.mob_sae401.data.models.User

object UserList {
    val list: List<User> = listOf(
        User("toto@mail.fr", "password"),
        User("john@mail.fr", "123456")
    )
}