package com.efedaniel.bloodfinder.bloodfinder.apis

import com.efedaniel.bloodfinder.bloodfinder.models.request.NotificationRequest
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NotificationApiService {

    @POST("/fcm/send")
    suspend fun sendNotification(@Body notificationRequest: NotificationRequest): Response<JsonElement>

}