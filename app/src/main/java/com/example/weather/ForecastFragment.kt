package com.example.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FragmentForecastBinding
import com.example.weather.model.forecast.ForecastResult
import com.example.weather.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding

    private lateinit var layoutManager: RecyclerView.LayoutManager
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

        initViews()

        mainViewModel.forecastResult.observe(viewLifecycleOwner, Observer { itForecast ->
            val forecastResultList = mutableListOf<ForecastResult>()
            itForecast.list.forEach {
                forecastResultList.add(
                    ForecastResult(
                        main = it.weather.first().main,
                        date = it.dt_txt,
                        temp = it.main.temp.toString(),
                        description = it.weather.first().description
                    )
                )
            }
            adapter.updateList(forecastResultList)
        })
    }

    private fun initViews() {
        layoutManager = LinearLayoutManager(requireContext())
        binding?.forecastRecycler?.layoutManager = layoutManager
        adapter = ForecastAdapter(requireContext(), listOf())
        binding?.forecastRecycler?.adapter = adapter
    }

    companion object {
        fun newInstance() = ForecastFragment()
    }

}