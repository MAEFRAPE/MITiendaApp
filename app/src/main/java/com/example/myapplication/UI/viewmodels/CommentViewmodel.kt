package com.example.myapplication.UI.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.Models.Comment
import com.example.myapplication.Data.Repositorios.CommentRepository
import kotlinx.coroutines.launch

class CommentViewmodel(private val repo:CommentRepository):ViewModel() {
    private var _comments:MutableLiveData<List<Comment>> = MutableLiveData()
    val comment: LiveData<List<Comment>> get() = _comments

    fun loadCommentInfo(){

        viewModelScope.launch {
        val result = repo.loadCommentInfo()
        _comments.postValue(result)
        }
    }
}