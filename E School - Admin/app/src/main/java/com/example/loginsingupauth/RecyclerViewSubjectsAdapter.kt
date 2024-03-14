package com.example.loginsingupauth

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewSubjectsAdapter(
    private val getActivity: SubjectsActivity,
    private val selectedGrade: String?,
    private val subjectsList: List<Subjects_DataClass>
) : RecyclerView.Adapter<RecyclerViewSubjectsAdapter.SubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_subjects_list_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return subjectsList.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.tvSubjectTitle.text = subjectsList[position].title
        holder.ivSubjectImg.setImageResource(subjectsList[position].image)

        holder.cardView.setOnClickListener {

            val intent = Intent(getActivity, FeaturesActivity::class.java)
            intent.putExtra("selectedGrade", selectedGrade)
            intent.putExtra("selectedSubject", subjectsList[position].title)
            getActivity.startActivity(intent)
        }



    }

    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSubjectTitle: TextView = itemView.findViewById(R.id.tvGradeTitle)
        val ivSubjectImg: ImageView = itemView.findViewById(R.id.ivGradeImg)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}
