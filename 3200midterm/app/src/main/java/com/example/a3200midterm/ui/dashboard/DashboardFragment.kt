package com.example.a3200midterm.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a3200midterm.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbTimerView: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dbTimerView = ViewModelProvider(this).get(DashboardViewModel::class.java)

        val timerTextView: TextView = binding.dbTextView
        dbTimerView.timerValue.observe(viewLifecycleOwner) {
            timerTextView.text = formatTime(it)
        }

        val startButton: Button = binding.dbStart
        startButton.setOnClickListener { dbTimerView.startTimer() }

        val pauseButton: Button = binding.dbPause
        pauseButton.setOnClickListener { dbTimerView.pauseTimer() }

        val resetButton: Button = binding.dbReset
        resetButton.setOnClickListener { dbTimerView.resetTimer() }

        return root
    }

    fun formatTime(timeInSeconds: Long): String {
        val hours = timeInSeconds / 3600
        val minutes = (timeInSeconds % 3600) / 60
        val seconds = timeInSeconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}