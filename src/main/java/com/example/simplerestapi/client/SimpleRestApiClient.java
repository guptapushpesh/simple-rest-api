package com.example.simplerestapi.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SimpleRestApiClient {

    public static void main(String[] args) {
        callGET();
        callPOST();
        //callPOSTJSON();
    }

    private static void callGET(){
        try {
            String url = "http://localhost:8080/random";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print result
            System.out.println("Response: " + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void callPOST(){
        try {
            String url = "http://localhost:8080/save";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String name = "apple";
            StringBuilder params = new StringBuilder();
            params.append("item=").append(URLEncoder.encode(name, StandardCharsets.UTF_8));

            // Construct the full URL
            String fullUrl = url + "?" + params.toString();

            // Log the full URL
            System.out.println("Sending POST request to URL: " + fullUrl);
            System.out.println("Request Parameters: " + params.toString());

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = params.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void callPOSTJSON(){
        try {
            String url = "http://localhost:8080/save";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonInputString = "{\"name\": \"apple\"}";

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
