package com.anonycar.member.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptorImpl implements Encryptor {
    private static final String ALGORITHM = "SHA-256";

    public EncryptorImpl() {
    }

    @Override
    public String encrypt(String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(text.getBytes());
            return bytesToHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(ALGORITHM);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}