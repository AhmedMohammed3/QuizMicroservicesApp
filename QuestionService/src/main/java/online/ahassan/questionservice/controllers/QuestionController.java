package online.ahassan.questionservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.ahassan.questionservice.dto.QuestionDto;
import online.ahassan.questionservice.dto.QuizQuestionDto;
import online.ahassan.questionservice.entities.QuestionOptions;
import online.ahassan.questionservice.services.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<Page<QuestionDto>> getAllQuestions(@RequestParam Map<String, String> requestFilters, Pageable pageable) {
        Map<String, String> caseInsensitiveFilters = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitiveFilters.putAll(requestFilters);

        try {
            Page<QuestionDto> allQuestions = questionService.getAllQuestions(caseInsensitiveFilters, pageable);
            log.info("Questions fetched successfully. Page: {}, Size: {}, Total: {}",
                    pageable.getPageNumber(), pageable.getPageSize(), allQuestions.getTotalElements());
            return ResponseEntity.ok(allQuestions);
        } catch (Exception e) {
            log.error("Error fetching questions", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<QuestionDto> addQuestion(@RequestBody QuestionDto questionDto) {
        try {
            QuestionDto createdQuestion = questionService.addQuestion(questionDto);
            log.info("Question added successfully: {}", createdQuestion.getQuestionTitle());
            return ResponseEntity.ok(createdQuestion);
        } catch (Exception e) {
            log.error("Error adding question", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * PUT - Full update (replace entire resource)
     */
    @PutMapping("/{id}")
    public ResponseEntity<QuestionDto> replaceQuestion(@PathVariable Integer id, @RequestBody QuestionDto questionDto) {
        try {
            QuestionDto updatedQuestion = questionService.replaceQuestion(id, questionDto);
            return ResponseEntity.ok(updatedQuestion);
        } catch (IllegalArgumentException e) {
            log.error("Error replacing question", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error replacing question", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * PATCH - Partial update
     */
    @PatchMapping("/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable Integer id, @RequestBody QuestionDto questionDto) {
        try {
            QuestionDto updatedQuestion = questionService.updateQuestion(id, questionDto);
            return ResponseEntity.ok(updatedQuestion);
        } catch (IllegalArgumentException e) {
            log.error("Error updating question", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error updating question", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        try {
            String message = questionService.deleteQuestion(id);
            log.info("Question deleted successfully: {}", message);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            log.error("Error deleting question", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete question");
        }
    }

    @GetMapping("/generate")
    public ResponseEntity<List<QuestionDto>> generateRandomQuestions(@RequestParam String category, @RequestParam Integer numOfQuestions) {
        try {
            List<QuestionDto> randomQuestions = questionService.findRandomQuestionsByCategory(category, numOfQuestions);
            return ResponseEntity.ok(randomQuestions);
        } catch (Exception e) {
            log.error("Error generating random questions", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<Page<QuestionDto>> findByQuizId(@PathVariable Integer quizId, Pageable pageable) {
        try {
            Page<QuestionDto> questions = questionService.findByQuizId(quizId, pageable);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            log.error("Error finding questions by quizId", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{questionId}/option/{optionId}")
    public ResponseEntity<QuestionOptions> findByQuestionIdAndOptionId(@PathVariable Integer questionId, @PathVariable Integer optionId) {
        try {
            Optional<QuestionOptions> questionOption = questionService.findByQuestionIdAndOptionId(questionId, optionId);
            return questionOption.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            log.error("Error finding question by questionId and optionId", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addQuestionToQuiz")
    public ResponseEntity<QuizQuestionDto> addQuestionToQuiz(QuizQuestionDto quizQuestionDto) {
        try {
            QuizQuestionDto addedQuestionToQuiz = questionService.addQuestionToQuiz(quizQuestionDto);
            return ResponseEntity.ok(addedQuestionToQuiz);
        } catch (Exception e) {
            log.error("Error adding question to quiz", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
