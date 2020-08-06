package coderslab.quiz.service;

import coderslab.quiz.entities.Answer;
import coderslab.quiz.entities.Category;
import coderslab.quiz.entities.Question;
import coderslab.quiz.interfaces.QuestionService;
import coderslab.quiz.repositories.AnswerRepository;
import coderslab.quiz.repositories.QuestionRepository;
import coderslab.quiz.repositories.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl(
            UserRepository userRepository,
            QuestionRepository questionRepository,
            AnswerRepository answerRepository
    ) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> findTrueAnswers(Question question) {

        List<Answer> trueAnswers = new ArrayList<>();
        List<Integer> properAnswersIDs = new ArrayList<>();
        question.getAnswerList().stream().forEach(answer -> {
            {
                if (answer.isProper())
                    trueAnswers.add(answer);
            }
        });

        return trueAnswers;
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> findAllinCategory(String categoryName) {
        return questionRepository.findAllByCategoryCategoryName(categoryName);
    }

    @Override
    public boolean doesQuestionExist(String questionQuery) {
        if (questionRepository.findFirstByQuery(questionQuery) != null)
            return true;
        else
            return false;
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public void update(Question question) {
    }

    @Override
    public List<Answer> findAnswersForQuestion(Question question) {
        return question.getAnswerList();
    }

    public Question findByQuery(String query) {
        return questionRepository.findByQuery(query);
    }

    @Override
    public List<Question> findAllinCategory(Category category) {
        return questionRepository.findAllByCategory(category);
    }

    @Override
    public int countProperAnswers(Question question) {
        return answerRepository.countProperAnswers(question);
    }

    @Override
    public Boolean savePicture(Question question, MultipartFile file) {

        String[] elemnts = file.getOriginalFilename().split("\\.");
        Path path1 = Paths.get("src/main/webapp/resources/uploaded/pictures/" + Timestamp.valueOf(LocalDateTime.now()) + "." + elemnts[elemnts.length - 1]);

        if (file.getSize() > 0) {

            deletePicture(question);

            try {
                InputStream inputStream = new ByteArrayInputStream(file.getBytes());
                Files.copy(
                        inputStream,
                        path1,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        if (file.getSize() > 0)
            question.setPicture("../../" + path1.toString().substring(16));

        return true;
    }

    @Override
    public Boolean deletePicture(Question question) {

        if ((question.getPicture() != null) && (question.getPicture()).startsWith("../../resources/uploaded/pictures")) {

            //TODO ten blok trzeba przenieść gdziś indziej
            try {
                Files.delete(Paths.get("src/main/webapp/", findById(question.getId()).getPicture().substring(6)));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            question.setPicture(null);
            System.out.println("picture deleted");
            return true;
        } else if ((question.getPicture() != null) && (question.getPicture().startsWith("http://res.cloudinary.com/marcin1136/image/upload/"))) {
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "marcin1136",
                    "api_key", "737373993878471",
                    "api_secret", "m59_i4tJ2O2pmBuTrGW0W5USEKA"));

            String publicID = question.getPicture().substring(50).split("/")[1].split("\\.")[0];

            try {
                cloudinary.uploader().destroy(publicID, ObjectUtils.emptyMap());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            question.setPicture(null);
            return true;

        } else {
            question.setPicture(null);
            System.out.println("picture in not uploaded on local or remote server");
            return false;
        }
    }


}
