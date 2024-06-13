package com.example.mob_sae401.data.models

import androidx.annotation.DrawableRes

data class Work(
    val id: Int,
    val name: String,
    val description: String,
    val director: String,
    val releaseDate: String,
    val booked: Boolean,
    @DrawableRes val image: Int,
    val reviews: List<Review>,
    val type: WorkType
)

enum class WorkType {
    BOOK {
        override fun toString(): String {
            return "book"
        }
    },
    MOVIE {
        override fun toString(): String {
            return "movie"
        }
    }
}
