package com.fachrudin.base.presentation.fibonacci

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by achmad.fachrudin on 25-Jun-19
 */
class FibonacciActivityTest {

    @Mock
    lateinit var fibonacciActivity: FibonacciActivity

    @Before
    fun setUp() {
        Dispatchers.resetMain()
        MockitoAnnotations.initMocks(this)
        fibonacciActivity = FibonacciActivity()
    }

    @Test
    fun testListFibonacci() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        // expected
        val expectedResult = "0, 1, 1"

        // actual
        val actualResult = fibonacciActivity.listFibonacci(3)

        Assert.assertEquals(expectedResult, actualResult)
    }
}