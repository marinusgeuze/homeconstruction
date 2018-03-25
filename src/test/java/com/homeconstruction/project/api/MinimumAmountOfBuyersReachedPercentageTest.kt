package com.homeconstruction.project.api

import org.junit.Assert.*
import org.junit.Test

class MinimumAmountOfBuyersReachedPercentageTest {

    @Test
    fun testValid1Percentage() {

        val projectReachedPercentage = MinimumAmountOfBuyersReachedPercentage(1)
        assertSame(1, projectReachedPercentage.minimumAmountOfBuyersReachedPercentage)
    }

    @Test
    fun testValid70Percentage() {

        val projectReachedPercentage = MinimumAmountOfBuyersReachedPercentage(70)
        assertSame(70, projectReachedPercentage.minimumAmountOfBuyersReachedPercentage)
    }

    @Test
    fun testValid100Percentage() {

        val projectReachedPercentage = MinimumAmountOfBuyersReachedPercentage(100)
        assertSame(100, projectReachedPercentage.minimumAmountOfBuyersReachedPercentage)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalid0Percentage() {

        MinimumAmountOfBuyersReachedPercentage(0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalid101Percentage() {

        MinimumAmountOfBuyersReachedPercentage(101)
    }
}