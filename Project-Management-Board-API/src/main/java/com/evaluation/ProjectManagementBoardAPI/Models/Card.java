package com.evaluation.ProjectManagementBoardAPI.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int section;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
}
