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


class BookPdfMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookPdfMainBinding
    private var pdfFileUri: Uri? = null
    private lateinit var storageReference: StorageReference
    private lateinit var databaseReference: DatabaseReference
    private var selectedGrade: String? = null
    private var selectedSubject: String? = null
    private var selectedFeature: String? = null
    private var parentItemTitle: String? = null
    private var childItemTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookPdfMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        initClickListener()

        selectedGrade = intent.getStringExtra("selectedGrade")
        selectedSubject = intent.getStringExtra("selectedSubject")
        selectedFeature = intent.getStringExtra("selectedFeature")
        parentItemTitle = intent.getStringExtra("parentItemTitle")
        childItemTitle = intent.getStringExtra("childItemTitle")

        if (selectedFeature == "TEXT BOOK" || selectedFeature == "UNIT TEST"  || selectedFeature == "QUIZ") {

            Toast.makeText(this, "Selected Grade: $selectedGrade", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Selected Subject: $selectedSubject", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Selected Feature: $selectedFeature", Toast.LENGTH_SHORT).show()
        }

        else if (selectedFeature == "PAPERS") {

            Toast.makeText(this, "Selected Grade: $selectedGrade", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Selected Subject: $selectedSubject", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Selected Feature: $selectedFeature", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Selected Parent Item: $parentItemTitle", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Selected Child Item: $childItemTitle", Toast.LENGTH_SHORT).show()
        }

    }

    private fun init() {
        binding = ActivityBookPdfMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        storageReference = FirebaseStorage.getInstance().reference.child("pdfs")
        databaseReference = FirebaseDatabase.getInstance().reference.child("pdfs")
    }

    private fun initClickListener(){

        binding.selectPdfButton.setOnClickListener {
            launcher.launch("application/pdf")
        }

        binding.uploadBtn.setOnClickListener {
            if (selectedFeature == "TEXT BOOK") {
                if (pdfFileUri != null && selectedGrade != null && selectedSubject != null && selectedFeature != null) {
                    uploadBookPdfFileToFirebase(selectedGrade!!, selectedSubject!!, selectedFeature!!)
                } else {
                    Toast.makeText(this, "Please select Text Book pdf...!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            else if (selectedFeature == "UNIT TEST") {
                if (pdfFileUri != null && selectedGrade != null && selectedSubject != null && selectedFeature != null) {
                    uploadBookPdfFileToFirebase(selectedGrade!!, selectedSubject!!, selectedFeature!!)
                } else {
                    Toast.makeText(this, "Please select UNIT TEST pdf...!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            else if (selectedFeature == "QUIZ") {
                if (pdfFileUri != null && selectedGrade != null && selectedSubject != null && selectedFeature != null) {
                    uploadBookPdfFileToFirebase(selectedGrade!!, selectedSubject!!, selectedFeature!!)
                } else {
                    Toast.makeText(this, "Please select QUIZ pdf...!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            else if (selectedFeature == "PAPERS") {
                if (pdfFileUri != null && selectedGrade != null && selectedSubject != null && selectedFeature != null && parentItemTitle != null && childItemTitle != null) {
                    uploadPaperPdfFileToFirebase(selectedGrade!!, selectedSubject!!, selectedFeature!!, parentItemTitle!!, childItemTitle!! )
                } else {
                    Toast.makeText(this, "Please select paper pdf...!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            else{
                Toast.makeText(this, "Please select pdf...!", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        binding.showAllBtn.setOnClickListener {
            val intent = Intent(this, AllPdfsActivity::class.java)
            intent.putExtra("selectedGrade", selectedGrade)
            intent.putExtra("selectedSubject", selectedSubject)
            intent.putExtra("selectedFeature", selectedFeature)
            intent.putExtra("parentItemTitle", parentItemTitle)
            intent.putExtra("childItemTitle", childItemTitle)

            startActivity(intent)
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        pdfFileUri = uri
        val fileName = uri?.let { DocumentFile.fromSingleUri(this, it)?.name }
        binding.fileName.text = fileName.toString()
    }

    private fun uploadBookPdfFileToFirebase(grade: String, subject: String, feature: String) {
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
                                Toast.makeText(this, "Uploaded successfully!", Toast.LENGTH_SHORT).show()
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


    private fun uploadPaperPdfFileToFirebase(grade: String, subject: String, feature: String, parentItemTitle: String, childItemTitle: String) {
        val fileName = binding.fileName.text.toString()
        val mStorageRef = storageReference.child("$grade/$subject/$feature/$parentItemTitle/$childItemTitle/${System.currentTimeMillis()}/$fileName")

        pdfFileUri?.let { uri ->
            mStorageRef.putFile(uri).addOnSuccessListener {
                mStorageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    val pdfFile = PdfFile(fileName, downloadUri.toString())
                    databaseReference.child(grade).child(subject).child(feature).child(parentItemTitle).child(childItemTitle).push().key?.let { pushKey ->
                        databaseReference.child(grade).child(subject).child(feature).child(parentItemTitle).child(childItemTitle).child(pushKey).setValue(pdfFile)
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


