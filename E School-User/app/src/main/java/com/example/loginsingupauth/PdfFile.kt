package com.example.loginsingupauth

data class PdfFile(val fileName: String?, val downloadUrl: String?){
    constructor() : this("","")
}