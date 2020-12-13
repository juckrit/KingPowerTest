package com.example.kingpowertest.presentation.main.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.kingpowertest.R
import com.example.kingpowertest.data.model.PhotoModel
import com.example.kingpowertest.databinding.ItemListPhotoBinding
import com.example.kingpowertest.presentation.main.fragment.MainFragmentDirections

class PhotoAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val photos = ArrayList<PhotoModel>()


    fun setList(photoList:List<PhotoModel>){
        photos.clear()
        photos.addAll(photoList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            ItemListPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotoViewHolder).binding(photos[position])
    }

    class PhotoViewHolder(private val binding: ItemListPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(model:PhotoModel){
            Glide.with(binding.root.context)
                .load(model.thumbnailUrl)
                .error(R.color.colorBlack)
                .placeholder(R.color.colorPrimary)
                .listener(object:RequestListener<Drawable>{
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

            binding.tv.text = model.title
            binding.root.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(
                    model
                )
                it.findNavController().navigate(action)
            }
        }
    }
}