package com.example.a3200midterm

import com.example.a3200midterm.ui.notifications.NotificationsFragment
import com.example.a3200midterm.ui.notifications.NotificationsViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NotificationsTimerTest {

    @Test
    fun `test formatTime`() {
        val fragment = NotificationsFragment()
        val formattedTime = fragment.formatTime(1337)
        assertEquals("00:22:17", formattedTime)
    }

    @Test
    fun `test formatTime with 2102`() {
        val fragment = NotificationsFragment()
        val formattedTime = fragment.formatTime(2102)
        assertEquals("00:35:02", formattedTime)
    }

    @Test
    fun `test formatTime with 3150`() {
        val fragment = NotificationsFragment()
        val formattedTime = fragment.formatTime(3150)
        assertEquals("00:52:30", formattedTime)
    }

    @Test
    fun `test formatTime with 3200`() {
        val fragment = NotificationsFragment()
        val formattedTime = fragment.formatTime(3200)
        assertEquals("00:53:20", formattedTime)
    }

    private lateinit var viewModel: NotificationsViewModel

    @Before
    fun setup() {
        viewModel = NotificationsViewModel()
    }

    @Test
    fun testTimerFunctionality() {
        viewModel.startTimer()

        Thread.sleep(100)
        assertEquals(1, viewModel.timerValue.value?.toInt())

        viewModel.pauseTimer()
        Thread.sleep(1000)

        assertEquals(1, viewModel.timerValue.value?.toInt())

        viewModel.resetTimer()
        assertEquals(0, viewModel.timerValue.value?.toInt())
    }
}