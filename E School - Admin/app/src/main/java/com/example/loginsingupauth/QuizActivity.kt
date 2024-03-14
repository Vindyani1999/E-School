package com.example.loginsingupauth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginsingupauth.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedGrade = intent.getStringExtra("selectedGrade")
        val selectedSubject = intent.getStringExtra("selectedSubject")

        // Array of grades
        val grades = arrayOf("Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11")

        // Loop through grades
        for (grade in grades) {
            // Check if the selectedGrade matches the current grade in the loop
            if (selectedGrade == grade) {
                // Use a when statement to handle different subjects for the selected grade
                when (grade) {
                    "Grade 11" -> {
                        if (selectedSubject == "Maths") {
                            binding.quizbtn1.setOnClickListener {
                                openWebsite("https://intomath.org/grade-11-math-quizzes/")
                            }
                            binding.quizbtn2.setOnClickListener {
                                openWebsite("https://www.proprofs.com/quiz-school/story.php?title=quiz-for-class-12u2")
                            }
                            binding.quizbtn3.setOnClickListener {
                                openWebsite("https://www.proprofs.com/quiz-school/story.php?title=grade-11-section-11-characteristics--function")
                            }
                            binding.quizbtn4.setOnClickListener {
                                openWebsite("https://www.scribd.com/document/426313814/Gen-Math-Quiz-Bee-Questionnaire-Grade11")
                            }
                            binding.quizbtn5.setOnClickListener {
                                openWebsite("https://quizizz.com/admin/quiz/5dacdcfb2acb49001b2cd3cf/grade-11")
                            }
                        }

                        else if (selectedSubject == "Science") {
                            binding.quizbtn1.setOnClickListener {
                                openWebsite("https://www.google.com")
                            }
                            binding.quizbtn2.setOnClickListener {
                                openWebsite("https://www.facebook.com")
                            }
                            binding.quizbtn3.setOnClickListener {
                                openWebsite("https://www.facebook.com")
                            }
                            binding.quizbtn4.setOnClickListener {
                                openWebsite("https://www.facebook.com")
                            }
                            binding.quizbtn5.setOnClickListener {
                                openWebsite("https://www.facebook.com")
                            }
                        }



                    }

                }

                break
            }
        }
    }

    private fun openWebsite(url: String) {
        // Create an Intent with the action view and the website URL
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        // Check if there's an app to handle this intent before starting it
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // Handle the case where there's no app to handle the intent (e.g., no web browser installed)
            showToast("No app to handle the intent.")
        }
    }

    private fun showToast(message: String) {
        // Example function to show a toast message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
