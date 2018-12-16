package cn.greenwishing.bms.web.controller.api.weixin;

import cn.greenwishing.bms.api.weixin.weapp.JSCode2SessionRequest;
import cn.greenwishing.bms.api.weixin.weapp.JSCode2SessionResponse;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.dto.article.ArticleCategoryDTO;
import cn.greenwishing.bms.dto.billing.BillingAccountDTO;
import cn.greenwishing.bms.dto.billing.BillingDTO;
import cn.greenwishing.bms.dto.billing.BillingPagingDTO;
import cn.greenwishing.bms.dto.feedback.FeedbackDTO;
import cn.greenwishing.bms.dto.feedback.FeedbackPagingDTO;
import cn.greenwishing.bms.dto.open.OpenUserDTO;
import cn.greenwishing.bms.dto.open.WeAppUserInfo;
import cn.greenwishing.bms.dto.statistics.highcharts.Series;
import cn.greenwishing.bms.dto.statistics.tree.BillingAccountTypeNode;
import cn.greenwishing.bms.dto.statistics.tree.TreeNode;
import cn.greenwishing.bms.dto.user.UserDTO;
import cn.greenwishing.bms.service.*;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.web.controller.api.ApiResult;
import cn.greenwishing.bms.web.validator.BillingValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wufan
 * @date 2018/1/6
 */
@Controller
@RequestMapping("/api/weixin/weapp")
public class WeAppApiController {

    @Resource
    private UserService userService;
    @Resource
    private WeAppService weAppService;
    @Resource
    private BillingService billingService;
    @Resource
    private ArticleService articleService;
    @Resource
    private FeedbackService feedbackService;

    /**
     * 小程序用户注册
     */
    @RequestMapping("register")
    public ModelAndView register(String code, WeAppUserInfo userInfo) {
        ApiResult result;
        try {
            JSCode2SessionResponse response = new JSCode2SessionRequest(code).execute();
            userInfo.setOpenid(response.getOpenid());
            OpenUserDTO openUserDTO = weAppService.register(userInfo);
            result = ApiResult.success()
                    .add("openid", response.getOpenid())
                    .add("userGuid", openUserDTO.getUserGuid())
                    .add("userAccount", openUserDTO.getUserAccount());
        } catch (Exception e) {
            result = ApiResult.fail(-1, e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 小程序用户登录
     */
    @RequestMapping("login")
    public ModelAndView login(String openid) {
        ApiResult result;
        try {
            UserDTO user = weAppService.loadUserByOpenid(openid);
            if (user == null) {
                result = ApiResult.fail(-1, "未关联用户");
            } else {
                String userGuid = user.getGuid();
                result = ApiResult.success()
                        .add("userGuid", userGuid)
                        .add("userAccount", user.getAccount());
            }
        } catch (Exception e) {
            result = ApiResult.fail(-1, e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 小程序设置帐号密码
     */
    @RequestMapping("account")
    public ModelAndView account(String userGuid, String account, String password) {
        ApiResult result;
        try {
            weAppService.updateAccount(userGuid, account, password);
            result = ApiResult.success();
        } catch (Exception e) {
            result = ApiResult.fail(-1, e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    @RequestMapping("checkupdate")
    public ModelAndView checkUpdate(String version) {
        ApiResult result = ApiResult.success()
                .add("update", true);
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 小程序获取账单类型，账户、文章分类等信息
     */
    @RequestMapping("data")
    public ModelAndView data(String userGuid) {
        ApiResult result;
        if (ValidationUtils.isEmpty(userGuid)) {
            result = ApiResult.fail(-1, "未登录");
        } else {
            try {
                List<TreeNode> billingTypes = billingService.loadBillingTreeNodes(userGuid);
                List<BillingAccountDTO> accountDTOs = billingService.loadBillingAccounts(userGuid);

                List<BillingAccountTypeNode> billingAccounts = new ArrayList<>();
                accountDTOs.stream()
                        .collect(Collectors.groupingBy(BillingAccountDTO::getType))
                        .forEach((accountType, accounts) -> {
                            BillingAccountTypeNode node = new BillingAccountTypeNode(accountType);
                            accounts.forEach(account -> {
                                node.addChild(new TreeNode(account));
                            });
                            billingAccounts.add(node);
                        });
                List<ArticleCategoryDTO> articleCategories = articleService.loadArticleCategories(userGuid);
                result = ApiResult.success()
                        .add("billingTypes", billingTypes)
                        .add("billingAccounts", billingAccounts)
                        .add("articleCategories", articleCategories)
                ;
            } catch (Exception e) {
                result = ApiResult.fail(-1, e.getMessage());
            }
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 记账
     */
    @RequestMapping("addbilling")
    public ModelAndView addBilling(BillingDTO billingDTO, BindingResult errors) {
        ApiResult result;
        new BillingValidator().validate(billingDTO, errors);
        if (errors.hasErrors()) {
            result = ApiResult.fail(-1, errors.getFieldError().getDefaultMessage());
        } else {
            billingService.saveOrUpdateBilling(billingDTO);
            result = ApiResult.success();
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 账单
     */
    @RequestMapping("billingdata")
    public ModelAndView billingData(BillingPagingDTO pagingDTO) {
        ApiResult result;
        if (ValidationUtils.isEmpty(pagingDTO.getUserGuid())) {
            result = ApiResult.fail(-1, "未登录");
        } else {
            BillingPagingDTO paging = billingService.loadBillingPaging(pagingDTO);
            result = ApiResult.success().add("paging", paging);
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    @RequestMapping("nearest")
    public ModelAndView nearest(String userGuid, @RequestParam(defaultValue = "20") Integer size, @RequestParam(defaultValue = "EXPEND") BillingType type) {
        ApiResult result;
        if (ValidationUtils.isEmpty(userGuid)) {
            result = ApiResult.fail(-1, "未登录");
        } else {
            List<Series> series = billingService.loadNearestStatistics(size, type, userGuid);
            result = ApiResult.success().add("series", series);
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    @RequestMapping("statistics")
    public ModelAndView statistics(String userGuid, String type, String from, String to) {
        ApiResult result;
        if (ValidationUtils.isEmpty(userGuid)) {
            result = ApiResult.fail(-1, "未登录");
        } else {
            List<BillingStatistics> data = billingService.loadBillingStatistics(type, from, to, userGuid);
            result = ApiResult.success().add("data", data);
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 我的反馈记录
     */
    @RequestMapping("feedbackdata")
    public ModelAndView loadFeedbackData(FeedbackPagingDTO pagingDTO) {
        ApiResult result;
        if (ValidationUtils.isEmpty(pagingDTO.getUserGuid())) {
            result = ApiResult.fail(-1, "未登录");
        } else {
            FeedbackPagingDTO paging = feedbackService.loadFeedbackPaging(pagingDTO);
            result = ApiResult.success().add("paging", paging);
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 反馈
     */
    @RequestMapping("addfeedback")
    public ModelAndView addFeedback(FeedbackDTO feedbackDTO, BindingResult errors) {
        ApiResult result;
        if (ValidationUtils.isEmpty(feedbackDTO.getUserGuid())) {
            result = ApiResult.fail(-1, "未登录");
        } else if (ValidationUtils.isEmpty(feedbackDTO.getContent())) {
            result = ApiResult.fail(-1, "请输入反馈内容");
        } else {
            feedbackService.saveFeedback(feedbackDTO);
            result = ApiResult.success();
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 回复反馈
     */
    @RequestMapping("replyfeedback")
    public ModelAndView replyFeedback(FeedbackDTO feedbackDTO, BindingResult errors) {
        ApiResult result;
        if (ValidationUtils.isEmpty(feedbackDTO.getUserGuid())) {
            result = ApiResult.fail(-1, "未登录");
        } else if (ValidationUtils.isEmpty(feedbackDTO.getFeedbackGuid())) {
            result = ApiResult.fail(-1, "未指定反馈记录");
        } else if (ValidationUtils.isEmpty(feedbackDTO.getContent())) {
            result = ApiResult.fail(-1, "请输入回复内容");
        } else {
            feedbackService.saveReply(feedbackDTO);
            result = ApiResult.success();
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 回复反馈
     */
    @RequestMapping("openid_list")
    public ModelAndView openidList() {
        List<OpenUserDTO> users = weAppService.loadAllOpenUser();
        List<String> openidList = new ArrayList<>();
        for (OpenUserDTO user : users) {
            openidList.add(user.getOpenid());
        }
        ApiResult result = ApiResult.success();
        result.add("openid_list", openidList);
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 回复反馈
     */
    @RequestMapping("upload")
    public ModelAndView upload(String openid) {
        ApiResult result;
        try {
            weAppService.uploadAvatar(openid);
            result = ApiResult.success();
        }catch (Exception e) {
            result = ApiResult.fail(-1, e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }
}
