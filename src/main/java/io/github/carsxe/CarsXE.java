package io.github.carsxe;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CarsXE {
    private final String apiKey;

    public CarsXE(String apiKey) {
        this.apiKey = apiKey;
    }

    private String getBaseUrl() {
        return "https://api.carsxe.com";
    }

    private String buildUrl(String endpoint, Map<String, String> params) throws Exception {
        StringBuilder urlBuilder = new StringBuilder(getBaseUrl() + "/" + endpoint + "?key=" + apiKey + "&source=java");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                urlBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        return urlBuilder.toString();
    }

    private Map<String, Object> fetch(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        BufferedReader reader;
        if (responseCode == 200) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Convert JSON response to Map using Jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.toString(),
                new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {
                });
    }

    private Map<String, Object> post(String urlString, String jsonBody, Map<String, String> headers) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        // Add custom headers
        for (Map.Entry<String, String> header : headers.entrySet()) {
            conn.setRequestProperty(header.getKey(), header.getValue());
        }

        conn.setDoOutput(true);
        conn.getOutputStream().write(jsonBody.getBytes("UTF-8"));

        int responseCode = conn.getResponseCode();
        BufferedReader reader;
        if (responseCode == 200) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Convert JSON response to Map using Jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.toString(),
                new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {
                });
    }

    public Map<String, Object> specs(Map<String, String> params) throws Exception {
        String url = buildUrl("specs", params);
        return fetch(url);
    }

    public Map<String, Object> marketvalue(Map<String, String> params) throws Exception {
        String url = buildUrl("v2/marketvalue", params);
        return fetch(url);
    }

    public Map<String, Object> history(Map<String, String> params) throws Exception {
        String url = buildUrl("history", params);
        return fetch(url);
    }

    public Map<String, Object> recalls(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/recalls", params);
        return fetch(url);
    }

    public Map<String, Object> internationalVinDecoder(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/international-vin-decoder", params);
        return fetch(url);
    }

    public Map<String, Object> platedecoder(Map<String, String> params) throws Exception {
        String url = buildUrl("v2/platedecoder", params);
        return fetch(url);
    }

    public Map<String, Object> plateImageRecognition(String imageUrl) throws Exception {
        String url = getBaseUrl() + "/platerecognition?key=" + apiKey + "&source=java";
        String jsonBody = "{\"image\":\"" + imageUrl + "\"}";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return post(url, jsonBody, headers);
    }

    public Map<String, Object> vinOcr(String imageUrl) throws Exception {
        String url = getBaseUrl() + "/v1/vinocr?key=" + apiKey + "&source=java";
        String jsonBody = "{\"image\":\"" + imageUrl + "\"}";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return post(url, jsonBody, headers);
    }

    public Map<String, Object> yearMakeModel(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/ymm", params);
        return fetch(url);
    }

    public Map<String, Object> images(Map<String, String> params) throws Exception {
        String url = buildUrl("images", params);
        return fetch(url);
    }

    public Map<String, Object> obdcodesdecoder(Map<String, String> params) throws Exception {
        String url = buildUrl("obdcodesdecoder", params);
        return fetch(url);
    }

    public Map<String, Object> LienAndTheft(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/lien-theft", params);
        return fetch(url);
    }
}