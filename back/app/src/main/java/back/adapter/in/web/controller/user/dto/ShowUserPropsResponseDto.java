package back.adapter.in.web.controller.user.dto;

import org.springframework.web.multipart.MultipartFile;

public record ShowUserPropsResponseDto(String userId,String userEmail, String userName, String userImagePath) {
}
