package dev.jayson.temperapp.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.jayson.temperapp.data.LevelsRepositoryImpl
import dev.jayson.temperapp.domain.usecase.LevelsUseCase

@Suppress("UNCHECKED_CAST")
class LevelsViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LevelsViewModel::class.java)) {
            val repository = LevelsRepositoryImpl(context)
            val fetchLevelsUseCase = LevelsUseCase(repository)
            return LevelsViewModel(fetchLevelsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}