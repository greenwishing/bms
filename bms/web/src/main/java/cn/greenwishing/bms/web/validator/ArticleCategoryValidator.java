package cn.greenwishing.bms.web.validator;

import cn.greenwishing.bms.dto.article.ArticleCategoryDTO;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Wu Fan
 */
public class ArticleCategoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ArticleCategoryDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ArticleCategoryDTO categoryDTO = (ArticleCategoryDTO) o;

        String name = categoryDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入分类名称");
        }
    }
}
