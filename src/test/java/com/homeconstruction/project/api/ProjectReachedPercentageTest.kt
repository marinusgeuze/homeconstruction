package com.homeconstruction.project.api

import org.junit.Assert.*
import org.junit.Test

class ProjectReachedPercentageTest {

    @Test
    fun testValid1Percentage() {

        val projectReachedPercentage = ProjectReachedPercentage(1)
        assertSame(1, projectReachedPercentage.percentage)
    }

    @Test
    fun testValid70Percentage() {

        val projectReachedPercentage = ProjectReachedPercentage(70)
        assertSame(70, projectReachedPercentage.percentage)
    }

    @Test
    fun testValid100Percentage() {

        val projectReachedPercentage = ProjectReachedPercentage(100)
        assertSame(100, projectReachedPercentage.percentage)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalid0Percentage() {

        ProjectReachedPercentage(0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalid101Percentage() {

        ProjectReachedPercentage(101)
    }
}