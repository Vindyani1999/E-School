package com.example.loginsingupauth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class ChildRecyclerViewAdapter(
    private val childList: List<ChildItem>,
    private val selectedGrade: String?,
    private val selectedSubject: String?,
    private val selectedFeature: String?,
    private val onItemClickCallback: OnItemClickCallback,
    private val parentItemTitle: String?
) : RecyclerView.Adapter<ChildRecyclerViewAdapter.ChildViewHolder>() {

    interface OnItemClickCallback {
        fun onChildItemClick(
            position: Int,
            selectedGrade: String?,
            selectedSubject: String?,
            selectedFeature: String?,
            parentItemTitle: String?,
            childTitle: String
        )
    }

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.childLogoIv)
        val title: TextView = itemView.findViewById(R.id.childTitleTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_item, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val childItem = childList[position]

        holder.imageView.setImageResource(childItem.image)
        holder.title.text = childItem.title
        holder.itemView.setOnClickListener {
            onItemClickCallback.onChildItemClick(position, selectedGrade, selectedSubject, selectedFeature, parentItemTitle, childItem.title)
        }
    }

    override fun getItemCount(): Int {
        return childList.size
    }

}