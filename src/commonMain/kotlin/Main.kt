import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    println("Starting Ktor...")

    val server = embeddedServer(CIO, port = 8080) {
        routing {
            get("/") {
                call.respond("Hello, world, from Ktor/Native!")
            }
        }
    }

    server.start()
}