package com.example.kingpowertest.presentation.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kingpowertest.databinding.DetailFragmentBinding
import com.example.kingpowertest.presentation.main.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel
    private var _binding: DetailFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}