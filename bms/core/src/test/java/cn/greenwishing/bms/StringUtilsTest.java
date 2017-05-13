package cn.greenwishing.bms;

import cn.greenwishing.bms.utils.StringUtils;
import org.testng.annotations.Test;

/**
 * User: Wufan
 * Date: 2017/5/13
 */
public class StringUtilsTest {

    @Test
    public void test() {
        for (int i =1; i< 1000; i ++) {
            String random = StringUtils.random(8);
            System.out.println(random);
        }
    }
}
