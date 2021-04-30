package ru.tinkoff.kotlin.plugins

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.singleton
import ru.tinkoff.kotlin.student.StudentDao
import ru.tinkoff.kotlin.student.StudentService

fun Application.studentModule() {
    val service: StudentService by closestDI().instance()

    routing {
        route("/students") {
            get {
                call.respond(service.findAll())
            }
            post {
                val request = call.receive<CreateStudentRequest>()
                call.respond(service.create(request.name, request.groupNumber))
            }
            put("/{id}") {
                val request = call.receive<UpdateStudentRequest>()
                val studentId = call.parameters["id"]
                if (studentId != null) {
                    call.respond(service.update(studentId.toInt(), request.name, request.groupNumber))
                }
            }
            delete("/{id}") {
                val studentId = call.parameters["id"]
                if (studentId != null) {
                    call.respond(service.delete(studentId.toInt()))
                }
            }
        }
    }
}

fun DI.Builder.studentComponents() {
    bind<StudentDao>() with singleton { StudentDao(instance()) }
    bind<StudentService>() with singleton { StudentService(instance(), instance()) }
}

@Serializable
private data class CreateStudentRequest(val name: String, val groupNumber: Int)

@Serializable
private data class UpdateStudentRequest(val name: String, val groupNumber: Int)
