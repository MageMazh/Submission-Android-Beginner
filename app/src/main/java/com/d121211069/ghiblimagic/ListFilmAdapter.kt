package com.d121211069.ghiblimagic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrecyclerview.Film

class ListFilmAdapter(private val listFilm: ArrayList<Film>) :
    RecyclerView.Adapter<ListFilmAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_film, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listFilm[position]
        holder.imgPhoto.setImageResource(photo)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listFilm[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listFilm.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Film)
    }
}