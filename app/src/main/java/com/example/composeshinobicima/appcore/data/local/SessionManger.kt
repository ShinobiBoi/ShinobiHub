package com.example.composeshinobicima.appcore.data.local


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class SessionManager(private val context: Context) {

    companion object {
        private val SESSION_ID_KEY = stringPreferencesKey("session_id")
        private val ACCOUNT_ID_KEY = intPreferencesKey("account_id")
    }

    // Save both session id and account id
    suspend fun saveSessionData(sessionId: String, accountId: Int) {
        context.dataStore.edit { prefs ->
            prefs[SESSION_ID_KEY] = sessionId
            prefs[ACCOUNT_ID_KEY] = accountId
        }
    }

    // Save session id only
    suspend fun saveSessionId(sessionId: String) {
        context.dataStore.edit { prefs ->
            prefs[SESSION_ID_KEY] = sessionId
        }
    }

    // Save account id only
    suspend fun saveAccountId(accountId: Int) {
        context.dataStore.edit { prefs ->
            prefs[ACCOUNT_ID_KEY] = accountId
        }
    }

    // Read session id
    fun getSessionId(): Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[SESSION_ID_KEY]
    }

    // Read account id
    fun getAccountId(): Flow<Int?> = context.dataStore.data.map { prefs ->
        prefs[ACCOUNT_ID_KEY]
    }

    // Clear both (logout)
    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs.remove(SESSION_ID_KEY)
            prefs.remove(ACCOUNT_ID_KEY)
        }
    }
}
