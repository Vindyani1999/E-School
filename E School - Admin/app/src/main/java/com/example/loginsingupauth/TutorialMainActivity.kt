package com.example.loginsingupauth


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import com.example.loginsingupauth.databinding.ActivityBookPdfMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class TutorialMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookPdfMainBinding
    private var pdfFileUri: Uri? = null
    private lateinit var storageReference: StorageReference
    private lateinit var databaseReference: DatabaseReference
    private var selectedGrade: String? = null
    private var selectedSubject: String? = null
    private var selectedFeature: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookPdfMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        initClickListener()

        selectedGrade = intent.getStringExtra("selectedGrade")
        selectedSubject = intent.getStringExtra("selectedSubject")
        selectedFeature = intent.getStringExtra("selectedFeature")


    }

    private fun init() {
        binding = ActivityBookPdfMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        storageReference = FirebaseStorage.getInstance().reference.child("videos")
        databaseReference = FirebaseDatabase.getInstance().reference.child("videos")
    }

    private fun initClickListener(){

        binding.selectPdfButton.setOnClickListener {
            launcher.launch("video/*")
        }


        binding.uploadBtn.setOnClickListener {
            if (pdfFileUri != null && selectedGrade != null && selectedSubject != null && selectedFeature != null) {
                uploadVideoFileToFirebase(selectedGrade!!, selectedSubject!!, selectedFeature!!)
            } else {
                Toast.makeText(this, "Please select video and subject first", Toast.LENGTH_SHORT).show()
            }
        }
        binding.showAllBtn.setOnClickListener {
            val intent = Intent(this, AllTutorialsActivity::class.java)
            intent.putExtra("selectedGrade", selectedGrade)
            intent.putExtra("selectedSubject", selectedSubject)
            intent.putExtra("selectedFeature", selectedFeature)

            startActivity(intent)
        }

    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        pdfFileUri = uri
        val fileName = uri?.let { DocumentFile.fromSingleUri(this, it)?.name }
        binding.fileName.text = fileName.toString()
    }

    private fun uploadVideoFileToFirebase(grade: String, subject: String, feature: String) {
        val fileName = binding.fileName.text.toString()

        val mStorageRef = storageReference.child("$grade/$subject/$feature/${System.currentTimeMillis()}/$fileName")

        pdfFileUri?.let { uri ->
            mStorageRef.putFile(uri).addOnSuccessListener {
                mStorageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    val pdfFile = PdfFile(fileName, downloadUri.toString())
                    databaseReference.child(grade).child(subject).child(feature).push().key?.let { pushKey ->
                        databaseReference.child(grade).child(subject).child(feature).child(pushKey).setValue(pdfFile)
                            .addOnSuccessListener {
                                pdfFileUri = null
                                binding.fileName.text = resources.getString(R.string.no_pdf_file_selected_yet)
                                Toast.makeText(this, "Uploaded successfully", Toast.LENGTH_SHORT).show()
                                if (binding.progressBar.isShown)
                                    binding.progressBar.visibility = View.GONE
                            }.addOnFailureListener { err ->
                                Toast.makeText(this, err.message.toString(), Toast.LENGTH_SHORT).show()
                                if (binding.progressBar.isShown)
                                    binding.progressBar.visibility = View.GONE
                            }
                    }
                }
            }.addOnProgressListener { uploadTask ->
                val uploadingPercent = uploadTask.bytesTransferred * 100 / uploadTask.totalByteCount
                binding.progressBar.progress = uploadingPercent.toInt()
                if (!binding.progressBar.isShown)
                    binding.progressBar.visibility = View.VISIBLE
            }.addOnFailureListener { err ->
                if (binding.progressBar.isShown)
                    binding.progressBar.visibility = View.GONE

                Toast.makeText(this, err.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}


