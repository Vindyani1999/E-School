package com.example.loginsingupauth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PastPapersActivity : AppCompatActivity() {

    private lateinit var parentRecyclerView: RecyclerView
    private lateinit var parentList: ArrayList<ParentItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectedGrade = intent.getStringExtra("selectedGrade")
        val selectedSubject = intent.getStringExtra("selectedSubject")
        val selectedFeature = intent.getStringExtra("selectedFeature")


        parentRecyclerView = findViewById(R.id.parentRecyclerView)
        parentRecyclerView.setHasFixedSize(true)
        parentRecyclerView.layoutManager = LinearLayoutManager(this)
        parentList = ArrayList()

        prepareData()
        val adapter = ParentRecyclerViewAdapter(parentList, selectedGrade, selectedSubject, selectedFeature, this)

        parentRecyclerView.adapter = adapter
    }

    private fun prepareData() {

        val childItems1 = ArrayList<ChildItem>()
        childItems1.add(ChildItem("Southern province", R.drawable.folder))
        childItems1.add(ChildItem("Western province", R.drawable.folder))
        childItems1.add(ChildItem("North western province", R.drawable.folder))
        childItems1.add(ChildItem("North Central Province", R.drawable.folder))
        childItems1.add(ChildItem("Uva province", R.drawable.folder))
        childItems1.add(ChildItem("Sabaragamuwa Province", R.drawable.folder))
        childItems1.add(ChildItem("Northern Province", R.drawable.folder))
        childItems1.add(ChildItem("Central Province", R.drawable.folder))
        childItems1.add(ChildItem("Eastern Province", R.drawable.folder))

        parentList.add(ParentItem("1st Term (Provincial)", R.drawable.first, childItems1))


        val childItem2 = ArrayList<ChildItem>()
        childItem2.add(ChildItem("Royal College - Colombo", R.drawable.folder))
        childItem2.add(ChildItem("Ananda College - Colombo", R.drawable.folder))
        childItem2.add(ChildItem("Nalanda College - Colombo", R.drawable.folder))
        childItem2.add(ChildItem("Vishaka Vidyalaya - Colombo", R.drawable.folder))
        childItem2.add(ChildItem("Bishop's College, Colombo", R.drawable.folder))
        childItem2.add(ChildItem("D.S. Senanayake College, Colombo", R.drawable.folder))
        childItem2.add(ChildItem("St.Joseph's College, Colombo", R.drawable.folder))
        childItem2.add(ChildItem("Bandaranayake College, Gampaha", R.drawable.folder))
        childItem2.add(ChildItem("Sangamitha Balika Vidyalaya - Galle", R.drawable.folder))
        childItem2.add(ChildItem("Richmond College, Galle", R.drawable.folder))
        childItem2.add(ChildItem("Mahinda College, Galle", R.drawable.folder))

        parentList.add(ParentItem("1st Term (School)",R.drawable.first, childItem2))


        val childItem3 = ArrayList<ChildItem>()
        childItem3.add(ChildItem("Southern province", R.drawable.folder))
        childItem3.add(ChildItem("Western province", R.drawable.folder))
        childItem3.add(ChildItem("North western province", R.drawable.folder))
        childItem3.add(ChildItem("North Central Province", R.drawable.folder))
        childItem3.add(ChildItem("Uva province", R.drawable.folder))
        childItem3.add(ChildItem("Sabaragamuwa Province", R.drawable.folder))
        childItem3.add(ChildItem("Northern Province", R.drawable.folder))
        childItem3.add(ChildItem("Central Province", R.drawable.folder))
        childItem3.add(ChildItem("Eastern Province", R.drawable.folder))


        parentList.add(ParentItem("2nd Term (Provintial)", R.drawable.second, childItem3))


        val childItem4 = ArrayList<ChildItem>()
        childItem4.add(ChildItem("Royal College - Colombo", R.drawable.folder))
        childItem4.add(ChildItem("Ananda College - Colombo", R.drawable.folder))
        childItem4.add(ChildItem("Nalanda College - Colombo", R.drawable.folder))
        childItem4.add(ChildItem("Vishaka Vidyalaya - Colombo", R.drawable.folder))
        childItem4.add(ChildItem("Bishop's College, Colombo", R.drawable.folder))
        childItem4.add(ChildItem("D.S. Senanayake College, Colombo", R.drawable.folder))
        childItem4.add(ChildItem("St.Joseph's College, Colombo", R.drawable.folder))
        childItem4.add(ChildItem("Bandaranayake College, Gampaha", R.drawable.folder))
        childItem4.add(ChildItem("Sangamitha Balika Vidyalaya - Galle", R.drawable.folder))
        childItem4.add(ChildItem("Richmond College, Galle", R.drawable.folder))
        childItem4.add(ChildItem("Mahinda College, Galle", R.drawable.folder))

        parentList.add(ParentItem("2nd Term (School)", R.drawable.second, childItem4))


        val childItem5 = ArrayList<ChildItem>()
        childItem5.add(ChildItem("Southern province", R.drawable.folder))
        childItem5.add(ChildItem("Western province", R.drawable.folder))
        childItem5.add(ChildItem("North western province", R.drawable.folder))
        childItem5.add(ChildItem("North Central Province", R.drawable.folder))
        childItem5.add(ChildItem("Uva province", R.drawable.folder))
        childItem5.add(ChildItem("Sabaragamuwa Province", R.drawable.folder))
        childItem5.add(ChildItem("Northern Province", R.drawable.folder))
        childItem5.add(ChildItem("Central Province", R.drawable.folder))
        childItem5.add(ChildItem("Eastern Province", R.drawable.folder))

        parentList.add(ParentItem("3rd Term (Provincial)", R.drawable.third, childItem5))


        val childItem6 = ArrayList<ChildItem>()
        childItem6.add(ChildItem("Royal College - Colombo", R.drawable.folder))
        childItem6.add(ChildItem("Ananda College - Colombo", R.drawable.folder))
        childItem6.add(ChildItem("Nalanda College - Colombo", R.drawable.folder))
        childItem6.add(ChildItem("Vishaka Vidyalaya - Colombo", R.drawable.folder))
        childItem6.add(ChildItem("Bishop's College, Colombo", R.drawable.folder))
        childItem6.add(ChildItem("D.S. Senanayake College, Colombo", R.drawable.folder))
        childItem6.add(ChildItem("St.Joseph's College, Colombo", R.drawable.folder))
        childItem6.add(ChildItem("Bandaranayake College, Gampaha", R.drawable.folder))
        childItem6.add(ChildItem("Sangamitha Balika Vidyalaya - Galle", R.drawable.folder))
        childItem6.add(ChildItem("Richmond College, Galle", R.drawable.folder))
        childItem6.add(ChildItem("Mahinda College, Galle", R.drawable.folder))
        childItem6.add(ChildItem("Southern province", R.drawable.folder))

        parentList.add(ParentItem("3rd Term (School)", R.drawable.third, childItem6))
    }
}