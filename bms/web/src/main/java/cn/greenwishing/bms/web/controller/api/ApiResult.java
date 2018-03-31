package cn.greenwishing.bms.web.controller.api;

import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wufan
 * @date 2018/1/6
 */
public class ApiResult {

    private Integer code;
    private String message;
    private Map<String, Object> params = new HashMap<>();

    private ApiResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiResult success() {
        return new ApiResult(0, "成功");
    }

    public static ApiResult fail(Integer code, String message) {
        return new ApiResult(code, message);
    }

    public ModelMap toModelMap() {
        ModelMap model = new ModelMap();
        model.put("success", isSuccess());
        model.put("code", getCode());
        model.put("message", getMessage());
        if (!params.isEmpty()) {
            model.putAll(params);
        }
        return model;
    }

    public ApiResult add(String name, Object value) {
        params.put(name, value);
        return this;
    }

    public boolean isSuccess() {
        return code != null && code.equals(0);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
