package com.example.e_comget.Datoum.model.registration

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.e_comget.Datoum.model.item.UserLoginItem
import com.example.e_comget.MainViewModel

class LoginViewModel : ViewModel() {
    private val TAG = RegistrationViewModel::class.simpleName
    var registrationUIState = mutableStateOf(RegistrationUIStateLogin())

    fun onEvent(event: UIEvent) {
        when (event) {
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

            is UIEvent.FirstNameChanged -> TODO()
            is UIEvent.LastNameChanged -> TODO()
        }
    }

    fun login(mainViewModel: MainViewModel) {
        var userLoginItem: UserLoginItem = UserLoginItem(
            studentCardNum = registrationUIState.value.studentCardNum,
            studentPassword = registrationUIState.value.studentPassword
        )

        mainViewModel.login(userLoginItem)
    }
}