package cn.greenwishing.bms.web.validator;

import cn.greenwishing.bms.dto.ArticleDTO;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Wu Fan
 */
public class ArticleValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ArticleDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ArticleDTO articleDTO = (ArticleDTO) o;
        String content = articleDTO.getContent();
        if (ValidationUtils.isEmpty(content)) {
            errors.rejectValue("content", "content", "请输入文章内容");
        }
    }
}
