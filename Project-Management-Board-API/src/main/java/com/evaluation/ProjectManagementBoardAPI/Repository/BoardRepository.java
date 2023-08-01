package com.evaluation.ProjectManagementBoardAPI.Repository;


import com.evaluation.ProjectManagementBoardAPI.Models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


}
