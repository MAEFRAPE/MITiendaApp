package com.example.myapplication.Data.Repositorios

import com.example.myapplication.Data.Models.Comment
import com.example.myapplication.Data.databases.dao.CommentDao
import com.example.myapplication.Utils.Constants
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class CommentRepository(private val dataSource: CommentDao ,private val dataSourceFirebase: FirebaseFirestore) {
    private val db: CollectionReference = dataSourceFirebase.collection(Constants.COMMENT_COLECTION)
    suspend fun loadCommentInfo():List<Comment>{
        /*return withContext(Dispatchers.IO){
            dataSource.getAllComments()
        }*/
        val snapshot = db.get().await()
        return snapshot.toObjects(Comment::class.java)
    }
    suspend fun insertComment(comments:List<Comment>){

         withContext(Dispatchers.IO){
             val temp = dataSource.getAllComments()
             if (temp.isEmpty()){
                 dataSource.insertComment(comments)

             }

        }
    }
}