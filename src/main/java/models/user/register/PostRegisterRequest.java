package models.user.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class PostRegisterRequest {
    @JsonProperty("username")
    private String userName;
    private String password;
    private String email;
}
