package com.example.to_doapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.util.Constants.PREFERENCE_KEY
import com.example.to_doapp.util.Constants.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ViewModelScoped
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCE_NAME)
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object PreferenceKeys{
        val sortKeys = stringPreferencesKey(name= PREFERENCE_KEY)
    }

    private val dataStore = context.dataStore

    suspend fun persistSortStore(priority: Priority){
        dataStore.edit { preference->
            preference[PreferenceKeys.sortKeys] = priority.name
        }
    }

    val readSortState:Flow<String> = dataStore.data
        .catch { exception->
            if (exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
    }
        .map { preference->
            val sortState = preference[PreferenceKeys.sortKeys]?: Priority.NONE.name
            sortState
        }
}