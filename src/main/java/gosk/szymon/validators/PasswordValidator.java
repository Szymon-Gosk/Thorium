package gosk.szymon.validators;

public class PasswordValidator {

    public boolean validate(String password, String repeatedPassword) {
        return password.equals(repeatedPassword);
    }

}
