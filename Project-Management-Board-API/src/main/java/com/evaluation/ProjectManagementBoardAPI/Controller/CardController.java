package com.evaluation.ProjectManagementBoardAPI.Controller;


import com.evaluation.ProjectManagementBoardAPI.Models.Board;
import com.evaluation.ProjectManagementBoardAPI.Models.Card;
import com.evaluation.ProjectManagementBoardAPI.RequestObject.CardRequest;
import com.evaluation.ProjectManagementBoardAPI.ResponseObject.CardResponse;
import com.evaluation.ProjectManagementBoardAPI.Service.BoardService;
import com.evaluation.ProjectManagementBoardAPI.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/boards")
@CrossOrigin("*")
public class CardController {
    private final CardService cardService;
    private final BoardService boardService;

    @Autowired
    public CardController(CardService cardService, BoardService boardService) {
        this.cardService = cardService;
        this.boardService = boardService;
    }

    @PostMapping(value = "/{boardId}/cards")
    public ResponseEntity<CardResponse> createCard(
            @PathVariable Long boardId,
            @RequestBody CardRequest cardRequest
    ) {
        Board board = boardService.getBoardById(boardId);
        if (board == null) {
            return ResponseEntity.notFound().build();
        }

        Card createdCard = cardService.createCard(
                board,
                cardRequest.getTitle(),
                cardRequest.getDescription(),
                cardRequest.getSection()
        );

        CardResponse response = new CardResponse(
                createdCard.getId(),
                createdCard.getTitle(),
                createdCard.getDescription(),
                createdCard.getSection()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{boardId}/cards")
    public List<CardResponse> getAllCards(@PathVariable Long boardId) {
        Board board = boardService.getBoardById(boardId);
        if (board == null) {
            // If the board with the given ID is not found, return an empty list
            return List.of();
        }

        List<Card> cards = cardService.getAllCards(board);
        return CardResponse.fromCardList(cards);
    }

    @GetMapping("/{boardId}/cards/{cardId}")
    public ResponseEntity<CardResponse> getCardById(
            @PathVariable Long boardId,
            @PathVariable Long cardId
    ) {
        Board board = boardService.getBoardById(boardId);
        if (board == null) {
            return ResponseEntity.notFound().build();
        }

        Card card = cardService.getCardById(cardId, board);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }

        CardResponse response = new CardResponse(
                card.getId(),
                card.getTitle(),
                card.getDescription(),
                card.getSection()
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{boardId}/cards/{cardId}")
    public ResponseEntity<CardResponse> updateCard(
            @PathVariable Long boardId,
            @PathVariable Long cardId,
            @RequestBody CardRequest cardRequest
    ) {
        Board board = boardService.getBoardById(boardId);
        if (board == null) {
            return ResponseEntity.notFound().build();
        }

        Card card = cardService.getCardById(cardId, board);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }

        card.setTitle(cardRequest.getTitle());
        card.setDescription(cardRequest.getDescription());
        card.setSection(cardRequest.getSection());

        Card updatedCard = cardService.updateCard(card);

        CardResponse response = new CardResponse(
                updatedCard.getId(),
                updatedCard.getTitle(),
                updatedCard.getDescription(),
                updatedCard.getSection()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{boardId}/cards/{cardId}")
    public ResponseEntity<Map<String, Object>> deleteCard(
            @PathVariable Long boardId,
            @PathVariable Long cardId
    ) {
        Board board = boardService.getBoardById(boardId);
        if (board == null) {
            return ResponseEntity.notFound().build();
        }

        Card card = cardService.getCardById(cardId, board);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }

        cardService.deleteCard(card);

        Map<String, Object> response = Map.of(
                "successful", true,
                "message", "Card with ID " + cardId + " has been deleted successfully."
        );

        return ResponseEntity.ok(response);
    }
}