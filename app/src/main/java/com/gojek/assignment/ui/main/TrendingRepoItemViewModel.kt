package com.gojek.assignment.ui.main

import androidx.lifecycle.MutableLiveData
import com.gojek.assignment.base.BaseViewModel
import com.gojek.assignment.data.Repositories

class TrendingRepoItemViewModel : BaseViewModel() {
    private val author = MutableLiveData<String>()
    private val name = MutableLiveData<String>()
    private val avatar = MutableLiveData<String>()
    private val description = MutableLiveData<String>()
    private val languageColor = MutableLiveData<String>()
    private val language = MutableLiveData<String>()
    private val stars = MutableLiveData<Integer>()
    private val forks = MutableLiveData<Integer>()

    fun bind(repo: Repositories){
        author.value = repo.author
        name.value = repo.name
        avatar.value = repo.avatar
        description.value = repo.description
        languageColor.value = repo.languageColor
        language.value = repo.language
        stars.value = repo.stars
        forks.value = repo.forks
    }

    fun getRepoAuthor():MutableLiveData<String>{
        return author
    }

    fun getRepoName():MutableLiveData<String>{
        return name
    }

    fun getRepoAvatar():MutableLiveData<String>{
        return avatar
    }

    fun getRepoDescription():MutableLiveData<String>{
        return description
    }

    fun getRepoLanguageColor():MutableLiveData<String>{
        return languageColor
    }

    fun getRepoLanguage():MutableLiveData<String>{
        return language
    }

    fun getRepoStars():MutableLiveData<Integer>{
        return stars
    }

    fun getRepoForks():MutableLiveData<Integer>{
        return forks
    }

}