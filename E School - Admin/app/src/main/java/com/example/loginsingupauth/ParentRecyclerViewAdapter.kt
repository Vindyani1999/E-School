package com.example.loginsingupauth

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ParentRecyclerViewAdapter(
    private val parentItemList: List<ParentItem>,
    private val selectedGrade: String?,
    private val selectedSubject: String?,
    private val selectedFeature: String?,

    private val context: Context
) : RecyclerView.Adapter<ParentRecyclerViewAdapter.ParentRecyclerViewHolder>(), ChildRecyclerViewAdapter.OnItemClickCallback {

    inner class ParentRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parentImageView: ImageView = itemView.findViewById(R.id.parentLogoIv)
        val parentTitle: TextView = itemView.findViewById(R.id.parentTitleTv)
        val childRecyclerView: RecyclerView = itemView.findViewById(R.id.childRecyclerView)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_item, parent, false)
        return ParentRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentRecyclerViewHolder, position: Int) {
        val parentItem = parentItemList[position]

        holder.parentTitle.text = parentItem.title
        holder.parentImageView.setImageResource(parentItem.image)

        holder.childRecyclerView.setHasFixedSize(true)
        holder.childRecyclerView.layoutManager = GridLayoutManager(holder.itemView.context, 2)

        val adapter = ChildRecyclerViewAdapter(parentItem.childItemList, selectedGrade, selectedSubject, selectedFeature, this, parentItem.title)
        holder.childRecyclerView.adapter = adapter

        val isExpandable = parentItem.isExpandable
        holder.childRecyclerView.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.constraintLayout.setOnClickListener {
            isAnyItemExpanded(position)
            parentItem.isExpandable = !parentItem.isExpandable
            notifyItemChanged(position)

            val adapter = ChildRecyclerViewAdapter(parentItem.childItemList, selectedGrade, selectedSubject, selectedFeature, this, parentItem.title)
            holder.childRecyclerView.adapter = adapter
        }
    }


    override fun onChildItemClick(position: Int, selectedGrade: String?, selectedSubject: String?, selectedFeature: String?, parentItemTitle: String?, childTitle: String) {
        val parentItem = parentItemList[position]

        val path = "${parentItem.title}/$childTitle"


        val intent = Intent(context, AllPdfsActivity::class.java)
        intent.putExtra("selectedGrade", selectedGrade)
        intent.putExtra("selectedSubject", selectedSubject)
        intent.putExtra("selectedFeature", selectedFeature)
        intent.putExtra("parentItemTitle", parentItemTitle)
        intent.putExtra("childItemTitle", childTitle)

        context.startActivity(intent)
    }

    private fun isAnyItemExpanded(position: Int) {
        val temp = parentItemList.indexOfFirst {
            it.isExpandable
        }

        if (temp >= 0 && temp != position) {
            parentItemList[temp].isExpandable = false
            notifyItemChanged(temp)
        }
    }

    override fun getItemCount(): Int {
        return parentItemList.size
    }


}

