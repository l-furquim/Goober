package back.adapter.in.web.controller.user.dto;

import org.springframework.web.multipart.MultipartFile;

public record ShowUserPropsResponseDto(String userEmail, String userName, String userImagePath) {
}
