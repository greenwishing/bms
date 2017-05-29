package cn.greenwishing.bms.web.controller;

import cn.greenwishing.bms.dto.OSSFile;
import cn.greenwishing.bms.service.OSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * User: Wufan
 * Date: 2017/5/29
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private OSSService ossService;

    @RequestMapping("upload/{mode}")
    public ModelAndView upload(@PathVariable String mode, MultipartFile file) throws Exception {
        ModelMap model = new ModelMap();
        try {
            OSSFile ossFile = new OSSFile(file.getName(), file.getOriginalFilename(), file.getContentType(), file.getBytes());
            String key = ossService.upload(ossFile, mode);
            model.put("success", true);
            model.put("key", key);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("success", false);
            model.put("message", "文件上传失败");
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
