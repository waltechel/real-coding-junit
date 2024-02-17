package com.realcoding.chapter02.api.common.util;

import com.realcoding.chapter02.api.common.code.DomainEnum;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RCIdGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static String generateId(DomainEnum keyword) {
        String prefix = keyword.name();
        String randomHash = new BigInteger(130, random).toString(32);
        // 32자 길이의 해시를 생성하고, 길이를 조절합니다.
        randomHash = randomHash.substring(0, Math.min(32, randomHash.length()));

        return prefix + "-" + randomHash;
    }

}
