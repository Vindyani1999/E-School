package com.example.loginsingupauth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GradesActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var recyclerViewGradesAdapter: RecyclerViewGradesAdapter? = null
    private var gradesList = mutableListOf<Grades_DataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)

        gradesList = ArrayList()
        recyclerView = findViewById<View>(R.id.rvGrade) as RecyclerView
        recyclerViewGradesAdapter = RecyclerViewGradesAdapter(this@GradesActivity, gradesList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerViewGradesAdapter

        prepareGradesListData()
    }

    private fun prepareGradesListData() {
        var grade = Grades_DataClass("Grade 11", R.drawable.grade__11)
        gradesList.add(grade)
        grade = Grades_DataClass("Grade 10", R.drawable.grade__10)
        gradesList.add(grade)
        grade = Grades_DataClass("Grade 09", R.drawable.grade__09)
        gradesList.add(grade)
        grade = Grades_DataClass("Grade 08", R.drawable.grade__08)
        gradesList.add(grade)
        grade = Grades_DataClass("Grade 07", R.drawable.grade__07)
        gradesList.add(grade)
        grade = Grades_DataClass("Grade 06", R.drawable.grade__06)
        gradesList.add(grade)





        recyclerViewGradesAdapter!!.notifyDataSetChanged()
    }
}
