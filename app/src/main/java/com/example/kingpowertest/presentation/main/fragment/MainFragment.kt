package com.example.kingpowertest.presentation.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.example.kingpowertest.data.model.ResultWrapper
import com.example.kingpowertest.databinding.MainFragmentBinding
import com.example.kingpowertest.presentation.main.adapter.PhotoAdapter
import com.example.kingpowertest.presentation.main.model.PhotoPresentationModel
import com.example.kingpowertest.presentation.main.viewmodel.MainFragmentViewModel
import org.koin.android.ext.android.inject


class MainFragment : Fragment() {

    companion object {
        fun newInstance() =
            MainFragment()
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: MainFragmentViewModel by inject()
    private var _binding: MainFragmentBinding? = null
    private lateinit var adapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchData()
        initRecyclerView()
        displayPhotos()
    }

    private fun fetchData() {
        viewModel.getPhotoByAlbumId(1)
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = PhotoAdapter()
        binding.recyclerView.adapter = adapter
    }


    private fun displayPhotos() {
        val responseLiveData = viewModel.getPhotoListLiveData()

        responseLiveData.observe(requireActivity(), Observer {
            when (responseLiveData.value) {
                is ResultWrapper.Success -> {
                    adapter.setList((responseLiveData.value as ResultWrapper.Success<List<PhotoPresentationModel>>).value)
                    adapter.notifyDataSetChanged()
                }
                is ResultWrapper.Error -> {
                    Toast.makeText(requireContext(), "No data available", Toast.LENGTH_LONG).show()

                }
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

}