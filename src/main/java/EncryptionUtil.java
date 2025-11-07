import java.util.Base64;

public class EncryptionUtil {
    public static String encrypt(String data, String key) {
        return Base64.getEncoder().encodeToString((data + key).getBytes());
    }

    public static String decrypt(String data, String key) {
        String decoded = new String(Base64.getDecoder().decode(data));
        return decoded.replace(key, "");
    }
}
