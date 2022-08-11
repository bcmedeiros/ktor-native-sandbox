import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

fun main(): Unit = runBlocking {
    println("Starting Ktor...")

    var count = 0L

    val server = embeddedServer(CIO, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            get("/") {
                call.respond(
                    HelloResponse(
                        message = "Hello, world, from Ktor/Native!",
                        count = ++count,
                    )
                )
            }
        }
    }

    server.start()
}

@Serializable
data class HelloResponse(
    val message: String,
    val count: Long,
)