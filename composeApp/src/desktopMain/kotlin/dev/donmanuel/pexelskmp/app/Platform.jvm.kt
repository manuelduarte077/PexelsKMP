package dev.donmanuel.pexelskmp.app

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun initKoin() {
    initKoinCommon()
}