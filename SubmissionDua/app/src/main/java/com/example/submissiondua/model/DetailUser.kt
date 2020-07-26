package com.example.submissiondua.model

data class DetailUser(
    var username: String,
    var avatar: String,
    var name: String,
    var company: String,
    var location: String,
    var bio: String,
    var countRepo: Int,
    var countFollowers: Int,
    var countFollowing: Int
)

data class Repository(
    var fullName: String,
    var description: String,
    var createdAt: String,
    var language: String
)