package ru.tinkoff.kotlin.group

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

fun Application.groupModel() {
    val service: GroupService by closestDI().instance()

    routing {
        route("/groups") {
            get {
                call.respond(service.findAll())
            }
            get("/{groupNumber}") {
                val groupNumber = call.parameters["groupNumber"]
                if (groupNumber != null) {
                    call.respond(service.findStudentsInGroup(groupNumber.toInt()))
                }
            }
            post {
                val request = call.receive<CreateGroupRequest>()
                call.respond(service.create(request.number))
            }
            put("/{id}") {
                val request = call.receive<UpdateGroupRequest>()
                val groupId = call.parameters["id"]
                if (groupId != null) {
                    call.respond(service.update(groupId.toInt(), request.number))
                }
            }
            delete("/{id}") {
                val groupId = call.parameters["id"]
                if (groupId != null) {
                    call.respond(service.delete(groupId.toInt()))
                }
            }
        }
    }
}

fun DI.Builder.groupComponents() {
    bind<GroupDao>() with singleton { GroupDao(instance()) }
    bind<GroupService>() with singleton { GroupService(instance(), instance()) }
}

@Serializable
private data class CreateGroupRequest(val number: Int)

@Serializable
private data class UpdateGroupRequest(val number: Int)
