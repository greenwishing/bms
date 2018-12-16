package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.OSSFile;

/**
 * @author Frank wu
 * @date 2017/5/29
 */
public interface OSSService {
    String upload(OSSFile file, String mode);

    String upload(String avatarUrl);
}
