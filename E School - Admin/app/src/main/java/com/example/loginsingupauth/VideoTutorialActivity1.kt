package com.example.loginsingupauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.loginsingupauth.databinding.ActivityVideoTutorialBinding

class VideoTutorialActivity1 : AppCompatActivity() {
    private lateinit var binding: ActivityVideoTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedGrade = intent.getStringExtra("selectedGrade")
        val selectedSubject = intent.getStringExtra("selectedSubject")

        val grades = arrayOf("Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11")

        for (grade in grades) {
            // Check if the selectedGrade matches the current grade in the loop
            if (selectedGrade == grade) {
                // Use a when statement to handle different subjects for the selected grade
                when (grade) {
                    "Grade 11" -> {
                        if (selectedSubject == "Maths") {
                            setWebViewContent(binding.webView1, "https://www.youtube.com/embed/-mMBQGn3lOg?si=DbD9WKZTXjBm50gB")
                            setWebViewContent(binding.webView2, "https://www.youtube.com/embed/1GwJU-TaetA?si=NSKy-JfQcnQmO9DL" )
                            setWebViewContent(binding.webView3, "https://www.youtube.com/embed/y1NmNzxEILA?si=sQOPghzCJcl2ff1U") // Replace with another video URL
                            setWebViewContent(binding.webView4, "https://www.youtube.com/embed/PAxQQCerY6I?si=GQVWwK-KztiPIbui") // Replace with another video URL
                        }
                    }



                }
            }
        }
    }

    private fun setWebViewContent(webView: WebView, videoUrl: String) {
        val videoHtml = """
            <html>
                <head>
                    <style>
                        body { margin: 0; padding: 0; }
                    </style>
                </head>
                <body>
                    <iframe
                        width="100%"
                        height="100%"
                        src="$videoUrl"
                        title="YouTube video player"
                        frameborder="0"
                        allowfullscreen
                    ></iframe>
                </body>
            </html>
        """.trimIndent()

        webView.loadData(videoHtml, "text/html", "utf-8")
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
    }
}
