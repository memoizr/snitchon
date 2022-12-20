package me.snitchon.parameter

import com.snitch.Validator

interface Parameter<RAW : Any?, out PARSED : Any?> {
    val type: Class<*>
    val name: String
    val pattern: Validator<RAW, PARSED>
    val description: String
    val required: Boolean
    val emptyAsMissing: Boolean
    val invalidAsMissing: Boolean
}