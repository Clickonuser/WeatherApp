package com.example.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FragmentForecastBinding
import com.example.weather.model.forecast.ForecastResult

class ForecastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding

    private lateinit var adapter: ForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding?.forecastRecycler?.layoutManager = layoutManager

        val forecastResultLists = listOf<ForecastResult>(
            ForecastResult("testDate", "testTemp", "testDescription"),
            ForecastResult("testDate", "testTemp", "testDescription"),
            ForecastResult("testDate", "testTemp", "testDescription"),
            ForecastResult("testDate", "testTemp", "testDescription"),
            ForecastResult("testDate", "testTemp", "testDescription")
        )
        adapter = ForecastAdapter(forecastResultLists)
        binding?.forecastRecycler?.adapter = adapter
    }

    companion object {
        fun newInstance() = ForecastFragment()
    }

}