package com.immersion.shortenURL.service;

import com.immersion.shortenURL.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShortenService {

    private final RandomTokenGenerator randomTokenGenerator;

    public String getToken() {
        return randomTokenGenerator.generateRandToken(6);
    }

}
