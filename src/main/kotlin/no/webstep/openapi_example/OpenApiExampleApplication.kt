package no.webstep.openapi_example

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class OpenApiExampleApplication

fun main(args: Array<String>) {
    runApplication<OpenApiExampleApplication>(*args)
}
