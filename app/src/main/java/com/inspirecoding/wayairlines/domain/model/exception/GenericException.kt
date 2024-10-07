package com.inspirecoding.wayairlines.domain.model.exception

data class GenericException(
    val title: String? = null,
    val message: String? = null,
    val code: String? = null
)
