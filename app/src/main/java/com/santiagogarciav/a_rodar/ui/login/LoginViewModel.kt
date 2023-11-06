package com.santiagogarciav.a_rodar.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiagogarciav.a_rodar.data.ResourceRemote
import com.santiagogarciav.a_rodar.data.UserRepository
import emailValidator
import kotlinx.coroutines.launch


class LoginViewModel:ViewModel() {

    private val userRepository = UserRepository()

    private val _errorMsg : MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMsg

    private val _registerSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val registerSuccess: LiveData<Boolean> = _registerSuccess

    fun validateFields(email: String, password: String){
        if(email.isEmpty() || password.isEmpty()){
            _errorMsg.value = "Debe digitar todos los campos"
//            return false
        } else{
            if(password.length < 6){
                _errorMsg.value = "La contraseña debe tener minimo 6 digitos"
//                return false
            }else{
                if(!emailValidator(email)){
                    _errorMsg.value = "El correo electronico esta mal escrito"
//                    return false
                }else {
                    viewModelScope.launch {
                        val result = userRepository.loginUser(email, password)
                        result.let { resourceRemote ->
                            when (resourceRemote){
                                is ResourceRemote.Success -> {
                                    _registerSuccess.postValue(true)
                                    _errorMsg.postValue("Bienvenido")

                                }
                                is ResourceRemote.Error -> {
                                    var msg = result.message
                                    when(msg){
                                        "The email address is already in use by another account." -> msg = "Ya existe una cuenta con ese correo electronico"
                                        "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg = "Revise su conexion a internet"
                                        "An internal error has occurred. [ INVALID_LOGIN_CREDENTIALS ]" -> msg = "Correo electronico o contraseña incorrectos"
                                    }
                                    _errorMsg.postValue(msg)
                                }
                                else -> {
                                    //don´t use
                                }
                            }
                        }
                    }
//                    return true
                }
            }
        }
    }
}

