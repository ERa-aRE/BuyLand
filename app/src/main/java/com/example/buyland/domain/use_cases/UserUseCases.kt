package com.example.buyland.domain.use_cases

data class UserUseCases(
    val getIdByName: GetIdByName,
    val getUserById: GetUserById,
    val getPasswordById: GetPasswordById,
    val checkUserName: CheckUserName,
    val insertUser: InsertUser,
    val updateUser: UpdateUser,
)
