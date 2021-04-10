package com.lacuc.pets.data.animal

data class Medical(
    val date: Long,
    val title: String,
    val content: String,
    val hospital: String
) {
    val dateString: String
        get() = date.toString()
}

