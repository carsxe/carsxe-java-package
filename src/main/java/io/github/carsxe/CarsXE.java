package io.github.carsxe;

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

    private String fetch(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            StringBuilder errorResponse = new StringBuilder();
            String errorLine;
            while ((errorLine = errorStream.readLine()) != null) {
                errorResponse.append(errorLine);
            }
            errorStream.close();
            return "Error: HTTP status code " + responseCode + " - " + errorResponse.toString();
        }
    }

    private String post(String urlString, String jsonBody, Map<String, String> headers) throws Exception {
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
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            StringBuilder errorResponse = new StringBuilder();
            String errorLine;
            while ((errorLine = errorStream.readLine()) != null) {
                errorResponse.append(errorLine);
            }
            errorStream.close();
            return "Error: HTTP status code " + responseCode + " - " + errorResponse.toString();
        }
    }

    public String specs(Map<String, String> params) throws Exception {
        String url = buildUrl("specs", params);
        return fetch(url);
    }

    public String marketvalue(Map<String, String> params) throws Exception {
        String url = buildUrl("v2/marketvalue", params);
        return fetch(url);
    }

    public String history(Map<String, String> params) throws Exception {
        String url = buildUrl("history", params);
        return fetch(url);
    }

    public String recalls(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/recalls", params);
        return fetch(url);
    }

    public String internationalVinDecoder(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/international-vin-decoder", params);
        return fetch(url);
    }

    public String platedecoder(Map<String, String> params) throws Exception {
        String url = buildUrl("v2/platedecoder", params);
        return fetch(url);
    }

    public String plateImageRecognition(String imageUrl) throws Exception {
        String url = getBaseUrl() + "/platerecognition?key=" + apiKey + "&source=java";
        String jsonBody = "{\"image\":\"" + imageUrl + "\"}";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return post(url, jsonBody, headers);
    }

    public String vinOcr(String imageUrl) throws Exception {
        String url = getBaseUrl() + "/v1/vinocr?key=" + apiKey + "&source=java";
        String jsonBody = "{\"image\":\"" + imageUrl + "\"}";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return post(url, jsonBody, headers);
    }

    public String yearMakeModel(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/ymm", params);
        return fetch(url);
    }

    public String images(Map<String, String> params) throws Exception {
        String url = buildUrl("images", params);
        return fetch(url);
    }

    public String obdcodesdecoder(Map<String, String> params) throws Exception {
        String url = buildUrl("obdcodesdecoder", params);
        return fetch(url);
    }
}