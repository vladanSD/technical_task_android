package com.vladan.technical_task_android.ui.home.validations

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidateNameTest {

    private lateinit var validateName: ValidateName

    @Before
    fun setUp() {
        validateName = ValidateName()
    }

    @Test
    fun `Name must not be blank, return error`() {
        val result = validateName.execute("")
        assertEquals(result.successful, false)
    }

    @Test
    fun `Name contains more than 2 chars`() {
        val result = validateName.execute("Vladan")
        assertEquals(result.successful, true)
    }
}