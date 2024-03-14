package com.example.loginsingupauth

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class VideoListAdapter(private val videoList: List<VideoFile>) :
    RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>() {

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val fileNameTextView: TextView = itemView.findViewById(R.id.fileNameTextView)
        val downloadUrlTextView: TextView = itemView.findViewById(R.id.downloadUrlTextView)

        val videoView: VideoView

        init {
            videoView = itemView.findViewById(R.id.videoViewId)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val currentItem = videoList[position]
        holder.fileNameTextView.text = currentItem.fileName
        holder.downloadUrlTextView.text = currentItem.downloadUrl

        holder.cardView.setOnClickListener {
            val context = holder.cardView.context
            val intent = Intent(context, VideoPlayerActivity::class.java)
            intent.putExtra("videoUrl", currentItem.downloadUrl)
            intent.putExtra("videoFileName", currentItem.fileName)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}
