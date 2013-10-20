package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.service.FileService;
import cn.greenwishing.bms.utils.BMSProperties;
import com.baidu.inf.iis.bcs.BaiduBCS;

/**
 * @author Wu Fan
 */
public class FileServiceImpl implements FileService {

    public static final String DEFAULT_ENCODING = BMSProperties.get("baidu.bcs.default.encoding");

    private BaiduBCS baiduBCS;


    public void setBaiduBCS(BaiduBCS baiduBCS) {
        baiduBCS.setDefaultEncoding(DEFAULT_ENCODING);
        this.baiduBCS = baiduBCS;
    }
}
