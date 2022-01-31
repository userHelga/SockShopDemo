package models.user.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import models.user.cards.pojos.Embedded;

@Data
public class GetCardsResponse {
    @JsonProperty("_embedded")
    private Embedded embedded;
}
