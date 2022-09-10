package com.example.buyland.data.remote.dto

import com.example.buyland.domain.model.Comment


data class CommentDtoObject(
    val DateCreate: String,
    val NumRank: Int,
    val TextNazar: String,
    val Type_Erore: String,
    val User_Name: String
){
    fun toComment(): Comment {
        return Comment(DateCreate,NumRank,TextNazar,User_Name)
    }
}