package com.wdyapplications.prime_access.utils.okhttp.base;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wdyapplications.prime_access.utils.Status;
import com.wdyapplications.prime_access.utils.Utilities;
import com.wdyapplications.prime_access.utils.contract.Response;
import okhttp3.OkHttpClient;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class OkHttpClientUtilsBase {
    protected okhttp3.Response get(String url, long timeout, TimeUnit timeUnit) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(timeout, timeUnit)
                .readTimeout(timeout, timeUnit)
                .connectTimeout(timeout, timeUnit)
                .build();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("isEncrypte", "false")
                .addHeader("Connection","close")
                .build();
        return client.newCall(request).execute();
    }

    protected okhttp3.Response get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("isEncrypte", "false")
                .build();

        return client.newCall(request).execute();
    }

    protected okhttp3.Response post(String url, String queryBody, String type) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(3600, TimeUnit.SECONDS)
                .readTimeout(3600, TimeUnit.SECONDS)
                .connectTimeout(3600, TimeUnit.SECONDS)
                .build();
        okhttp3.MediaType   mediaType = okhttp3.MediaType.parse(getMediaType(type) + "; charset=utf-8");
        okhttp3.RequestBody body      = okhttp3.RequestBody.create(mediaType, queryBody);
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("isEncrypte", "false")
                .build();


        return client.newCall(request).execute();
    }

    /**
     * Pour les requetes XML de type POST
     *
     * @param url de la requete XML.
     * @param queryBody
     * @return le retour de la requete.
     */
    protected ResponseEntity<String> postXml(String url, String queryBody, String host) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/xml");
        headers.set("Host", host);

        HttpEntity<String> requestEntity = new HttpEntity<>(queryBody, headers);

        try {
            // Envoi de la demande et récupération de la réponse
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            // Affichage de la réponse
            return responseEntity;
        }catch (Exception e) {
                // Affiche l'erreur pour le débogage
            String message = e.getMessage();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message); // Retourne une réponse d'erreur en cas d'échec
        }
    }

//    protected ResponseEntity<String> getRequest(String url) {
//
//        // Créez une instance de RestTemplate
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Effectuez une requête GET en utilisant la méthode getForObject() de RestTemplate
//        // et spécifiez le type de réponse attendu en utilisant la méthode .class
//        String response = restTemplate.getForObject(url, String.class);
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type","application/xml");
//
//
//        try {
//            // Envoi de la demande et récupération de la réponse
//            ResponseEntity<String> responseEntity = restTemplate.getForObject(url, String.class);
//
//            // Affichage de la réponse
//            System.out.println(responseEntity.getBody());
//
//            return responseEntity;
//        }catch (Exception e) {
//            // Affiche l'erreur pour le débogage
//            String message = e.getMessage();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message); // Retourne une réponse d'erreur en cas d'échec
//
//        }
//    }

    protected Response getResponse(okhttp3.Response response) throws IOException {
        Response apiResponse = new Response();
        if (!response.isSuccessful() || response.body() == null) {
            Status status = new Status();
            status.setCode(response.code() + "");
            status.setMessage(response.body() == null ? "L'appel de l'api a échoué." : response.message());
            apiResponse.setHasError(true);
            apiResponse.setStatus(status);
        } else {
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            okhttp3.ResponseBody responseBody = response.body();
            apiResponse = objectMapper.readValue(responseBody.string(), Response.class);
        }

        return apiResponse;
    }


    protected Map<String, Object> getResponseAsMap(okhttp3.Response response) throws IOException {
        Map<String, Object> resp = new HashMap<>();
        try {
            if (response.isSuccessful()) {
                ObjectMapper         objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                okhttp3.ResponseBody responseBody = response.body();
                resp = objectMapper.readValue(responseBody.string(), HashMap.class);
            } else {
                throw new Exception(response.message());
            }
        } catch (Exception e) {
            resp.putIfAbsent("hasError", true);
            resp.putIfAbsent("message", e.getMessage());
        }
        return resp;
    }

    protected List<Map<String, Object>> getResponseAsListMap(okhttp3.Response response) throws IOException {
        List<Map<String, Object>> resp = new ArrayList<>();
        try {
            if (response.isSuccessful()) {
                ObjectMapper         objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                okhttp3.ResponseBody responseBody = response.body();
                resp = objectMapper.readValue(responseBody.string(), new TypeReference<List<Map<String, Object>>>() {
                });
            } else {
                throw new Exception(response.message());
            }
        } catch (Exception e) {
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.putIfAbsent("hasError", true);
            errorMap.putIfAbsent("message", e.getMessage());
            resp.add(errorMap);
        }
        return resp;
    }

    protected Map<String, Object> toResponseAsMap(okhttp3.Response response) throws IOException {
        Map<String, Object> resp = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            okhttp3.ResponseBody responseBody = response.body();
            resp = objectMapper.readValue(responseBody.string(), HashMap.class);
        } catch (Exception e) {
            resp.putIfAbsent("hasError", true);
            resp.putIfAbsent("message", e.getMessage());
        }
        return resp;
    }

    protected JSONObject toResponseAsJson(okhttp3.Response response) throws IOException {
        JSONObject resp = new JSONObject();
        try {
            ObjectMapper         objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            okhttp3.ResponseBody responseBody = response.body();
            resp = objectMapper.readValue(responseBody.string(), JSONObject.class);
        } catch (Exception e) {
            resp.put("hasError", true);
            resp.put("message", e.getMessage());
        }
        return resp;
    }


    protected String getMediaType(String type) {
        String mediaType = "application/json";
        if (Utilities.areEquals(type, "xml")) {
            mediaType = "application/xml";
        }
        return mediaType;
    }


    protected String fullUrl(String base, String uri) {
        if (!base.endsWith("/")) {
            base = base + "/";
        }
        return String.format("%s%s", base, uri);
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
