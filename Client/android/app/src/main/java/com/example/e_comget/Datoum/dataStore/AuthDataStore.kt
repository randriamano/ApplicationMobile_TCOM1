//package com.example.e_comget.Datoum.dataStore
//
//import android.content.Context
//import kotlinx.coroutines.flow.Flow
//
//object AuthDataStore {
//    private const val DATASTORE_NAME = "auth_prefs"
//    private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)
//
//    private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
//
//    val isLoggedIn: Flow<Boolean> = dataStore.data.map { preferences ->
//        preferences[IS_LOGGED_IN_KEY] ?: false
//    }
//
//    suspend fun setIsLoggedIn(isLoggedIn: Boolean) {
//        dataStore.edit { preferences ->
//            preferences[IS_LOGGED_IN_KEY] = isLoggedIn
//        }
//    }
//}