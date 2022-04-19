package cherry.technologies.pokemonrest.web.utils

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

fun <T> T.responseOk(): ResponseEntity<T> = ResponseEntity
    .ok()
    .contentType(MediaType.APPLICATION_JSON)
    .body(this)

fun <T> T.responseCreated(): ResponseEntity<T> = ResponseEntity
    .status(HttpStatus.CREATED)
    .contentType(MediaType.APPLICATION_JSON)
    .body(this)

fun <T> T.responseBadRequest(): ResponseEntity<T> = ResponseEntity
    .status(HttpStatus.BAD_REQUEST)
    .contentType(MediaType.APPLICATION_JSON)
    .body(this)

fun <T> T.responseNotFound(): ResponseEntity<T> = ResponseEntity
    .status(HttpStatus.NOT_FOUND)
    .contentType(MediaType.APPLICATION_JSON)
    .body(this)