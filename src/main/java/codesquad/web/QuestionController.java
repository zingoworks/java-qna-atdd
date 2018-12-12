package codesquad.web;

import codesquad.domain.Question;
import codesquad.domain.User;
import codesquad.security.LoginUser;
import codesquad.service.QnaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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

//    @GetMapping("")
//    public String list(Model model) {
//        List<User> users = userService.findAll();
//        log.debug("user size : {}", users.size());
//        model.addAttribute("users", users);
//        return "/user/list";
//    }
//
//    @GetMapping("/{id}/form")
//    public String updateForm(@LoginUser User loginUser, @PathVariable long id, Model model) {
//        model.addAttribute("user", userService.findById(loginUser, id));
//        return "/user/updateForm";
//    }
//
//    @PutMapping("/{id}")
//    public String update(@LoginUser User loginUser, @PathVariable long id, User target) {
//        userService.update(loginUser, id, target);
//        return "redirect:/users";
//    }
}
