package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonFetcher {
    static String firstName;
    static String lastName;
    static String postalCode;


    public void jsonFetcher() throws IOException, InterruptedException {

        // Create HttpClient instance
        HttpClient client = HttpClient.newHttpClient();

        // Create HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .GET() // Specify HTTP GET
                .build();
        // Fetch the data
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode usersArray = objectMapper.readTree(response.body());

        // Extract Name and Postal Code from the first object
        JsonNode firstUser = usersArray.get(0); // Get the first user
        String fullName = firstUser.get("name").asText();
        postalCode = firstUser.get("address").get("zipcode").asText();

        // Split Name into First and Last Name
        String[] nameParts = fullName.split(" ", 2); // Split on the first space

        firstName = nameParts[0];
        lastName = nameParts.length > 1 ? nameParts[1] : ""; // Handle case where there is no last name

    }

}