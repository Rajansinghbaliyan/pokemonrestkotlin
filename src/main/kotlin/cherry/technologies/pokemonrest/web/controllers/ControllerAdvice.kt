package cherry.technologies.pokemonrest.web.controllers

import cherry.technologies.pokemonrest.web.customexception.BadRequestException
import cherry.technologies.pokemonrest.web.customexception.InternalException
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

    @ExceptionHandler(value = [BadRequestException::class])
    fun badRequestHandler(e: BadRequestException) = ResponseEntity
        .badRequest()
        .body(e.message)

    @ExceptionHandler(value = [InternalException::class])
    fun internalErrorHandler(e: InternalException) = ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(e.message)

    @ExceptionHandler(value = [java.lang.Exception::class])
    fun globalExceptionHandler(e: Exception) = ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(e.message)


}