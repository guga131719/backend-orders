package com.challenge.backend.integration;

import com.challenge.backend.integration.domain.Product;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductApi {

    private final String USER_MICROSERVICE_URL = "https://fakestoreapi.com/products/"; // Substitua pela URL real do microserviço de usuários

    public Product getProductById(Long userId) {
        String url = USER_MICROSERVICE_URL+userId.toString();
        Product product=new Product();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);


            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                Gson gson = new Gson();
                Product productGson = gson.fromJson(responseBody, Product.class);
                product=productGson;

                System.out.println("Status Code: " + statusCode);
                System.out.println("Response Body: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }
}
