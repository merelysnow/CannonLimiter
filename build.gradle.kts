plugins {
    id("java")

    id("com.github.johnrengelman.shadow") version ("8.1.1")
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven(url = "https://jitpack.io")
    maven(url = "https://repo.nickuc.com/maven2/")
    maven(url = "https://repo.nickuc.com/maven-releases/")
    maven(url = "https://repo.dmulloy2.net/repository/public/")
    maven(url = "https://repo.bg-software.com/repository/nms/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
    maven(url = "https://oss.sonatype.org/content/repositories/snapshot")
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url = "https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    implementation("com.github.SaiintBrisson.command-framework:bukkit:1.3.1")
    implementation("com.github.DevNatan.inventory-framework:inventory-framework:6f6a634cc5478a16ff81773ed80187dde27d6edd")
    compileOnly("org.jetbrains:annotations:23.0.0")

    compileOnly(fileTree("libs"))

    // https://mvnrepository.com/artifact/com.github.ben-manes.caffeine/caffeine
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")

    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
}

tasks.test {
    useJUnitPlatform()
}

bukkit {
    name = "CannonLimiter"
    main = "com.factions.cannonlimiter.CannonLimiterPlugin"
    version = "1.0.0"
    depend = listOf()
}