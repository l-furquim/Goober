package back.application.service.question;

import back.adapter.in.web.controller.question.dto.AwnswerAQuestionRequestDto;
import back.adapter.in.web.controller.question.dto.EditQuestionRequestDto;
import back.adapter.in.web.controller.question.dto.MakeAQuestionRequestDto;
import back.domain.enums.QuestionStatus;
import back.domain.exception.QuestionException;
import back.domain.exception.UserException;
import back.domain.model.question.Question;
import back.domain.port.in.QuestionService;
import back.domain.port.out.QuestionRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    public QuestionServiceImpl(){

    }

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public Question makeAQuestion(MakeAQuestionRequestDto makeAQuestionRequestDto) {
        if(makeAQuestionRequestDto.content().isEmpty()){
            throw new QuestionException("Nao e possivel fazer uma pergunta em branco.");
        }

        var question = new Question(
                makeAQuestionRequestDto.userName(),
                makeAQuestionRequestDto.content(),
                QuestionStatus.NOTANSWERED
        );

        try{
            questionRepository.save(question);
        }catch (IllegalArgumentException e){
            throw new QuestionException(e.getMessage());
        }catch(OptimisticLockingFailureException e) {
            throw new UserException((e.getMessage()));
        }

        return question;
    }

    @Transactional
    @Override
    public void editQuestion(EditQuestionRequestDto editQuestionRequestDto) {
        var question = questionRepository.findQuestionById(editQuestionRequestDto.id());

        if(question.isEmpty()){
            throw new QuestionException("Nao foi possivel editar a pergunta, ela nao existe.");
        }

        question.get().setQuestionContent(editQuestionRequestDto.content());
    }

    @Transactional
    @Override
    public void answerAQuestion(AwnswerAQuestionRequestDto awnswerAQuestionRequestDto) {
        var question = questionRepository.findQuestionById(awnswerAQuestionRequestDto.id());

        if(question.isEmpty()){
            throw new QuestionException("Nao foi possivel responder a pergunta, pergunta nao existe.");
        }

        question.get().setQuestionStatus(QuestionStatus.ANSWERED);
        var asnwerer = new Question(
                awnswerAQuestionRequestDto.userName(),
                awnswerAQuestionRequestDto.content(),
                QuestionStatus.NOTANSWERED
        );
    }
}
