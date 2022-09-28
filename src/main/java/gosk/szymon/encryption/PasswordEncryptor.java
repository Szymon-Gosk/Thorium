package gosk.szymon.encryption;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean match(String candidate, String actual) {
        return BCrypt.checkpw(candidate, actual);
    }

}
