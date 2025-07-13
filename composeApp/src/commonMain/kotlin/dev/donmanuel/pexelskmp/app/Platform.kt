package dev.donmanuel.pexelskmp.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun initKoin()