package com.evaluation.ProjectManagementBoardAPI.Service;

import com.evaluation.ProjectManagementBoardAPI.Models.Board;
import com.evaluation.ProjectManagementBoardAPI.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards(){
        return boardRepository.findAll();
    }

    public Board createBoard(String title) {
        Board board = new Board(title);
        return boardRepository.save(board);
    }

    public Board getBoardById(Long id){
        return boardRepository.findById(id).get();
    }

    public Board saveBoard(Board board){
        return boardRepository.save(board);
    }


    public boolean deleteBoardById(Long id) {
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return true;
        }
        return false;
    }




}