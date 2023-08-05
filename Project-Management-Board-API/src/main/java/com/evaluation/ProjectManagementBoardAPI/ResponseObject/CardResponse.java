package com.evaluation.ProjectManagementBoardAPI.ResponseObject;

import com.evaluation.ProjectManagementBoardAPI.Models.Card;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CardResponse {

    private Long cardId;
    private String title;
    private String description;
    private Integer section;

    public static List<CardResponse> fromCardList(List<Card> cards) {
        return cards.stream()
                .map(card -> new CardResponse(
                        card.getId(),
                        card.getTitle(),
                        card.getDescription(),
                        card.getSection()
                ))
                .collect(Collectors.toList());
    }
}
