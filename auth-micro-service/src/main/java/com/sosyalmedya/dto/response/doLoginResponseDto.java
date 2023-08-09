package com.sosyalmedya.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class doLoginResponseDto {
    /**
     * 200: Successful
     * 400: Error
     */
    int status;
    String result;
    String token; // JWT token
}
