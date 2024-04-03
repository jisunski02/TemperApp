package dev.jayson.temperapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dev.jayson.temperapp.data.model.ActivityModel
import dev.jayson.temperapp.data.model.LevelListModel
import dev.jayson.temperapp.data.model.LevelModel
import dev.jayson.temperapp.domain.usecase.LevelsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi

class LevelsViewModelTest{

    private val testDispatcher = TestCoroutineDispatcher()
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LevelsViewModel
    private val levelsUseCase: LevelsUseCase = mockk()
    private val levelsObserver: Observer<LevelListModel> = mockk(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        // Mock the LevelsUseCase to return a specific result when invoked
        val testActivities = listOf(
            ActivityModel("1", "Hello", null, null, "00", null, "", "",
                "", ""),
            ActivityModel("1", "Hello", null, null, "00", null, "", "",
                "", ""),
            ActivityModel("1", "Hello", null, null, "00", null, "", "",
                "", ""),
        )

        val testLevels = listOf(
            LevelModel(null, "", "","", "AndroidTest" ),
            LevelModel(null, "", "","", "" ),
            LevelModel(null, "", "","", "" ),
            LevelModel(null, "", "","", "" )
        )

        coEvery { levelsUseCase.invoke() } returns LevelListModel(testLevels)

        viewModel = LevelsViewModel(levelsUseCase).apply {
            levels.observeForever(levelsObserver)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `fetchLevels fetches levels correct size`() = runTest {
        // Act
        viewModel.fetchLevels() // Call the method without parameters

        // Assert
        val levelsValue = viewModel.levels.value // Assuming you have an extension function to get LiveData value
        val size = levelsValue?.levelModels?.size
        assertEquals(4, size)

    }

    @Test
    fun `fetchLevels fetches levels correct title`() = runTest {
        // Act
        viewModel.fetchLevels() // Call the method without parameters

        // Assert
        val levelsValue = viewModel.levels.value // Assuming you have an extension function to get LiveData value
        assertEquals("AndroidTest", levelsValue?.levelModels!![0].title)

    }
}