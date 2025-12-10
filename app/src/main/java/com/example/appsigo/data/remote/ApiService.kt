package com.example.appsigo.data.remote

import com.example.appsigo.data.model.LoginRequest
import com.example.appsigo.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/ws/rest/auth")
    suspend fun loginUser(
        @Body request: LoginRequest
    ): Response<UserResponse> // Usamos Response<T> para manejar el código HTTP
}