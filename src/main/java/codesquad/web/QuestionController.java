package codesquad.web;

import codesquad.domain.Question;
import codesquad.domain.User;
import codesquad.security.LoginUser;
import codesquad.service.QnaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Resource(name = "qnaService")
    private QnaService qnaService;

    @GetMapping("/form")
    public String form() {
        return "/qna/form";
    }

    @PostMapping("")
    public String create(@LoginUser User loginUser, Question question) {
        qnaService.create(loginUser, question);
        return "redirect:/";
    }

    @GetMapping("")
    public String list(Model model, Pageable pageable) {
        List<Question> questions = qnaService.findAll(pageable);
        log.debug("qna size : {}", questions.size());
        model.addAttribute("questions", questions);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable long id, Model model) {
        //TODO : null 익셉션처리
        Question question = qnaService.findById(id).orElse(null);
        log.debug("qna number {}", id);
        model.addAttribute("question", question);
        return "/qna/show";
    }

//    @GetMapping("/{id}/form")
//    public String updateForm(@LoginUser User loginUser, @PathVariable long id, Model model) {
//        model.addAttribute("question", qnaService.findById(id));
//        return "/qna/updateForm";
//    }

//    @PutMapping("/{id}")
//    public String update(@LoginUser User loginUser, @PathVariable long id, User target) {
//        userService.update(loginUser, id, target);
//        return "redirect:/users";
//    }
}
