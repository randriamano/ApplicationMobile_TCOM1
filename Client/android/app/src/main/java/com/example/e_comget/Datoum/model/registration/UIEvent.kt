package com.example.e_comget.Datoum.model.registration

sealed class UIEvent {
    data class FirstNameChanged(var studentName: String) : UIEvent()
    data class LastNameChanged(var studentFirstname: String) : UIEvent()
    data class IdNumChanged(var studentCardNum: String) : UIEvent()
    data class PasswordChanged(var studentPassword: String) : UIEvent()
}