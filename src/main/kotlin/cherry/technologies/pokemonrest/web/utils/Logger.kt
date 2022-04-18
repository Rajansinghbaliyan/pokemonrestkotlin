package cherry.technologies.pokemonrest.web.utils

import java.util.logging.Logger

fun <T> T.logInfo(log: Logger,msg: String): T {
    log.info(msg)
    return this
}