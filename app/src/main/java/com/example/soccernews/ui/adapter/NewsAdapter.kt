package com.example.soccernews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soccernews.databinding.NewsItemBinding
import com.example.soccernews.domain.News
import com.squareup.picasso.Picasso

open class NewsAdapter(private val dataSet: List<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class ViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News) {
            binding.titleText.text = item.title
            binding.descriptionText.text = item.description

            Picasso.get().load(item.image).into(binding.ivThumbnail);
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(dataSet[position])

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
