package com.example.e_comget.Datoum.model.registration

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.e_comget.Datoum.model.item.UserItemToSend
import com.example.e_comget.MainViewModel

class RegistrationViewModel : ViewModel() {
    private val TAG = RegistrationViewModel::class.simpleName
    var registrationUIState = mutableStateOf(RegistrationUIState())

    fun onEvent(event: UIEvent) {
        when (event) {
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
        }
    }

    fun signUp(mainViewModel: MainViewModel) {
        var userItem: UserItemToSend = UserItemToSend(
            studentName = registrationUIState.value.studentName,
            studentFirstname = registrationUIState.value.studentFirstname,
            studentCardNum = registrationUIState.value.studentCardNum,
            studentPassword = registrationUIState.value.studentPassword
        )

        mainViewModel.register(userItem)
    }
}
