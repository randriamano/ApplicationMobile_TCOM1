package com.example.e_comget.Datoum.model.rules

object Validator {
    fun validateFirstName(fName: String) : validationResutl{
        return validationResutl(
            (!fName.isNullOrEmpty() && fName.length >= 6)
        )
    }
    fun validateLastName(lName: String) : validationResutl{
        return validationResutl((!lName.isNullOrEmpty() && lName.length >= 2))

    }
    fun validateIdNum(IdNum: String) : validationResutl{
        return validationResutl((!IdNum.isNullOrEmpty()))

    }
    fun validatPassword(Password: String) : validationResutl{
        return validationResutl((!Password.isNullOrEmpty()  && Password.length >= 4))

    }
}

data class validationResutl(
    val status : Boolean = false
)