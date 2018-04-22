package cn.greenwishing.bms.web.controller.feedback;

import cn.greenwishing.bms.dto.feedback.FeedbackDTO;
import cn.greenwishing.bms.dto.feedback.FeedbackPagingDTO;
import cn.greenwishing.bms.service.FeedbackService;
import cn.greenwishing.bms.utils.SecurityHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;

/**
 * @author Wufan
 * @date 2018/4/22
 */
@Controller
@RequestMapping("/system/feedback")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @RequestMapping("list")
    public ModelAndView feedbackList(FeedbackPagingDTO pagingDTO, ModelMap model) {
        pagingDTO = feedbackService.loadFeedbackPaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        return new ModelAndView("feedback/feedback_list");
    }

    @GetMapping("reply")
    @ModelAttribute("replyDTO")
    public ModelAndView replyForm(String feedbackGuid, ModelMap model) {
        FeedbackDTO replyDTO = new FeedbackDTO();
        replyDTO.setFeedbackGuid(feedbackGuid);
        model.put("replyDTO", replyDTO);
        return new ModelAndView("feedback/reply_form", model);
    }

    @PostMapping("reply")
    public ModelAndView save(@ModelAttribute("replyDTO") FeedbackDTO replyDTO, BindingResult errors) {
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            replyDTO.setUserGuid(SecurityHolder.getUserGuid());
            feedbackService.saveReply(replyDTO);
            model.put("success", true);
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }
}
