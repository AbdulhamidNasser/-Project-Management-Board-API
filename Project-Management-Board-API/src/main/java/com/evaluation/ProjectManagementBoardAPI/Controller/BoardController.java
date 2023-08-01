package com.evaluation.ProjectManagementBoardAPI.Controller;

import com.evaluation.ProjectManagementBoardAPI.Models.Board;
import com.evaluation.ProjectManagementBoardAPI.RequestObject.BoardRequest;
import com.evaluation.ProjectManagementBoardAPI.ResponseObject.BoardResponse;
import com.evaluation.ProjectManagementBoardAPI.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody Board updatedBoard) {
        Board board = boardService.getBoardById(id);
        board.setName(updatedBoard.getName());
        board.setDescription(updatedBoard.getDescription());

        boardService.saveBoard(board);

        return "Updated Successfully";
    }
}
