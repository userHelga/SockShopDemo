package models.user.cards.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardItem{
	private String expires;
	private String longNum;
	@JsonProperty("_links")
	private Links links;
	private String ccv;
}
