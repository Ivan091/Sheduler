plugins {
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.boot") version "2.5.5"
    java
}
repositories {
    mavenCentral()
}
dependencyManagement {
    imports {
        mavenBom("org.springframework:spring-framework-bom:5.3.10")
        mavenBom("org.springframework.boot:spring-boot-dependencies:2.5.5")
    }
    dependencies {
        dependencySet("io.springfox:3.0.0") {
            entry("springfox-swagger-ui")
            entry("springfox-boot-starter")
        }
        dependencySet("com.fasterxml.jackson.core:2.12.4") {
            entry("jackson-annotations")
            entry("jackson-databind")
        }
        dependencySet("org.mockito:3.12.4") {
            entry("mockito-core")
            entry("mockito-junit-jupiter")
        }
        dependency("org.apache.commons:commons-lang3:3.12.0")
        dependency("org.postgresql:postgresql:42.2.24")
        dependency("org.junit.jupiter:junit-jupiter-engine:5.8.0")
    }
}
dependencies {
    implementation("org.springframework:spring-beans")
    implementation("org.springframework:spring-core")
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-core")
    implementation("org.springframework.data:spring-data-jpa")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.apache.commons:commons-lang3")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
}
tasks.test {
    useJUnitPlatform()
}