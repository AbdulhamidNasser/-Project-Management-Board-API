package com.evaluation.ProjectManagementBoardAPI.Service;

import com.evaluation.ProjectManagementBoardAPI.Models.Board;
import com.evaluation.ProjectManagementBoardAPI.Models.Card;
import com.evaluation.ProjectManagementBoardAPI.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {


    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(Board board, String title, String description, int section) {
        Card card = new Card();
        card.setBoard(board);
        card.setTitle(title);
        card.setDescription(description);
        card.setSection(section);
        return cardRepository.save(card);
    }

    public List<Card> getAllCards(Board board) {
        return cardRepository.findByBoard(board);
    }

    public Card getCardById(Long cardId, Board board) {
        // You may add validation to check if the card belongs to the specified board
        return cardRepository.findByIdAndBoard(cardId, board);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    public void deleteCard(Card card) {
        cardRepository.delete(card);
    }
}
