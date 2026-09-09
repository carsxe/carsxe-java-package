package io.github.carsxe;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
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
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry.getValue() != null) {
                    urlBuilder.append("&")
                            .append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name()))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name()));
                }
            }
        }
        return urlBuilder.toString();
    }

    private String readResponse(HttpURLConnection conn) throws Exception {
        int responseCode = conn.getResponseCode();
        InputStream stream = (responseCode >= 200 && responseCode < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();
        if (stream == null) {
            return "";
        }
        return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
    }

    private Map<String, Object> parseJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json,
                new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {
                });
    }

    private Map<String, Object> fetch(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return parseJson(readResponse(conn));
    }

    private String fetchRaw(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return readResponse(conn);
    }

    private Map<String, Object> post(String urlString, String jsonBody, Map<String, String> headers) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        // Add custom headers
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }
        }

        conn.setDoOutput(true);
        conn.getOutputStream().write(jsonBody.getBytes(StandardCharsets.UTF_8));

        return parseJson(readResponse(conn));
    }

    public Map<String, Object> specs(Map<String, String> params) throws Exception {
        String url = buildUrl("specs", params);
        return fetch(url);
    }

    // marketvalue: GET /v2/marketvalue
    // Required: vin
    // Optional: state (US state code), mileage (numeric), condition (excellent|clean|average|rough)
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

    // recallsYmm: GET /v1/recalls-ymm
    // Required: year, make, model
    public Map<String, Object> recallsYmm(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/recalls-ymm", params);
        return fetch(url);
    }

    // recallsBatchSubmit: POST /v1/recalls-batch/submit
    // Provide at least one of: vins (List<String>), csv, csvUrl
    // Optional: webhookUrl
    public Map<String, Object> recallsBatchSubmit(Map<String, Object> body) throws Exception {
        String url = buildUrl("v1/recalls-batch/submit", new HashMap<String, String>());
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body == null ? new HashMap<String, Object>() : body);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return post(url, jsonBody, headers);
    }

    public Map<String, Object> recallsBatchSubmit(List<String> vins) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("vins", vins);
        return recallsBatchSubmit(body);
    }

    // recallsBatchStatus: GET /v1/recalls-batch/status
    // Required: batchId
    public Map<String, Object> recallsBatchStatus(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/recalls-batch/status", params);
        return fetch(url);
    }

    // recallsBatchResults: GET /v1/recalls-batch/results
    // Required: batchId
    public Map<String, Object> recallsBatchResults(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/recalls-batch/results", params);
        return fetch(url);
    }

    // recallsBatchDownload: GET /v1/recalls-batch/download
    // Required: batchId
    // Returns CSV text
    public String recallsBatchDownload(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/recalls-batch/download", params);
        return fetchRaw(url);
    }

    // ymmOptions: GET /v1/ymm-options
    // Optional: year, make, model, dimension (years|makes|models|trims|variants), trim
    public Map<String, Object> ymmOptions(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/ymm-options", params);
        return fetch(url);
    }

    // ownershipVin: GET /v1/ownership/vin
    // Required: vin
    // Optional: include (demographics,emails,phones,vehicle_history)
    public Map<String, Object> ownershipVin(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/ownership/vin", params);
        return fetch(url);
    }

    // ownershipPerson: GET /v1/ownership/person
    // Required: first_name, last_name, address, zip
    // Optional: include
    public Map<String, Object> ownershipPerson(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/ownership/person", params);
        return fetch(url);
    }

    // ownershipAddress: GET /v1/ownership/address
    // Required: address, zip
    // Optional: include, variant (legacy)
    public Map<String, Object> ownershipAddress(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/ownership/address", params);
        return fetch(url);
    }

    // ownershipZip: GET /v1/ownership/zip
    // Required: zip
    // Optional: gender, min_age, max_age, income, page, limit, include, variant
    public Map<String, Object> ownershipZip(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/ownership/zip", params);
        return fetch(url);
    }

    // usPlatedecoder: GET /v1/us-platedecoder
    // Required: plate, state
    // Optional: decodeVIN (true|false)
    public Map<String, Object> usPlatedecoder(Map<String, String> params) throws Exception {
        String url = buildUrl("v1/us-platedecoder", params);
        return fetch(url);
    }
}
