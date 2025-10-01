package com.example.businessservice.config;

import com.example.businessservice.exception.BadRequestException;
import com.example.businessservice.exception.DataServiceException;
import com.example.businessservice.exception.ResourceNotFoundException;
import com.example.businessservice.exception.ServiceUnavailableException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Configuration class for Feign clients.
 */
@Configuration
public class FeignConfig {

    private static final Logger log = LoggerFactory.getLogger(FeignConfig.class);

    /**
     * Creates a custom error decoder for Feign clients.
     *
     * @return the custom error decoder
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    /**
     * Custom error decoder that translates Feign exceptions into application-specific exceptions.
     */
    public static class CustomErrorDecoder implements ErrorDecoder {

        private final ErrorDecoder defaultErrorDecoder = new Default();

        @Override
        public Exception decode(String methodKey, Response response) {
            String requestUrl = response.request().url();
            HttpStatus responseStatus = HttpStatus.valueOf(response.status());
            String responseBody = getResponseBody(response);

            log.error("Error calling data service: {} - Status: {} - Body: {}", 
                    requestUrl, responseStatus, responseBody);

            switch (responseStatus) {
                case NOT_FOUND:
                    return new ResourceNotFoundException(
                            "Resource not found in data service: " + requestUrl, 
                            new Exception(responseBody));
                case BAD_REQUEST:
                    return new BadRequestException(
                            "Invalid request to data service: " + responseBody, 
                            new Exception(responseBody));
                case SERVICE_UNAVAILABLE:
                    return new ServiceUnavailableException(
                            "Data service is unavailable: " + requestUrl, 
                            new Exception(responseBody));
                default:
                    if (responseStatus.is5xxServerError()) {
                        return new ServiceUnavailableException(
                                "Data service error: " + responseStatus.value() + " - " + requestUrl, 
                                new Exception(responseBody));
                    } else if (responseStatus.is4xxClientError()) {
                        return new DataServiceException(
                                "Client error when calling data service: " + responseStatus.value() + " - " + responseBody, 
                                new Exception(responseBody), 
                                responseStatus);
                    }
                    return defaultErrorDecoder.decode(methodKey, response);
            }
        }

        private String getResponseBody(Response response) {
            try (InputStream bodyIs = response.body().asInputStream()) {
                byte[] bodyBytes = bodyIs.readAllBytes();
                return new String(bodyBytes, StandardCharsets.UTF_8);
            } catch (IOException e) {
                log.error("Error reading response body", e);
                return "Could not read response body";
            }
        }
    }
}