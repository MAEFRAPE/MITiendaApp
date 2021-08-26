package com.example.myapplication.Data.Repositorios

import com.example.myapplication.Data.Models.Comment
import com.example.myapplication.Data.Models.User
import com.example.myapplication.Data.databases.dao.CommentDao
import com.example.myapplication.Utils.Constants
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Error
import java.text.SimpleDateFormat
import java.util.*


class CommentRepository(/*private val dataSource: CommentDao*/ private val dataSourceFirebase: FirebaseFirestore) {
    private val db: CollectionReference = dataSourceFirebase.collection(Constants.COMMENT_COLECTION)

    suspend fun loadCommentInfo():List<Comment>{
        /*return withContext(Dispatchers.IO){
            dataSource.getAllComments()
        }*/
        /*= db.get().await()*/
        val snapshot = db.get().await()
        return snapshot.toObjects(Comment::class.java)
    }
    /*suspend fun insertComment(comments:List<Comment>){

         withContext(Dispatchers.IO){
             val temp = dataSource.getAllComments()
             if (temp.isEmpty()){
                 dataSource.insertComment(comments)

             }

        }
    }*/

    suspend fun registroComment(user:FirebaseUser,comment:String):Comment{
            val commentDB = Comment(user.uid.toString(),user.photoUrl.toString(),user.displayName.toString(),comment)
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
        db.document(date).set(commentDB)

        return commentDB
    }
}