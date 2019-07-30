package com.ceosilvajr.signme.models

import java.io.Serializable
import java.util.*

/**
 * @author ceosilvajr@gmail.com
 */
data class Pirma(
        var id: String = "",
        var encodedBase64Image: String = "",
        var date: Date = Date()
) : Serializable