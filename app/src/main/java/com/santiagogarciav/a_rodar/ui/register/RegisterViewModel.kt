package com.santiagogarciav.a_rodar.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiagogarciav.a_rodar.data.ResourceRemote
import com.santiagogarciav.a_rodar.model.User
import com.santiagogarciav.a_rodar.data.UserRepository
import emailValidator
import kotlinx.coroutines.launch


class RegisterViewModel:ViewModel() {


    private val userRepository = UserRepository()

    private val _errorMsg : MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String?> = _errorMsg

    private val _registerSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val registerSuccess: LiveData<Boolean> = _registerSuccess

    fun validateFields(
        email: String,
        password: String,
        repeatPasswor: String,
        name: String,
        number: String
    ){
//  fun validateFields(email: String, password: String, repeatPasswor: String): Boolean{
        if(email.isEmpty() || password.isEmpty() || repeatPasswor.isEmpty() || name.isEmpty() || number.isEmpty()){
            _errorMsg.value = "Debe digitar todos los campos"
//            return false
        } else{
            if(password != repeatPasswor){
                _errorMsg.value = "Las contraseñas deben ser iguales"

//                return false
            }else{
                if(password.length < 6){
                    _errorMsg.value = "La contraseña debe tener minimo 6 digitos"
//                    return false
                }else {
                    if (!number.matches(Regex("^[0-9]+$"))) {
                        _errorMsg.value = "El numero esta mal escrito"
                    } else {
                        if (!emailValidator(email)) {
                            _errorMsg.value = "El correo electronico esta mal escrito"
//                            return false
                        } else {
                            viewModelScope.launch {
                                val result = userRepository.registerUser(email, password)
                                result.let { resourceRemote ->
                                    when (resourceRemote) {
                                        is ResourceRemote.Success -> {
                                            var uid = result.data
                                            uid?.let { Log.d("uid Uder", it) }
                                            val user = User(uid, email, name, number)
                                            createUser(user)

                                        }

                                        is ResourceRemote.Error -> {
                                            var msg = result.message
                                            when (msg) {
                                                "The email address is already in use by another account." -> msg =
                                                    "Ya existe una cuenta con ese correo electronico"

                                                "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg =
                                                    "Revise su conexion a internet"
                                            }
                                            _errorMsg.postValue(msg)
                                        }

                                        else -> {
                                            //don´t use
                                        }
                                    }
                                }
                            }
                        //                        return true
                        }
                    }
                }
            }
        }
    }

    private fun createUser(user: User) {
        viewModelScope.launch {
            val result = userRepository.createUser(user)
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        _registerSuccess.postValue(true)
                        _errorMsg.postValue("Usuario creado exitosamente")
                    }
                    is ResourceRemote.Error -> {
                        var msg = result.message
                        _errorMsg.postValue(msg)
                    }
                    else -> {
                        //don´t use
                    }
                }
            }
        }
    }

}