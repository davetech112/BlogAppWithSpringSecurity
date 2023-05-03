package com.example.blogappweek9.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponse<T> {
    private String message;
    private T data;
    private HttpStatus status;
}
