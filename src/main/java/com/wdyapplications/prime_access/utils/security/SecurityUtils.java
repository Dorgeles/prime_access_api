package com.wdyapplications.prime_access.utils.security;


public class SecurityUtils {

    public static  String ExtractDataFromAesMobile(String data) {
        return ExtractDataFromAesMobile(data, ParamKey.KEY_AES);
    }

    public static String ExtractDataFromAesMobile(String data, String keyAes) {
        String decryptedAes = "";
        try {
            decryptedAes =  ManageAes.decodeForMobile(data, keyAes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return decryptedAes;
    }

    public static  String EncryptResponseMobile(String data) {
        return EncryptResponseMobile(data, ParamKey.KEY_AES);
    }

    public static String EncryptResponseMobile(String responseValue, String keyAes) {
        String data = "";
        try {
            data = "{\"item\":\"" + ManageAes.encodeForMobile(responseValue, keyAes) + "\"}";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
