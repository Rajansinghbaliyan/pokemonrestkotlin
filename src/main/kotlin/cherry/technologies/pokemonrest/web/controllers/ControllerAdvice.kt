package cherry.technologies.pokemonrest.web.controllers

import cherry.technologies.pokemonrest.web.customexception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

@org.springframework.web.bind.annotation.ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundHandler(e: NotFoundException) = ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(e.message)
}