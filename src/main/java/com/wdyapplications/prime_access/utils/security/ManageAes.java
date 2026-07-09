package com.wdyapplications.prime_access.utils.security;

import com.wdyapplications.prime_access.utils.Utilities;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

public class ManageAes {

    public ManageAes() {
        Security.addProvider(new BouncyCastleProvider());
    }
    public static String encodeForMobile(String plainText,String secretCode) throws Exception {
        String data_encoded = "";
        try {
            Security.addProvider(new BouncyCastleProvider());
            final String encryptionKey = Utilities.notBlank(secretCode)?secretCode:ParamKey.KEY_AES;
            final SecretKeySpec keySpecification = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), ParamKey.ALGORITHM_AES_ECB_PKCS7);
            final Cipher cipher = Cipher.getInstance(ParamKey.ALGORITHM_AES_ECB_PKCS7, "BC");
            cipher.init(Cipher.ENCRYPT_MODE, keySpecification);
            final byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            data_encoded = new String(Base64.encode(encryptedBytes));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return data_encoded;
    }
    public static String decodeForMobile(String plainText, String secretCode) {
        String data_encoded = "";
        try {
            Security.addProvider(new BouncyCastleProvider());
            final String encryptionKey = Utilities.notBlank(secretCode)?secretCode:ParamKey.KEY_AES;
            final SecretKeySpec keySpecification = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), ParamKey.ALGORITHM_AES_ECB_PKCS7);
            final Cipher cipher = Cipher.getInstance(ParamKey.ALGORITHM_AES_ECB_PKCS7, "BC");
            cipher.init(Cipher.DECRYPT_MODE, keySpecification);
            final byte[] bytesToDecrypt = Base64.decode((plainText.getBytes(StandardCharsets.UTF_8)));
            final byte[] encryptedBytes = cipher.doFinal(bytesToDecrypt);
            data_encoded = new String(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data_encoded;
    }
}
