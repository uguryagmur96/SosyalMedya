package com.sosyalmedya.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class doRegisterResponseDto {
    /**
     * 200: Successful
     * 400: Error
     */
    int status;
    String result;
}
