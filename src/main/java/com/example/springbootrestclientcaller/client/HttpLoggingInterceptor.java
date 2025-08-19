package com.example.springbootrestclientcaller.client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@Slf4j
public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);

        ClientHttpResponse response = execution.execute(request, body);

        logResponse(request, response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) {
        log.info("--> [Request] {} {} | [Headers] {} | [Body] {}", request.getMethod(), request.getURI(), request.getHeaders(),
                new String(body, StandardCharsets.UTF_8));
    }

    private void logResponse(HttpRequest request, ClientHttpResponse response) throws IOException {
        log.info("<-- [Response] Status: {}  [{}]", response.getStatusCode(), request.getURI());
    }

}