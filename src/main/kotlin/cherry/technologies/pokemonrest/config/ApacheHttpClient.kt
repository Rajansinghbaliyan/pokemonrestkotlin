package cherry.technologies.pokemonrest.config

import org.apache.http.client.config.RequestConfig
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateCustomizer
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class ApacheHttpClient @Autowired constructor(
    @param:Value("\${maxconnection}") private val MAX_CONNECTION: Int,
    @param:Value("\${maxconnectionperroute}") private val MAX_PER_ROUTE: Int,
    @param:Value("\${sockettimeout}") private val SOCKET_TIMEOUT: Int,
    @param:Value("\${requesttimeout}") private val REQUEST_TIMEOUT: Int
) :
    RestTemplateCustomizer {
    fun clientHttpRequestFactory(): ClientHttpRequestFactory {
        val manager = PoolingHttpClientConnectionManager()
        manager.maxTotal = MAX_CONNECTION
        manager.defaultMaxPerRoute = MAX_PER_ROUTE
        val requestConfig = RequestConfig
            .custom()
            .setSocketTimeout(SOCKET_TIMEOUT)
            .setConnectionRequestTimeout(REQUEST_TIMEOUT)
            .build()
        val closeableHttpClient = HttpClients
            .custom()
            .setConnectionManager(manager)
            .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy())
            .setDefaultRequestConfig(requestConfig)
            .build()
        return HttpComponentsClientHttpRequestFactory(closeableHttpClient)
    }

    override fun customize(restTemplate: RestTemplate) {
        restTemplate.requestFactory = clientHttpRequestFactory()
    }
}
