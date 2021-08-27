package com.example.myapplication.Data.Repositorios


import android.graphics.Bitmap
import com.example.myapplication.Data.Models.Storeinfo
import com.example.myapplication.Data.Models.User
import com.example.myapplication.Utils.Constants
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.lang.Error


class UserRepository(private val dataSourceFirebase: FirebaseAuth,private val dataSourceStorage:StorageReference,
                     private val dataSourceBD: FirebaseFirestore) {

    private val db: CollectionReference = dataSourceBD.collection(Constants.USER_COLECTION)

    suspend fun loadUser(idUser:String): User? {

        val snapshot = db.document(idUser).get().await()
        return snapshot.toObject(User::class.java)
    }

    suspend fun loginIn():FirebaseUser?{
        return dataSourceFirebase.currentUser

    }



   suspend fun registro(nombres:String, apellidos:String,email:String, tel:String, pass:String):FirebaseUser?{
        try {
            dataSourceFirebase.createUserWithEmailAndPassword(email,pass).await()
            var user= dataSourceFirebase.currentUser
            val profileUpdate = userProfileChangeRequest {
                displayName= nombres +" "+ apellidos

            }


            user!!.updateProfile(profileUpdate).await()
            val userDB = User(user.uid.toString(),nombres,apellidos,email,tel,"https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png")
            db.document(user.uid).set(userDB)
            return user
        }catch (e: FirebaseAuthUserCollisionException){
            throw Error("Correo en Uso")
        }

    }

   suspend fun login(email: String,pass: String):FirebaseUser?{
       try {
           dataSourceFirebase.signInWithEmailAndPassword(email,pass).await()
           return dataSourceFirebase.currentUser

       }catch (e: FirebaseAuthInvalidCredentialsException){
           throw Error("Credenciales Invalidas")
       }catch (e:FirebaseAuthInvalidUserException){
           throw Error("Credenciales Invalidas")

       }

   }

    suspend fun logout():FirebaseUser?{
        dataSourceFirebase.signOut()
        return null

    }

    suspend fun uploadImage(bitmap: Bitmap):FirebaseUser?{
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val data =baos.toByteArray()
        val user= dataSourceFirebase.currentUser
        val profileRef = dataSourceStorage.child("${user!!.uid}.jpg")
        profileRef.putBytes(data).await()
        val uri = profileRef.downloadUrl.await()
        val profileUpdate = userProfileChangeRequest {
            photoUri=uri

        }
        user!!.updateProfile(profileUpdate).await()
        val url = hashMapOf("imag" to uri.toString())
        db.document(user.uid).set(url, SetOptions.merge())
        return user
    }
}