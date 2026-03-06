package com.daya.homemadepro.data.modal

data class LoginResponse(
    val countryCode: String,
    val deviceId: String,
    val password: String,
    val phoneNumber: String
)