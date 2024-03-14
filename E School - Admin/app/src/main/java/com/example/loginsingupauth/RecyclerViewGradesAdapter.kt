package com.example.loginsingupauth

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewGradesAdapter(
    private val getActivity: GradesActivity,
    private val gradesList: List<Grades_DataClass>
) : RecyclerView.Adapter<RecyclerViewGradesAdapter.GradeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_grades_list_item, parent, false)
        return GradeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gradesList.size
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        holder.tvGradeTitle.text = gradesList[position].title
        holder.ivGradeImg.setImageResource(gradesList[position].image)
        holder.cardView.setOnClickListener {

            val selectedGrade = gradesList[position].title


            val intent = Intent(getActivity, SubjectsActivity::class.java)
            intent.putExtra("selectedGrade", selectedGrade)
            getActivity.startActivity(intent)
        }
    }

    class GradeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvGradeTitle: TextView = itemView.findViewById(R.id.tvGradeTitle)
        val ivGradeImg: ImageView = itemView.findViewById(R.id.ivGradeImg)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}