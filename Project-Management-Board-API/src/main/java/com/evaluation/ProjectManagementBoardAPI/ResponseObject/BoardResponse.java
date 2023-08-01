package com.evaluation.ProjectManagementBoardAPI.ResponseObject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class BoardResponse {
    private Long boardId;
    private String name;
    private Map<Integer, String> columns;
}