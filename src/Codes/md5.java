package Codes;
/**
 * Created by aksha on 26-03-2017.
 */
        import java.security.MessageDigest;
        import java.math.BigInteger;
/** * Created by aksha on 26-03-2017. */
public class md5 {
    public String MD5(String pass) throws Exception{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(pass.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        return(hashtext);
    }
}