package com.example.kingpowertest.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kingpowertest.R
import com.example.kingpowertest.data.model.PhotoModel
import com.example.kingpowertest.databinding.ItemListPhotoBinding

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
                .load(model.url)
                .error(R.color.colorBlack)
                .placeholder(R.color.colorPrimary)
                .into(binding.iv)
            binding.tv.text = model.title
        }
    }
}