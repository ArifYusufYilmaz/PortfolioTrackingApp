package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.collectApiDtos.CollectApiBistDataDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BistApiImpl {

    private String BIST_API_URL = "https://api.collectapi.com/economy/liveBorsa" ;
    private String COLLECT_API_KEY = "apikey 3IfBBGrDMaePGc1vO6NQ9b:0p3eYEzwFyZE9XCWDXQxt7" ;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BistApiImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "bistdatas")
    public CollectApiBistDataDto getBistApiResponse(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization" , COLLECT_API_KEY);

        HttpEntity request = new HttpEntity(headers);
        //org.springframework.http.HttpEntity<String> requestEntity = new org.springframework.http.HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(BIST_API_URL, HttpMethod.GET,request ,String.class);
        String jsonResponse = response.getBody();
        System.out.println("json response : " + jsonResponse);
        try {
            return objectMapper.readValue(response.getBody(), CollectApiBistDataDto.class);
        } catch (JsonProcessingException e) {
            System.err.println("Error while deserializing JSON: " + jsonResponse);

            throw new RuntimeException(e);
        }

    }

}
