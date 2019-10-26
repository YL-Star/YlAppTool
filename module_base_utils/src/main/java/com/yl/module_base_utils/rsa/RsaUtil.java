package com.yl.module_base_utils.rsa;


import com.yl.module_base_utils.Base64Util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

/**
 * @author cuishuoguo
 * Created on 2018/6/1.
 */
public class RsaUtil {
   private static String pubicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbVV/qscWt4/ah8ftu7l6EYGt6Dy/CdmqO6b5w\n" +
           "zMdENeSFTeMpI//aI2JXZy1GH80gvkRTVB+f0zk/cNH8TKaoQm5gtVjR4f/mrOAxqiwDIxgr5W9z\n" +
           "vhXnEr0gij0wbmT/8r/poC1Sob8EjuHvk8mqYkgsVSJtoEUw9Vn2DzgvgwIDAQAB";
    private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJtVX+qxxa3j9qHx+27uXoRga3oP\n" +
            "L8J2ao7pvnDMx0Q15IVN4ykj/9ojYldnLUYfzSC+RFNUH5/TOT9w0fxMpqhCbmC1WNHh/+as4DGq\n" +
            "LAMjGCvlb3O+FecSvSCKPTBuZP/yv+mgLVKhvwSO4e+TyapiSCxVIm2gRTD1WfYPOC+DAgMBAAEC\n" +
            "gYEAjAsm1OO9w475dvxBobvK/zIm8/7pIju8ymg2yF/3b6nfA8g6+4Z3T2YDbaWaDdTyDYk89Jut\n" +
            "f1ziCi5fQ0agIT205K80BxzsVNk6/7x48fQXQzo6I7PLqBSHShcSBm9l6nGoms9UmynJ6OQbPMWV\n" +
            "TUqvaRyQECGFT+WwidcA6HECQQDblh8sHh4NYOFZpo7Fqe65xhKTOZNIeqA8VsUwCdT760jBo2q+\n" +
            "D0EKQLjDV+oCE3U33T/jFQMjeIlefpKijoB9AkEAtReW2Io/tgAS09zaNKsOZt8uo9ulHF1zOHWh\n" +
            "kx0xhKLv8WYzcXHTYbG9PdfQuXNeP4PEnHApwl39/uQgBNBv/wJAAkSxu73v54l5qnhq/yW7PpYK\n" +
            "ORUeLWB+6UCoNs/TMGsEzGNIxf/YVyfGANaWp0DWNCaH82uTJbmoCZ7bGuzH7QJADniNGE6r2qpU\n" +
            "1tM+KJlGtFe/TV6+6e5Zx7g2qh/aFiRDEkI9eCJFZvgiDDATwM4ESQUJkAqye5KxOk4WdOwHFwJA\n" +
            "fVtmcxPXUf5inqRj6kw49Sub0xew5pJMxE+sIPZAjVgvP3EnHPeSjVjCOPXEACsZtjLk5j53nNtQ\n" +
            "JDCVkunSrA==";
    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

//
//    public static void main(String[] args) {
//        String name="1111";
//        Optional<String> s = Optional.ofNullable(name);
//        s.ifPresent(a->{
//            System.out.println("哈哈哈");
//        });
//        System.out.println("1111");
//    }


    public static void main(String[] args) {
        //公钥加密
        byte[] encryptedBytes = new byte[0];
        try {
            encryptedBytes = encrypt("ceshi测试加密哦！".getBytes("utf-8"), getPublicKey(pubicKey));
            System.out.println("加密后：" + encodeToString(encryptedBytes));

            //私钥解密
            byte[] decryptedBytes = decrypt(encryptedBytes, getPrivateKey(privateKey));
            System.out.println("解密后：" + new String(decryptedBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String RsaEncrypt(String str) {
        String strencode = "";
        //公钥加密
        byte[] encryptedBytes = new byte[0];
        try {
            encryptedBytes = encrypt(str.getBytes(), getPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApWLKrgtdT6iR8hK3fD/rN9P150pDbvkG+5kp4e2P7g5ysCGtyK5M9LUGy6Cxjdb7+wv5p3J0hM8yV/YWXdgQW870kDFFp5RRI7+pBm4IcpnbVK2+ze90a9u0HPpWT5vAGOkhH5TGSZWQbqXIdhtj2tF538TnKJFoclQhBnJzQh1S2SJFo+E6NqRF7OkpI1PB7RaHgTaa2EenOS0ZgFzb5CwHoKjjjScVhvMFHp+D+G57wnSoyqqmxOZJHFRjuL+kq0wNxBzXFIDI4fHQWEJCMf8KlR3XlzzKZXJ6A/LeIeHJXS9N5Mfub5HmV4nUTEYkF0XOvFIlJs3C3/dfU5wVmQIDAQAB"));
            System.out.println("加密后：" + encodeToString(encryptedBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeToString(encryptedBytes);
    }

    public static String RsaEncrypt4(String str) {
        String strencode = "";
        //公钥加密
        byte[] encryptedBytes = new byte[0];
        try {
            encryptedBytes = encrypt(str.getBytes(), getPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApnx/Ol6dwKSKIznvDETyanOgD17KBqU/KUwDg1ppv9P9Frqc9r2pXj+TE/Hqc75ru6Y/BDqUikOGnB0DfwBGaZ3k/54ejOr2H7b6FEgolBCwJ5gNTxjcoS0yJm+aKYnqQiRRJGI3lLwS3KpaX6aBDFRcrREgCjYnzIUjnCynM9ecDnYJ9Hp8sLHa60TtQ3C9kTNb8TT6CQOUox1qhQJ8RAEatOxJlBe493caoXbW5N3w5LPsaSHTuLMy8gQgXyzECClSd6Asj7wobYQB7ti3fX/bCnZTjBtz3Rmu06xQlkIPRKgV0c/FBv3dkTDGOOocxy42wb24rgnlW+GRQh69/QIDAQAB"));
            System.out.println("加密后：" + encodeToString(encryptedBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeToString(encryptedBytes);
    }



    public static String RsaEncrypt2(String str) {
        String strencode = "";
        //公钥加密
        byte[] encryptedBytes = new byte[0];
        try {
            encryptedBytes = encrypt(str.getBytes(), getPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhKJVs8IaYDTeXOvudqOFMr01YT83B+L8a8TvRitqIZtUo7FJog4NxqyS9eNspo5awufwMLmnXBx8XlSQ+QCG/FE2knOxMYeseD8YqmOeKuepVSbk1//czlYiDWjjlMYK6XAvs2maRcS+NOcBYJiL0WCr2gkV/fBLIA9BDNI2XFaRKDbClWmuaIPUiDljHalqLXYcNOJ1AeDXb4qcKfOByMK+gFHM05pyEbTfs46EbpHincNdkDVTXT+MRa0xs0PwvTKGa490bV4V/7XwEOPKc2yK06aIsu7FGtGfCjQaIRJKbG04czxUBayY2qrfEn+K090BeawIj88VB9+1R/iVcQIDAQAB"));
//            System.out.println("加密后：" + encodeToString(encryptedBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeToString(encryptedBytes);
    }

    public static String RsaDecrypt(String str) {
        String strencode = "";
        byte[] decryptedBytes = new byte[0];
        try {
            //私钥解密
            decryptedBytes = decrypt(str.getBytes(), getPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQClYsquC11PqJHyErd8P+s30/XnSkNu+Qb7mSnh7Y/uDnKwIa3Irkz0tQbLoLGN1vv7C/mncnSEzzJX9hZd2BBbzvSQMUWnlFEjv6kGbghymdtUrb7N73Rr27Qc+lZPm8AY6SEflMZJlZBupch2G2Pa0XnfxOcokWhyVCEGcnNCHVLZIkWj4To2pEXs6SkjU8HtFoeBNprYR6c5LRmAXNvkLAegqOONJxWG8wUen4P4bnvCdKjKqqbE5kkcVGO4v6SrTA3EHNcUgMjh8dBYQkIx/wqVHdeXPMplcnoD8t4h4cldL03kx+5vkeZXidRMRiQXRc68UiUmzcLf919TnBWZAgMBAAECggEAPZwl4Nw+8JPdeo5aaCDvoSaG7piNsmN2pHeUUKPNAGPFebOyJ5Y2+NoPJABC5JH34Le/Uw+iNG9zU61K+76nqWvZU3XnetwXtfeKIekPoJGMUWlAMTtI5X09BaaETLe6wgzm1/r2OydKXM3JdaYSdsueWSvi6QvyGtUYUGyGX1QvkRUUtwGPDPaaZxuZtktDwuduKP9iVUpz4gjbK4wgMScs4a7XM7G3MLtwZnKxcqKRVTeLIbAoYhbHXI79re6eNz1dHNrzkEZj4Qb/AzkVUy5SE1qGK/+TbXZOOCrW5tf+vSDV4+v8pYZHcfgL0RZF0myxrhjVazUjgw+VARunAQKBgQDbSD8Vpxu/S4i/nBM/AaZIGyds8gS+f5hAaosE1K7aTC3BbpifgokQpnAHgu4QR0beSHTVcGjB68YCyTbUzqWS8oZBY9p+uVc9BCuy5++zOd3XnbgcATIL4QNxYvDNp5hv0v707BVnDetLA7Ss/gem+YF5naeptiulWHru1TgBkQKBgQDBFDnCnqXq1pR5cTc2zgMEsSn1tTM1vb0q6ECtFnlu57Z89t0bWdv1tbkbPgBulP1g9HFyKEoikJ17Ezt7cXMCFBZYscvEeQFc+t+Nl6oGcQjWgZKjS6iPsqhqTz/wtYWVoe7idALy6GeI+YjMXGuBUwIidxOjhCbmWdcpae/PiQKBgH6p3MbFIGH/X55OqU9bxJEiHkPwCI2BZpDO+j5wHBA8sLFrEP4SiNn1SgEMwDbm9Siw8cvYgqqBZn91exaxxzJ6pNJgu3U32gyEvDk7gFomst45XH/QsPfrofF9FVu103fEiUEwnaxJfgB2w4aptgHdXczCJHcDo3J/CYOr5g4BAoGBAI+dJrVvYgRCZZ/LKYGXTMFxsb80b4navtkqdekJHXvz2FYtHRuaBDVIXQ45WQLnyWwThkqeUz5QiQKhtI3GPgBexpxkayeRxgTOCfh8xhgSfbtoF5Vk0lVIU2L33ZyCuIErOM+x8PuTCGfnC+zOFZzVDHJsVew7gN+4FYWVaa3ZAoGABdXX5NF6ecee6mtHp4/8THSG+uQZZxTgUJLoWhs7T5xRmTwvUk2l7f4F4tCjzGpDpk5RX7gx0+e28PY8twXEoK/hFjdd9M4Ubr6cWXCsbp1brIXR+c+drD25CP2jbLCWQ6KlIdyYPopLRTFo9cRxYZeu5RFgUsRY5V3fBFoi8JQ="));
//            System.out.println("解密后：" + new String(decryptedBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new String(decryptedBytes);
//        return encodeToString(decryptedBytes);
    }
    /**
     * 加密调用方式   byte[] encryptedBytes=RsaUtil.encrypt("此处填字节码，即byte",RsaUtil.getPublicKey("此处填公钥"))
     *           String s=RsaUtil.encodeToString(encryptedBytes);s是加密后的字符串
     *           接口必传参数 channel  param  其中param是加密后的字符串 加密前是将参数转换成json字符串 随后进行加密
     *
     */


    /**
     * 将base64编码后的公钥字符串转成PublicKey实例
     */

    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            keyBytes = Base64.getDecoder().decode(publicKey);
        } else {
            keyBytes = Base64Util.decode(publicKey);

        }
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 将base64编码后的私钥字符串转成PrivateKey实例
     */

    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            keyBytes = Base64.getDecoder().decode(privateKey);
        } else {
            keyBytes = Base64Util.decode(privateKey);
        }
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 公钥加密
     */
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    /**
     * 私钥解密
     */
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }


    /**
     * 公钥加密
     */
    public static byte[] encrypt1(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    /**
     * 私钥解密
     */
    public static byte[] decrypt1(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }


    public static String encodeToString(byte[] bytes) {
        char[] encodedChars = encode(bytes);
        return new String(encodedChars);
    }


    public static char[] encode(byte[] data) {

        int l = data.length;

        char[] out = new char[l << 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }

        return out;
    }

}
