package com.anonycar.board.dto;

import lombok.Getter;

@Getter
public class BoardRequest {
    private String boardName;
    private String boardType;

    public BoardRequest(String boardName, String boardType) {
        this.boardName = boardName;
        this.boardType = boardType;
    }
}
