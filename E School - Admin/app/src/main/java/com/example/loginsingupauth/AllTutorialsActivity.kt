package com.example.loginsingupauth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginsingupauth.databinding.ActivityAllTutorialsBinding
import com.google.firebase.database.*

class AllTutorialsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllTutorialsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var allVideoListAdapter: VideoListAdapter
    private val allVideoList: MutableList<VideoFile> = mutableListOf()
    private lateinit var databaseReference: DatabaseReference

    private var selectedGrade: String? = null
    private var selectedSubject: String? = null
    private var selectedFeature: String? = null

    private var parentItemTitle: String? = null
    private var childItemTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllTutorialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.allTutorialsRecyclerView
        allVideoListAdapter = VideoListAdapter(allVideoList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = allVideoListAdapter

        databaseReference = FirebaseDatabase.getInstance().reference.child("videos")

        selectedGrade = intent.getStringExtra("selectedGrade")
        selectedSubject = intent.getStringExtra("selectedSubject")
        selectedFeature = intent.getStringExtra("selectedFeature")
        parentItemTitle = intent.getStringExtra("parentItemTitle")
        childItemTitle = intent.getStringExtra("childItemTitle")

        loadAllVideos(selectedGrade, selectedSubject, selectedFeature)
    }

    private fun loadAllVideos(grade: String?, subject: String?, feature: String?) {
        allVideoList.clear()
        allVideoListAdapter.notifyDataSetChanged()

        if (grade != null && subject != null && feature != null) {
            // Query Firebase for all videos
            val query: Query = databaseReference.child(grade).child(subject).child(feature)
            query.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (videoSnapshot in dataSnapshot.children) {
                        val videoFile = videoSnapshot.getValue(VideoFile::class.java)
                        videoFile?.let { allVideoList.add(it) }
                    }
                    allVideoListAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        }
    }
}
