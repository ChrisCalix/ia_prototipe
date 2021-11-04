package com.dukena.myapplication.models.request

data class TransactionRequest(
    val cardNumber: Long,
    val country_code: String,
    val transaction_include: Boolean
)