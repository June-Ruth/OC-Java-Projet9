rootProject.name = "Mediscreen"

subdir("application") {
    include("web-app")
    include("patient-microservice")
    include("note-microservice")
    include("report-microservice")
}

subdir("core-libraries") {
    include("models")
}

class IncludeDsl(val root: String) {
    fun include(project: String) {
        settings.include(project)
        settings.project(":$project").also {
            it.projectDir = file("$root/$project")
        }
    }
}

fun subdir(root: String, block: IncludeDsl.() -> Unit) {
    block(IncludeDsl(root))
}
