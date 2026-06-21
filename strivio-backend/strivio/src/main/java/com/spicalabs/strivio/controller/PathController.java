package com.spicalabs.strivio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spicalabs.strivio.dtos.request.PathCreationRequest;
import com.spicalabs.strivio.dtos.response.PathResponse;
import com.spicalabs.strivio.service.PathService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/paths")
@RequiredArgsConstructor
public class PathController {

    private final PathService pathService;

    @PostMapping
    public ResponseEntity<PathResponse> createPath(@Valid @RequestBody PathCreationRequest req) {

        PathResponse response = pathService.createPath(req);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PathResponse>> getAllPaths() {
        List<PathResponse> response = pathService.getAllPaths();

        return ResponseEntity.ok(response);
    }
}
