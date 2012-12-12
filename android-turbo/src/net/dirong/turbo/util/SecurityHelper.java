package net.dirong.turbo.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;


/**
 * @author almozavr ;)
 */
public class SecurityHelper {

    /* Security */

    private static final String seedKey = "tworock";
    private static final String TAG = null;

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    public static String encodeString(String str) {
        if (str == null)
            return null;

        byte[] result = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(getRawKey(seedKey.getBytes()), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            result = cipher.doFinal(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Base64.encodeBytes(result);
    }

    public static String decodeString(String encrypted) {
        String result = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(getRawKey(seedKey.getBytes()), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            byte[] encryptedBytes = Base64.decode(encrypted);
            byte[] decrypted = cipher.doFinal(encryptedBytes);
            result = new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
