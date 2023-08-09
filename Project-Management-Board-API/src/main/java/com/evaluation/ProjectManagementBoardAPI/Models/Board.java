package com.evaluation.ProjectManagementBoardAPI.Models;


import lombok.*;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "boards")
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

    @OneToMany
    List<Card>  cards;
}
