package com.fachrudin.base.presentation.main

import com.fachrudin.base.network.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainPageActivityTest {

    @Mock
    lateinit var viewModel: MainPageViewModel

    private val service = RetrofitFactory.makeRetrofitServiceForTest()

    @Before
    fun setUp() {
        Dispatchers.resetMain()
        MockitoAnnotations.initMocks(this)
        viewModel = MainPageViewModel(null)
    }

    @Test
    fun getMenuFromApi() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        // expected
        val expectedResult = "Addition"

        // actual
        val actualRespone = runBlocking {
            service.getMenuAsync().await()
        }
        val actualResult = actualRespone.body()

        delay()

        Assert.assertEquals(expectedResult, actualResult?.menu?.get(0)?.title)
    }

    private fun delay() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}