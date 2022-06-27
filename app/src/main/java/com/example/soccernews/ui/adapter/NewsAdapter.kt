package com.example.soccernews.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soccernews.databinding.NewsItemBinding
import com.example.soccernews.domain.News
import com.squareup.picasso.Picasso

open class NewsAdapter(private val dataSet: List<News>, private val mContext: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    private lateinit var adapterNews: NewsAdapter



    inner class ViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var adapterNews: NewsAdapter
        fun bind(item: News) {
            binding.titleText.text = item.title
            binding.descriptionText.text = item.description

            adapterNews = NewsAdapter(dataSet, mContext)

            Picasso.get().load(item.image).into(binding.ivThumbnail);

            binding.btnOpenLink.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.link)
                //Abrir link: (Peguei esse context colocando um construtor de Context no Adapter e instanciando ela no fragment usando requireContext)
                mContext.startActivity(intent)
            }
            //Compartilhar string (link):
            binding.btnShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, item.title)
                intent.putExtra(Intent.EXTRA_TEXT, ("O link da noticia é " + item.link))
                mContext.startActivity(Intent.createChooser(intent,"Share via"))
            }
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
