package back.adapter.in.web.controller.question.dto;

import back.domain.model.question.Question;

import java.util.List;

public record GetAllQuestionByAnnouncementResponseDtoId(List<Question> questions) {
}
