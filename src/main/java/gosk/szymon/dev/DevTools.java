package gosk.szymon.dev;

import gosk.szymon.model.common.EducationLevel;
import gosk.szymon.model.common.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@DevOnly
@Component
public final class DevTools {

    private final RecipientRepository recipientRepository;
    private final EducationLevelRepository educationLevelRepository;

    @Autowired
    public DevTools(RecipientRepository recipientRepository, EducationLevelRepository educationLevelRepository) {
        this.recipientRepository = recipientRepository;
        this.educationLevelRepository = educationLevelRepository;
    }

    public ResponseEntity<Object> createRecipients(List<Recipient> recipients) {
        try {
            recipientRepository.saveAll(recipients);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating recipients: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> createEducationLevels(List<EducationLevel> educationLevels) {
        try {
            educationLevelRepository.saveAll(educationLevels);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating education levels: " + e.getMessage());
        }
    }


}
