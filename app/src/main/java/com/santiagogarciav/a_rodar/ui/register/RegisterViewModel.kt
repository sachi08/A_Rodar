package com.santiagogarciav.a_rodar.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import emailValidator

class RegisterViewModel:ViewModel() {

    private val _errorMsg : MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMsg
    fun validateFields(email: String, password: String, repeatPasswor: String) {
        if(email.isEmpty() || password.isEmpty() || repeatPasswor.isEmpty()){
            _errorMsg.value = "Debe digitar todos los campos"
        } else{
            if(password != repeatPasswor){
                _errorMsg.value = "Las contraseñas deben ser iguales"
            }else{
                if(password.length < 6){
                    _errorMsg.value = "La contraseña debe tener minimo 6 digitos"
                }else{
                    if(!emailValidator(email)){
                        _errorMsg.value = "El correo electronico esta mal escrito"
                    }
                }
            }
        }
    }

}