package dev.donmanuel.pexelskmp.pexelskmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform