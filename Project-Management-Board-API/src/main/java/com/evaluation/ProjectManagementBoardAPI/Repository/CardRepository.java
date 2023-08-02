package com.evaluation.ProjectManagementBoardAPI.Repository;

import com.evaluation.ProjectManagementBoardAPI.Models.Board;
import com.evaluation.ProjectManagementBoardAPI.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByBoard(Board board);

    Card findByIdAndBoard(Long cardId, Board board);
}
