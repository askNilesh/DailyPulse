package com.asknilesh.dailypulse

expect class PlatForm {
    val osName: String
    val osVersion: String
    val deviceModel: String
    val density: Int

    fun logSystemInfo()
}

