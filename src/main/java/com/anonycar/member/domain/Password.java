package com.anonycar.member.domain;

import com.anonycar.member.service.Encryptor;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Password {

    private static final Pattern PATTERN =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$");

    @Column(name = "password")
    private String value;

    protected Password() {
    }

    public static Password of(Encryptor encryptor, String password) {
        validate(password);
        return new Password(encryptor.encrypt(password));
    }

    public Password(String value) {
        this.value = value;
    }

    private static void validate(String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("비밀번호 조건을 만족하지 않습니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Password password = (Password) o;
        return getValue().equals(password.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}