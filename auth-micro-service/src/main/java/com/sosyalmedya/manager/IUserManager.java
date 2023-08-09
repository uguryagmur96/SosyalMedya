package com.sosyalmedya.manager;

import com.sosyalmedya.dto.request.UserSaveRequestDto;
import com.sosyalmedya.dto.response.UserSaveResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.sosyalmedya.constants.RestApiList.SAVE;

@FeignClient(name = "user-micro-service-manager", url = "${my-project.user-service.url}",dismiss404 = true)
public interface IUserManager {
    @PostMapping(SAVE)
    ResponseEntity<UserSaveResponseDto> save(@RequestBody UserSaveRequestDto dto);
}
