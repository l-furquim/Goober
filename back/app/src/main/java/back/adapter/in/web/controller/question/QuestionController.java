package back.adapter.in.web.controller.question;

import back.adapter.in.web.controller.question.dto.*;
import back.domain.port.in.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/make")
    public ResponseEntity<MakeaQuestResponseDto> makeAQuestion(@RequestBody MakeAQuestionRequestDto makeAQuestionRequestDto){

        questionService.makeAQuestion(makeAQuestionRequestDto);


       return ResponseEntity.ok().body(new MakeaQuestResponseDto("Pergunta realizada com sucesso"));
    }

    @PutMapping("/edit")
    public ResponseEntity<EditQuestionResponseDto> editAQuestion(@RequestBody EditQuestionRequestDto editQuestionRequestDto){

        questionService.editQuestion(editQuestionRequestDto);

        return ResponseEntity.ok().body(new EditQuestionResponseDto("Pergunta editada com sucesso"));
    }

    @PostMapping("/answer")
    public ResponseEntity<AnswerAQuestionResponseDto> answerAQuestion(@RequestBody AwnswerAQuestionRequestDto awnswerAQuestionRequestDto){


        questionService.answerAQuestion(awnswerAQuestionRequestDto);

        return ResponseEntity.ok().body(new AnswerAQuestionResponseDto("Resposta realizada com sucesso"));
    }

    @GetMapping("/get/announcementId={announcementId}")
    public ResponseEntity<GetAllQuestionByAnnouncementResponseDtoId> getAllQuestionsByAnnouncementId(@PathVariable("announcementId") String announcementId){


        var questions = questionService.getQuestionsByAnnouncementId(UUID.fromString(announcementId));

        return ResponseEntity.ok().body(new GetAllQuestionByAnnouncementResponseDtoId(questions));
    }





}
