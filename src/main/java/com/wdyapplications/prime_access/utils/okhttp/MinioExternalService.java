package com.wdyapplications.prime_access.utils.okhttp;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdyapplications.prime_access.utils.ParamsUtils;
import com.wdyapplications.prime_access.utils.dto.ImageDto;
import com.wdyapplications.prime_access.utils.okhttp.base.OkHttpClientUtilsBase;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class MinioExternalService extends OkHttpClientUtilsBase {
    @Autowired
    private ParamsUtils paramUtils;


    public String saveImage(ImageDto recordImageDto) throws Exception {
        try {
            Map<String, Object> data = new HashMap<>();
            Map<String, Object> req = new HashMap<>();
            data.put("file", recordImageDto.getFileBase64());
            data.put("filename", recordImageDto.getFilename());
            data.put("bucket_name", paramUtils.getMinioBucketName());
            data.put("subdirectory_name", recordImageDto.getPath());
            req.put("data", data);
            ObjectMapper objectMapper = new ObjectMapper();
            String request = objectMapper.writeValueAsString(req);
            okhttp3.Response apiResponse = post(paramUtils.getMinioUrl()+ "/minio-api/uploadFileFromBase64", request, "json");
            Map<String, Object> respMap = toResponseAsMap(apiResponse);
            JSONObject json = new JSONObject(respMap);
            if (json.has("items")) {
                JSONArray items = json.getJSONArray("items");
                if (items != null && !items.isEmpty()) {
                    return items.getJSONObject(0).getString("name");
                }
            }
        } catch (Exception e) {
            log.error("la requete a echoue : " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return  null;
    }
}
