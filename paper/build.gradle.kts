repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation(project(":shop-api"))
    implementation(project(":shop-common"))
    implementation(fileTree("lib"))

    compileOnly("com.github.MilkBowl:VaultAPI:1.7")

}