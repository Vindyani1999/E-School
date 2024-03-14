package com.example.loginsingupauth

data class ParentItem(
    val title : String,
    val image : Int ,
    val childItemList : ArrayList<ChildItem>,
    var isExpandable : Boolean = false
)
