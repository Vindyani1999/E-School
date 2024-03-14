package com.example.loginsingupauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FeaturesActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var recyclerViewFeaturesAdapterAdapter: RecyclerViewFeaturesAdapter? = null
    private var FeaturesList = mutableListOf<Features_DataClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_features)

        FeaturesList = ArrayList()
        recyclerView = findViewById<View>(R.id.rvFeatures) as RecyclerView
        val selectedGrade = intent.getStringExtra("selectedGrade")
        val selectedSubject = intent.getStringExtra("selectedSubject")

        recyclerViewFeaturesAdapterAdapter = RecyclerViewFeaturesAdapter(this@FeaturesActivity, selectedGrade, selectedSubject, FeaturesList)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,1)
        recyclerView!!.layoutManager=layoutManager
        recyclerView!!.adapter = recyclerViewFeaturesAdapterAdapter

        prepareFeaturesListData()

    }

    private fun prepareFeaturesListData(){
        var Features = Features_DataClass("TEXT BOOK",R.drawable.textbook)
        FeaturesList.add(Features)
        Features = Features_DataClass("PAPERS",R.drawable.papers)
        FeaturesList.add(Features)
        Features = Features_DataClass("UNIT TEST",R.drawable.unit)
        FeaturesList.add(Features)
        Features = Features_DataClass("QUIZ",R.drawable.quiz)
        FeaturesList.add(Features)
        Features = Features_DataClass("TUTORIAL",R.drawable.video)
        FeaturesList.add(Features)

        recyclerViewFeaturesAdapterAdapter!!.notifyDataSetChanged()
    }





}