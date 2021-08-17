package com.example.newsfresh

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsfresh.R.layout.item_news

class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition ])
    }
        return viewHolder

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val currentItem = items[position]
        holder.titleView.text  = currentItem.title
        holder.authorView.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {

        return items.size
    }

    fun updateNews(updatedNews:ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }


}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.news_title)
    val authorView: TextView = itemView.findViewById(R.id.news_author)

    val imageView: ImageView = itemView.findViewById(R.id.news_image)
}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}