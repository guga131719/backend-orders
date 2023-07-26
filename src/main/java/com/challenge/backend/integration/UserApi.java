package com.challenge.backend.integration;

import com.challenge.backend.integration.domain.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;



@Service
public class UserApi {

    private final String USER_MICROSERVICE_URL = "https://fakestoreapi.com/users/"; // Substitua pela URL real do microserviço de usuários

    public User getUserById(Long userId) {
        String url = USER_MICROSERVICE_URL+userId.toString();
        User user=new User();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);


            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                Gson gson = new Gson();
                User userGson = gson.fromJson(responseBody, User.class);
                user=userGson;

                System.out.println("Status Code: " + statusCode);
                System.out.println("Response Body: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}