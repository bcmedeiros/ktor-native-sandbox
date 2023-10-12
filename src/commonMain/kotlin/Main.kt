import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

fun main(): Unit = runBlocking {
    println("Starting Ktor...")

    val server = embeddedServer(CIO, port = 8080, module = {
        install(ContentNegotiation) {
            json()
        }
        install(Sessions) {
            cookie<UserData>("user_data")
        }
        routing {
            get("/") {
                val userData = call.sessions.get() ?: UserData()
                val newUserData = userData.copy(count = userData.count + 1)
                call.sessions.set(newUserData)
                call.respond(
                    HelloResponse(
                        message = "Hello, world, from Ktor/Native!",
                        count = newUserData.count,
                    )
                )
            }
        }
    })

    server.start()
}

@Serializable
data class UserData(
    val count: Long = 0,
)

@Serializable
data class HelloResponse(
    val message: String,
    val count: Long,
)