package com.anonycar.member.domain;

import java.util.Arrays;

public enum Brand {
    BENZ("benz"),
    HYUNDAI("hyundai");

    private String english;

    Brand(String english) {
        this.english = english;
    }

    public static Brand of(String name) {
        return Arrays.stream(Brand.values())
                .filter(brand -> brand.english.equals(name))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
