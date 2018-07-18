package Codes;
import org.apache.commons.codec.binary.Base64;
/**
 * Created by aksha on 30-03-2017.
 */
public class base64 {
    public String base64(String s) {
        byte[] encoded = Base64.encodeBase64(s.getBytes());
        return new String(encoded);
    }
    public String decodeBase64(String s){
         byte[] decoded = Base64.decodeBase64(s);
         return new String(decoded);

    }
}