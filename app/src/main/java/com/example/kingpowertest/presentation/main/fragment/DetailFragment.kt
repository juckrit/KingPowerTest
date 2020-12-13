package com.example.kingpowertest.presentation.main.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.kingpowertest.R
import com.example.kingpowertest.data.model.PhotoModel
import com.example.kingpowertest.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var photoModel: PhotoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { arguments ->
            val args = DetailFragmentArgs.fromBundle(arguments)
            photoModel = args.photoModel
        }
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayPhoto()
    }

    private fun displayPhoto(){
        Glide.with(requireContext())
            .load(photoModel.url)
            .error(R.color.colorBlack)
            .placeholder(R.color.colorPrimary)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("TEST",e.toString())
                    Log.d("TEST",model.toString())
                    Log.d("TEST",target.toString())
                    Log.d("TEST",isFirstResource.toString())
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("TEST",resource.toString())
                    Log.d("TEST",model.toString())
                    Log.d("TEST",target.toString())
                    Log.d("TEST",dataSource.toString())
                    Log.d("TEST",isFirstResource.toString())
                    return false

                }
            })
            .into(binding.iv)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}