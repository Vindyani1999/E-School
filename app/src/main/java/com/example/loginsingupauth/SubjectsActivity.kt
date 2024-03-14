// SubjectsActivity.kt
package com.example.loginsingupauth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SubjectsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewSubjectsAdapter: RecyclerViewSubjectsAdapter
    private var subjectsList = mutableListOf<Subjects_DataClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)

        recyclerView = findViewById(R.id.rvSubjects)
        val selectedGrade = intent.getStringExtra("selectedGrade")
        recyclerViewSubjectsAdapter = RecyclerViewSubjectsAdapter(this, selectedGrade, subjectsList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewSubjectsAdapter
        prepareSubjectsListData(selectedGrade)
    }

    private fun prepareSubjectsListData(selectedGrade: String?) {
        // Assuming you have a map of grades to subjects
        val gradeSubjectsMap = mapOf(
            "Grade 11" to listOf("Maths", "Commerce","Geography", "Technology", "Science", "Health","Sinhala","Art","History", "English"),
            "Grade 10" to listOf("Maths", "Commerce","Geography", "Technology", "Science", "Health","Sinhala","Art","History", "English"),
            "Grade 09" to listOf("Maths", "Commerce","Geography", "Technology", "Science", "Health","Sinhala","Art","History", "English"),
            "Grade 08" to listOf("Maths", "Commerce","Geography", "Technology", "Science", "Health","Sinhala","Art","History", "English"),
            "Grade 07" to listOf("Maths", "Commerce","Geography", "Technology", "Science", "Health","Sinhala","Art","History", "English"),
            "Grade 06" to listOf("Maths", "Commerce","Geography", "Technology", "Science", "Health","Sinhala","Art","History", "English"),
        )

        val subjectsForGrade = gradeSubjectsMap[selectedGrade]

        if (subjectsForGrade != null) {
            subjectsList.clear()
            for (subject in subjectsForGrade) {
                val subjectImage = getSubjectImage(subject)
                val subjectsDataClass = Subjects_DataClass(subject, subjectImage)
                subjectsList.add(subjectsDataClass)
            }
            recyclerViewSubjectsAdapter.notifyDataSetChanged()
        } else {

        }
    }

    private fun getSubjectImage(subject: String): Int {
        return when (subject) {
            "Maths" -> R.drawable.maths

            "Commerce"->R.drawable.commerce
            "Geography"->R.drawable.geo
            "Technology" -> R.drawable.it
            "Science" -> R.drawable.science
            "English" -> R.drawable.english
            "Sinhala" -> R.drawable.sinhala
            "History" -> R.drawable.history
            "Art" -> R.drawable.art
            "Health" -> R.drawable.health

            else -> R.drawable.science
        }
    }
}
