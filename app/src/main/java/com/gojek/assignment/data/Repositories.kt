package com.gojek.assignment.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.annotations.Nullable


@Entity
data class Repositories(
    val author: String,
    @field:PrimaryKey
    val name: String,
    val avatar: String,
    @field:Nullable
    val language: String?,
    val languageColor:String?,
    val stars:Integer,
    val forks:Integer
)
