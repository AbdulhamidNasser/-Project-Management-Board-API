package com.evaluation.ProjectManagementBoardAPI.Controller;

import com.evaluation.ProjectManagementBoardAPI.Models.Board;
import com.evaluation.ProjectManagementBoardAPI.RequestObject.BoardRequest;
import com.evaluation.ProjectManagementBoardAPI.ResponseObject.BoardResponse;
import com.evaluation.ProjectManagementBoardAPI.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(@RequestBody BoardRequest boardRequest) {
        Board createdBoard = boardService.createBoard(boardRequest.getTitle());
        BoardResponse response = new BoardResponse(
                createdBoard.getId(),
                createdBoard.getName(),
                Board.getColumns()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoardById(@PathVariable Long id) {
        Board board = boardService.getBoardById(id);
        if (board == null) {
            // If the board with the given ID is not found, return a 404 response
            return ResponseEntity.notFound().build();
        }

        BoardResponse response = new BoardResponse(
                board.getId(),
                board.getName(),
                Board.getColumns()
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody Board updatedBoard) {
        Board board = boardService.getBoardById(id);
        board.setName(updatedBoard.getName());

        boardService.saveBoard(board);

        return "Updated Successfully";
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBoard(@PathVariable Long id) {
        boolean isDeleted = boardService.deleteBoardById(id);
        if (isDeleted) {
            Map<String, Object> response = new HashMap<>();
            response.put("successful", true);
            response.put("message", "Board with ID " + id + " has been deleted successfully.");
            return ResponseEntity.ok(response);
        } else {
            // If the board with the given ID is not found, return a 404 response
            return ResponseEntity.notFound().build();
        }
    }




}
