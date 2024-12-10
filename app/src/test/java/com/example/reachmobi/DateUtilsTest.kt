package com.example.reachmobi

import com.example.reachmobi.utils.formatDate
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DateUtilsTest {

    @Test
    fun `test formatDate with valid date string`() {
        val dateString = "2024-12-09T10:15:30+00:00"

        val result = formatDate(dateString)

        // Then the result should match the expected formatted date
        assertEquals("December 09, 2024", result)
    }

}