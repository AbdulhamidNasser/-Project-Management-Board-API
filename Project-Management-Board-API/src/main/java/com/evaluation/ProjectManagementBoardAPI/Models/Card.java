package com.evaluation.ProjectManagementBoardAPI.Models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards")
public class Card{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int section;

    @ManyToOne//(fetch = FetchType.LAZY)
    private Board board;
}
