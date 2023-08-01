package com.evaluation.ProjectManagementBoardAPI.Models;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private static final Map<Integer, String> columns = new HashMap<>();
    static {
        columns.put(1, "To do");
        columns.put(2, "In progress");
        columns.put(3, "Done");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Board(String name) {
        this.name = name;
    }

    public static Map<Integer, String> getColumns() {
        return columns;
    }
}
