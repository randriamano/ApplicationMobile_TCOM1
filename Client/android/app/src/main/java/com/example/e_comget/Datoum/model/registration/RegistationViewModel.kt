package com.example.e_comget.Datoum.model.registration

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.e_comget.Datoum.model.rules.Validator

class RegistationViewModel : ViewModel() {

    var studentUIState : MutableState<RegistrationUIState> = mutableStateOf(RegistrationUIState())

    private val TAG  = RegistationViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegistrationUIState())

    fun onEvent(event : UIEvent){
        when(event){
            is UIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    studentName = event.studentName
                )
            }

            is UIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    studentFirstname = event.studentFirstname
                )
            }

            is UIEvent.IdNumChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    studentCardNum = event.studentCardNum
                )
            }

            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    studentPassword = event.studentPassword
                )
            }
            is UIEvent.RegisterButtonClicked -> {
                signUp()
            }

        }
    }

    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        studentUIState.value = registrationUIState.value

        Log.d(TAG, "Value of studentUIState")
        Log.d(TAG, "Inside_validateDataWithRules_veuillez remplir tous les champs")

        validateDataWithRules()
    }

    private fun validateDataWithRules() {
        var fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.studentName
        )
        var lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.studentFirstname
        )
        var idNumResult = Validator.validateIdNum(
            IdNum = registrationUIState.value.studentCardNum
        )
        var password = Validator.validatPassword(
            Password = registrationUIState.value.studentPassword
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult = $fNameResult")
        Log.d(TAG, "lNameResult = $lNameResult")
        Log.d(TAG, "idNumResult = $idNumResult")
        Log.d(TAG, "password = $password")

       // if (fNameResult && lNameResult && idNumResult && password){
            //alefa amzay ny data
         //   Log.d("", studentUIState.value.toString())
       // }
        //else{
       //     Log.d(TAG, "Inside_validateDataWithRules_veuillez remplir tous les champs")
      //  }
    }

    private fun printState(){
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }
}