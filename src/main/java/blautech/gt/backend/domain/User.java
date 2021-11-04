package blautech.gt.backend.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "user")
public class User implements Serializable {

    @Id
    private String id;

    private String username;
    private String createdAt;
    private String updatedAt;
    private String email;
    private long phone;

}
