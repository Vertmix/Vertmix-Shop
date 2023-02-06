rootProject.name = "Shop"

listOf(
    "api",
    "common",
    "paper"
).forEach { setupProject("shop-${it.replace("/", "-")}", file(it)) }

fun setupProject(name: String, projectDirectory: File) = setupProject(name) {
    projectDir = projectDirectory
}

inline fun setupProject(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}
