package gosk.szymon.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationInformationDTO {

    private String username;
    private String email;
    private String password;
    private String repeatedPassword;

}
