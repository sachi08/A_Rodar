package com.santiagogarciav.a_rodar.data

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.santiagogarciav.a_rodar.data.ResourceRemote
import com.santiagogarciav.a_rodar.model.User
import kotlinx.coroutines.tasks.await

class UserRepository  {

    private var auth: FirebaseAuth = Firebase.auth

    private val db = Firebase.firestore

    suspend fun loginUser(email: String, password: String): ResourceRemote<String?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            ResourceRemote.Success(data = result.user?.uid)
        } catch (e: FirebaseAuthException) {
            Log.e("Register", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            Log.e("RegisterNetwork", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseException) {
            Log.e("LoginException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
    suspend fun registerUser(email: String, password: String) : ResourceRemote<String?> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
//            result.user?.sendEmailVerification()
//            auth.sendPasswordResetEmail(email)
            ResourceRemote.Success(data = result.user?.uid)
        }catch (e: FirebaseAuthException){
            Log.e("RegisterMessageError", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }catch (e: FirebaseNetworkException){
            Log.e("RegisterNetworkK", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }


    suspend fun createUser(user: User): ResourceRemote<String?>  {
        return try {
            user.uid?.let { db.collection("user").document(it).set(user).await() }
            ResourceRemote.Success(data = user.uid)
        } catch (e: FirebaseFirestoreException) {
            Log.e("FirebaseFirestoreException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            Log.e("LoginNetworkK", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseException) {
            Log.e("LoginException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }


}