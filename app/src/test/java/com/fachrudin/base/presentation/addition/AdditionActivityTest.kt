package com.fachrudin.base.presentation.addition

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
class AdditionActivityTest {

    @Mock
    lateinit var additionActivity: AdditionActivity

    @Before
    fun setUp() {
        Dispatchers.resetMain()
        MockitoAnnotations.initMocks(this)
        additionActivity = AdditionActivity()
    }

    @Test
    fun testResultAddition() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        // expected
        val expectedResult = 7

        // actual
        val actualResult = additionActivity.resultAddition(3, 4)

        Assert.assertEquals(expectedResult, actualResult)
    }
}