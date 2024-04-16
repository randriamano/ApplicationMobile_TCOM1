package com.example.e_comget.Datoum.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

val Context.myPreferencesDataStore: DataStore<Preferences> by preferencesDataStore("settings")

data class ConnexionStatus(
    val isLoggedIn: Boolean,
    val userCardNum: String,
    val userName: String,
    val userFirstName: String
)

@Singleton
class MYPreferencesDataStore @Inject constructor(
    @ApplicationContext context: Context
) {
    private val myPreferencesDataStore: DataStore<Preferences> = context.myPreferencesDataStore

    private object PreferencesKeys {
        val IS_LOGGED_IN_KEYS: Preferences.Key<Boolean> = booleanPreferencesKey("is_logged_in")
        val USER_CARD_NAME: Preferences.Key<String> = stringPreferencesKey("card_name")
        val USER_NAME: Preferences.Key<String> = stringPreferencesKey("user_name")
        val USER_FIRSTNAME: Preferences.Key<String> = stringPreferencesKey("user_firstname")
    }

    val taskStatusFlow = myPreferencesDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val isLoggedIn = preferences[PreferencesKeys.IS_LOGGED_IN_KEYS] ?: false
            val userCardNum = preferences[PreferencesKeys.USER_CARD_NAME] ?: ""
            val userName = preferences[PreferencesKeys.USER_NAME] ?: ""
            val userFirstName = preferences[PreferencesKeys.USER_FIRSTNAME] ?: ""

            ConnexionStatus(
                isLoggedIn = isLoggedIn,
                userCardNum = userCardNum,
                userName = userName,
                userFirstName = userFirstName
            )
        }

    suspend fun updateIsLoggedIn(isLoggedIn: Boolean) {
        myPreferencesDataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LOGGED_IN_KEYS] = isLoggedIn
        }
    }

    suspend fun updateUserCardName(userCardNum: String) {
        myPreferencesDataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_CARD_NAME] = userCardNum
        }
    }

    suspend fun updateUserName(userName: String) {
        myPreferencesDataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_NAME] = userName
        }
    }

    suspend fun updateUserFirstName(userFirstName: String) {
        myPreferencesDataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_FIRSTNAME] = userFirstName
        }
    }
}


















