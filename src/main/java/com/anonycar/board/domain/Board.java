package com.anonycar.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Builder
    public Board(Long id, String name, BoardType boardType) {
        this.id = id;
        this.name = name;
        this.boardType = boardType;
    }
}
