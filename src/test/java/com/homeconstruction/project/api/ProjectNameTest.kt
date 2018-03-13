package com.homeconstruction.project.api

import org.junit.Assert.*
import org.junit.Test

class ProjectNameTest {

    @Test
    fun testValidName() {

        val projectName = ProjectName("Test")
        assertSame("Test", projectName.name)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBlankName() {

        ProjectName(" ");
    }

    @Test(expected = IllegalArgumentException::class)
    fun testEmptyName() {

        ProjectName("");
    }
}