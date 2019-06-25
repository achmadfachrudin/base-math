package com.fachrudin.base.presentation.multiplication

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
class MultiplicationActivityTest {

    @Mock
    lateinit var multiplicationActivity: MultiplicationActivity

    @Before
    fun setUp() {
        Dispatchers.resetMain()
        MockitoAnnotations.initMocks(this)
        multiplicationActivity = MultiplicationActivity()
    }

    @Test
    fun testResultMultiplication() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        // expected
        val expectedResult = 12

        // actual
        val actualResult = multiplicationActivity.resultMultiplication(3, 4)

        Assert.assertEquals(expectedResult, actualResult)
    }
}