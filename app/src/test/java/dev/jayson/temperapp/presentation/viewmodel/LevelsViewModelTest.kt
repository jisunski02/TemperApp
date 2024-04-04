package dev.jayson.temperapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dev.jayson.temperapp.data.model.ActivityModel
import dev.jayson.temperapp.data.model.DetailsModel
import dev.jayson.temperapp.data.model.FileModel
import dev.jayson.temperapp.data.model.IconModel
import dev.jayson.temperapp.data.model.LevelListModel
import dev.jayson.temperapp.data.model.LevelModel
import dev.jayson.temperapp.data.model.LockedIconModel
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

        val details = DetailsModel(10)

        val file = FileModel("android", details, "","")

        val testLockedicon = LockedIconModel("lockedicon123", file, "lockedicon")

        val testIcon = IconModel("icon1", null, "")
        val testIcon2 = IconModel("icon2", null, "")
        val testIcon3 = IconModel("icon3", null, "")

        val testActivities = listOf(
            ActivityModel("1", "Hello1", null, testIcon, "00", testLockedicon, "", "Activity1",
                "", ""),
            ActivityModel("2", "Hello2", null, testIcon2, "00", testLockedicon, "", "Activity2",
                "", ""),
            ActivityModel("3", "Hello3", null, testIcon3, "00", testLockedicon, "", "Activity3",
                "", ""),
        )

        val testActivities2 = listOf(
            ActivityModel("11", "Hello1", null, testIcon, "00", null, "", "Activity1",
                "", ""),
            ActivityModel("2", "Hello2", null, testIcon, "00", null, "", "Activity2",
                "", ""),
        )

        val testLevels = listOf(
            LevelModel(testActivities, "Level", "","", "AndroidTest" ),
            LevelModel(testActivities2, "", "","", "" ),
            LevelModel(testActivities, "", "","", "" ),
            LevelModel(testActivities2, "", "","", "" )
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

    @Test
    fun `fetchLevels fetches activities correct title`() = runTest {
        // Act
        viewModel.fetchLevels() // Call the method without parameters

        // Assert
        val levelsValue = viewModel.levels.value // Assuming you have an extension function to get LiveData value
        assertEquals("Activity2", levelsValue?.levelModels!![1].activities!![1].title)

    }

    @Test
    fun `fetchLevels fetches icon correct description`() = runTest {
        // Act
        viewModel.fetchLevels() // Call the method without parameters

        // Assert
        val levelsValue = viewModel.levels.value // Assuming you have an extension function to get LiveData value
        assertEquals("icon1", levelsValue?.levelModels!![0].activities!![0].iconModel!!.description)

    }

    @Test
    fun `fetchLevels fetches lockedicon correct title`() = runTest {
        // Act
        viewModel.fetchLevels() // Call the method without parameters

        // Assert
        val levelsValue = viewModel.levels.value // Assuming you have an extension function to get LiveData value
        assertEquals("lockedicon", levelsValue?.levelModels!![0].activities!![0].lockedIconModel!!.title)

    }

    @Test
    fun `fetchLevels fetches file correct content type`() = runTest {
        // Act
        viewModel.fetchLevels() // Call the method without parameters

        // Assert
        val levelsValue = viewModel.levels.value // Assuming you have an extension function to get LiveData value
        assertEquals("android", levelsValue?.levelModels!![0].activities!![0].lockedIconModel!!.fileModel!!.contentType)
    }

    @Test
    fun `fetchLevels fetches details correct size`() = runTest {
        // Act
        viewModel.fetchLevels() // Call the method without parameters

        // Assert
        val levelsValue = viewModel.levels.value // Assuming you have an extension function to get LiveData value
        assertEquals(10, levelsValue?.levelModels!![0].activities!![0].lockedIconModel!!.fileModel!!.detailsModel!!.size)
    }
}