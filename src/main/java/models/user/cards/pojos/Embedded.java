package models.user.cards.pojos;

import lombok.Data;

import java.util.List;

@Data
public class Embedded{
	private List<CardItem> card;
}