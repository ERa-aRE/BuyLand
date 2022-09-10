package com.example.buyland.presentation.comments

import com.example.buyland.domain.model.Comment

data class CommentsState(
    val commentsItems:List<Comment> = emptyList()

)
