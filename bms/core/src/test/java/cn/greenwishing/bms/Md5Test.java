package cn.greenwishing.bms;

import cn.greenwishing.bms.utils.MD5Utils;
import org.testng.annotations.Test;

/**
 * @author Wufan
 * @date 2017/10/23
 */
public class Md5Test {

    @Test
    public void test() {
        System.out.println(MD5Utils.md5("admin"));
    }
}
