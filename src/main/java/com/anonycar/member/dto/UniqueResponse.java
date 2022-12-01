package com.anonycar.member.dto;

import lombok.Getter;

@Getter
public class UniqueResponse {

    private boolean unique;

    public UniqueResponse(boolean unique) {
        this.unique = unique;
    }
}
