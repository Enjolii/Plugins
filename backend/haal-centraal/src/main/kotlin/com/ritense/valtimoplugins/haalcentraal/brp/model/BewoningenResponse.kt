package com.ritense.valtimoplugins.haalcentraal.brp.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.ritense.valtimoplugins.haalcentraal.brp.model.Bewoning

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BewoningenResponse(
    val bewoningen: List<Bewoning>? = emptyList()
)
