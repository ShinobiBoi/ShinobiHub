package com.example.composeshinobicima.appcore.data.local


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class SessionManager(private val context: Context) {

    companion object {
        private val SESSION_ID_KEY = stringPreferencesKey("session_id")
    }

    // Save session id
    suspend fun saveSessionId(sessionId: String) {
        context.dataStore.edit { prefs ->
            prefs[SESSION_ID_KEY] = sessionId
        }
    }

    // Read session id
    fun getSessionId(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[SESSION_ID_KEY]
        }
    }

    // Delete session id (logout)
    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs.remove(SESSION_ID_KEY)
        }
    }
}