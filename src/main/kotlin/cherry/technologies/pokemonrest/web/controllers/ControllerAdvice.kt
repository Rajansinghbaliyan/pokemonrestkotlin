package cherry.technologies.pokemonrest.web.controllers

import cherry.technologies.pokemonrest.web.customexception.BadRequestException
import cherry.technologies.pokemonrest.web.customexception.InternalException
import cherry.technologies.pokemonrest.web.customexception.NotFoundException
import cherry.technologies.pokemonrest.web.utils.responseBadRequest
import cherry.technologies.pokemonrest.web.utils.responseInternalError
import cherry.technologies.pokemonrest.web.utils.responseNotFound
import org.springframework.web.bind.annotation.ExceptionHandler

@org.springframework.web.bind.annotation.ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundHandler(e: NotFoundException) = ErrorMessage(e.message).responseNotFound()

    @ExceptionHandler(value = [BadRequestException::class])
    fun badRequestHandler(e: BadRequestException) = ErrorMessage(e.message).responseBadRequest()

    @ExceptionHandler(value = [InternalException::class])
    fun internalErrorHandler(e: InternalException) = ErrorMessage(e.message).responseInternalError()

    @ExceptionHandler(value = [java.lang.Exception::class])
    fun globalExceptionHandler(e: Exception) = ErrorMessage(e.message,e.stackTraceToString()).responseInternalError(e.message)


}

class ErrorMessage(val message:String?,val stackTrace:String? = null)
