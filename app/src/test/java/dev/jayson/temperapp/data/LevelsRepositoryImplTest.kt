package dev.jayson.temperapp.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import dev.jayson.temperapp.data.model.LevelListModel
import dev.jayson.temperapp.data.model.LevelModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayInputStream
import java.io.InputStream

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LevelsRepositoryImplTest {

    @Mock
    private lateinit var mockContext: Context

    private lateinit var levelsRepository: LevelsRepositoryImpl



    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        levelsRepository = LevelsRepositoryImpl(mockContext)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testFetchLevelList() = runTest {
        // Mock JSON response
        val mockJson = "{\"example\":\"data\"}"
        val mockInputStream: InputStream = ByteArrayInputStream(mockJson.toByteArray())

        // Mock the behavior of Context's assets method to return the mock InputStream
        val mockAssetManager = Mockito.mock(android.content.res.AssetManager::class.java)
        `when`(mockContext.assets).thenReturn(mockAssetManager)
        `when`(mockAssetManager.open("notion.json")).thenReturn(mockInputStream)

        // Call the method under test
        val result = levelsRepository.fetchLevelList()

        // Verify that the method returns the expected LevelListModel
        val list = listOf(
            LevelModel(null, "sdfl;sdfk", "sdfsdf", "sdfsf", "sdf")
        )

        val expectedModel = LevelListModel(list)
        assertEquals(expectedModel, result)


        // Attempt Gson parsing manually to catch any Gson parsing errors
        try {
            val gsonResult = Gson().fromJson(mockJson, LevelListModel::class.java)
            Log.e("success", "$gsonResult")

        } catch (e: Exception) {
            Log.e("failedGson", "$e")
        }
    }
    }
