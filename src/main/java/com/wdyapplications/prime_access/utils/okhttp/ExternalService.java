package com.wdyapplications.prime_access.utils.okhttp;


import com.wdyapplications.prime_access.utils.Status;
import com.wdyapplications.prime_access.utils.okhttp.base.OkHttpClientUtilsBase;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class ExternalService extends OkHttpClientUtilsBase {
    public Map<String, Object> sendSmsNotification(String message,String contact,String emeteur) throws Exception {
        try {
            Map<String, Object> data = new HashMap<>();
            // transformation des variables
            // création du message
            data.put("message", message);
            data.put("contact", contact);
            data.put("emetteur", emeteur);
            ObjectMapper objectMapper = new ObjectMapper();
            String request = objectMapper.writeValueAsString(data);
            okhttp3.Response apiResponse = post("https://iflex.orange.ci/api/switch-box-api/hJobExecution/sendSms", request, "json");
            Map<String, Object> respMap = toResponseAsMap(apiResponse);
            JSONObject json = new JSONObject(respMap);
            if (json.has("status") && Objects.equals(json.getJSONObject("status").getString("code"), "800")) {
                respMap.put("message", message);
                return respMap;
            }
            return respMap;
        } catch (Exception e) {
            Map<String, Object> errorData = new HashMap<>();
            Status status = new Status();
            status.setCode("500");
            status.setMessage(e.getMessage());
            errorData.put("status", status);
            errorData.put("hasError", true);
            System.out.println("la requete a echoue : " + e.getMessage());
            return errorData;
        }
    }
}
