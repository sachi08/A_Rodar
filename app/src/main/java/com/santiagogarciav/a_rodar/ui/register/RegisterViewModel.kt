package com.santiagogarciav.a_rodar.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//import com.santiagogarciav.a_rodar.data.UserRepository
import emailValidator


class RegisterViewModel:ViewModel() {


//    private val userRepository = UserRepository()

    private val _errorMsg : MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMsg
    fun validateFields(email: String, password: String, repeatPasswor: String): Boolean {
        if(email.isEmpty() || password.isEmpty() || repeatPasswor.isEmpty()){
            _errorMsg.value = "Debe digitar todos los campos"
            return false
        } else{
            if(password != repeatPasswor){
                _errorMsg.value = "Las contraseñas deben ser iguales"
                return false
            }else{
                if(password.length < 6){
                    _errorMsg.value = "La contraseña debe tener minimo 6 digitos"
                    return false
                }else{
                    if(!emailValidator(email)){
                        _errorMsg.value = "El correo electronico esta mal escrito"
                        return false
                    }else{
//                        viewModelScope.launch {
//                            userRepository.registerUser(email, password)
//                    }
                        return true
                    }
                }
            }
        }
    }
}