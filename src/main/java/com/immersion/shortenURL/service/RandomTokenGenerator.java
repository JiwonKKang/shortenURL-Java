package com.immersion.shortenURL.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class RandomTokenGenerator {

    private final char[] encodingLookup = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_',};

    public List<Integer> generateRandInts(int num) {
        List<Integer> randInts = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < num; i++) {
            randInts.add(random.nextInt(63));
        }

        return randInts;
    }

    public String encodeRandInts(List<Integer> randInts) {
        StringBuilder encoded = new StringBuilder();

        for (int num : randInts) {
            encoded.append(encodingLookup[num]);
        }

        return encoded.toString();
    }

    public String generateRandToken(int num) {
        List<Integer> randInts = generateRandInts(num);
        return encodeRandInts(randInts);
    }
}
