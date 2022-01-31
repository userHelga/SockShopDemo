package models.user.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class PostCardsRequest {
    private String longNum;
    private String expires;
    private String ccv;
    @JsonProperty("userID")
    private String userId;
}

