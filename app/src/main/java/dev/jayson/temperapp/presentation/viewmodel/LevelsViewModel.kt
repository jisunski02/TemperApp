package dev.jayson.temperapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jayson.temperapp.data.model.LevelListModel
import dev.jayson.temperapp.domain.usecase.LevelsUseCase
import kotlinx.coroutines.launch

class LevelsViewModel(private val levelsUseCase: LevelsUseCase): ViewModel() {

    private val levelsLiveData = MutableLiveData<LevelListModel>()
    val levels: LiveData<LevelListModel> = levelsLiveData

    init {
        fetchLevels()
    }

    fun fetchLevels() {
        viewModelScope.launch {
            try {
                val fetchedLevels = levelsUseCase.invoke()
                levelsLiveData.postValue(fetchedLevels)
            } catch (e: Exception) {
                Log.e("LevelsViewModel", "Error fetching levels", e)
                // Handle error state
            }
        }
    }
}