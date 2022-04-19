package cherry.technologies.pokemonrest.config

import cherry.technologies.pokemonrest.web.customexception.InternalException
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.ResponseErrorHandler
import java.util.logging.Logger

@Component
class RestResponseErrorConfig: ResponseErrorHandler {

    private val log:Logger = Logger.getLogger(RestResponseErrorConfig::class.toString())

    override fun hasError(response: ClientHttpResponse) =
        response.statusCode.is4xxClientError || response.statusCode.is5xxServerError



    override fun handleError(response: ClientHttpResponse) {
        when {
            response.statusCode.is4xxClientError -> {
                val msg = "Client Error in rest template ${response.body}"
                log.warning(msg)
                throw InternalException(msg)
            }
            response.statusCode.is5xxServerError -> {
                val msg = "Server Error in the rest template ${response.body}"
                log.warning(msg)
                throw InternalException(msg)
            }
        }
    }
}