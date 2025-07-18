/*
 * Copyright 2015-2025 Ritense BV, the Netherlands.
 *
 * Licensed under EUPL, Version 1.2 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ritense.valtimoplugins.haalcentraal.bag.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Links(
    val self: Link,
    val openbareRuimte: Link?,
    val nummeraanduiding: Link?,
    val woonplaats: Link?,
    val adresseerbaarObject: Link?,
    val panden: List<Link>?
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Link(
    val href: String?,
    val templated: Boolean?,
    val title: String?
)
