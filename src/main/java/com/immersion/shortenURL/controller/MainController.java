package com.immersion.shortenURL.controller;
import com.immersion.shortenURL.repository.RedisRepository;
import com.immersion.shortenURL.service.ShortenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final RedisRepository redisRepository;
    private final ShortenService shortenService;


    @Value("${app.host}")
    private String host;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/url/short")
    public ResponseEntity<Object> ShortenURL(@RequestParam String url) {
        String token = shortenService.getToken();
        redisRepository.set(token, url);
        String shortenUrl = host + "/" + token;
        return ResponseEntity.ok().body(Map.of("message", "success", "url", shortenUrl));
    }

    @GetMapping("/{token}")
    public RedirectView redirect(@PathVariable String token) {
        String original = redisRepository.get(token);
        log.warn("originalURL = {}", original);
        if (original == null) {
            original = "/";
        }
        return new RedirectView(original);
    }
}
