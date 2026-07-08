package com.wdyapplications.prime_access.utils.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ImageDto {
    private String filename;
    private String fileBase64;
    private String extension;
    private String path;
}
