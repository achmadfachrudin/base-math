package com.fachrudin.base.presentation.primary

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
class PrimaryActivityTest {

    @Mock
    lateinit var primaryActivity: PrimaryActivity

    @Before
    fun setUp() {
        Dispatchers.resetMain()
        MockitoAnnotations.initMocks(this)
        primaryActivity = PrimaryActivity()
    }

    @Test
    fun testIsPrimaryNumber() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        // expected
        val expectedResult = true

        // actual
        val actualResult = primaryActivity.isPrimaryNumber(7)

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun testListPrimary() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        // expected
        val expectedResult = "2, 3, 5"

        // actual
        val actualResult = primaryActivity.listPrimaryNumber(3)

        Assert.assertEquals(expectedResult, actualResult)
    }
}