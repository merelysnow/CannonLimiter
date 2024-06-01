plugins {
    id("java")

    id("com.github.johnrengelman.shadow") version ("8.1.1")
}

group = "com.merelysnow"
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
    maven(url = "https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation("com.github.SaiintBrisson.command-framework:bukkit:1.3.1")
    compileOnly("org.jetbrains:annotations:23.0.0")
    compileOnly("org.github.paperspigot:paperspigot-api:1.8.8-R0.1-SNAPSHOT")

    // https://mvnrepository.com/artifact/com.github.ben-manes.caffeine/caffeine
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")

    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
}


tasks {
    javadoc {
        options.encoding = "UTF-8"
    }
    compileJava {
        options.encoding = "UTF-8"
    }
    compileTestJava {
        options.encoding = "UTF-8"
    }
}

tasks.withType<Jar> {
    destinationDirectory = File("\\\\wsl.localhost\\Ubuntu-22.04\\home\\celoz\\minecraft\\server\\plugins")
}

tasks.test {
    useJUnitPlatform()
}


