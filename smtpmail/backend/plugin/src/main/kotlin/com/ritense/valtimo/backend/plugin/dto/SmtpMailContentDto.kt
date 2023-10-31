package com.ritense.valtimo.backend.plugin.dto

import org.springframework.core.io.InputStreamSource

data class SmtpMailContentDto(
    val mailMessage: String,
    val attachments: List<Attachment>
) {

    data class Attachment(
        val fileName: String,
        val file: InputStreamSource
    )
}