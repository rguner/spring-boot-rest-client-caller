package com.example.springbootrestclientcaller.client.personservicedifferenttimeout;

import com.example.springbootrestclientcaller.client.HttpLoggingInterceptor;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class PersonServiceDifferentTimeoutHttpClientConfig {


    @Value("${person.service.host.url:http://localhost:8080}")
    private String personServiceHostUrl;

    @Value("${person.service.host.username:user}")
    private String personServiceHostUsername;

    @Value("${person.service.host.password:pass}")
    private String personServiceHostPassword;

    @Value("${person.service.ws.socket.timeout:10000}")
    private long personServiceWsSocketTimeout;

    Map<Long, PersonServiceDifferentTimeoutRestClient> restClientMap = new ConcurrentHashMap<>();

    private CloseableHttpClient createHttpClient(long timeout) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(timeout))
                .setResponseTimeout(Timeout.ofMilliseconds(timeout))
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultSocketConfig(
                SocketConfig.custom()
                        .setSoTimeout(Timeout.ofMilliseconds(personServiceWsSocketTimeout))
                        .build()
        );

        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    private HttpServiceProxyFactory createProxyFactory(long timeout) {
        RestClient restClient = RestClient.builder()
                .baseUrl(personServiceHostUrl)
                .requestFactory(new HttpComponentsClientHttpRequestFactory(createHttpClient(timeout)))
                .requestInterceptors(consumerList -> {
                    consumerList.add(new BasicAuthenticationInterceptor(personServiceHostUsername, personServiceHostPassword));
                    consumerList.add(new HttpLoggingInterceptor());
                })
                .build();

        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
    }


    public PersonServiceDifferentTimeoutRestClient getPersonServiceRestClient(long timeout) {
        return restClientMap.computeIfAbsent(timeout,
                k -> createProxyFactory(timeout).createClient(PersonServiceDifferentTimeoutRestClient.class));
    }
}