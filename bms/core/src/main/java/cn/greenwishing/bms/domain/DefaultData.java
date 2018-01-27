package cn.greenwishing.bms.domain;

import cn.greenwishing.bms.domain.billing.BillingAccountType;
import cn.greenwishing.bms.domain.billing.BillingType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Frank wu
 * @date 2016/7/19
 */
public class DefaultData {

    public enum DefaultBillingCategory {
        L1(BillingType.EXPEND, "居家物业", "水费", "电费", "天然气费", "物业费", "房租", "家用电器", "日常用品"),
        L2(BillingType.EXPEND, "行车交通", "充公交卡次数", "充电子钱包", "打的", "坐公交", "火车票", "汽车票", "坐地铁"),
        L3(BillingType.EXPEND, "衣服饰品", "衣服裤子", "鞋帽包包", "护理饰品"),
        L4(BillingType.EXPEND, "休闲娱乐", "旅游度假", "休闲玩乐"),
        L5(BillingType.EXPEND, "食品酒水", "水果零食", "买菜", "酒水饮料", "牛奶", "柴米油盐酱醋茶"),
        L6(BillingType.EXPEND, "个人护理", "理发", "护理饰品"),
        L7(BillingType.EXPEND, "金融保险", "房贷", "房贷"),
        L8(BillingType.EXPEND, "人情往来", "送礼"),

        L9(BillingType.INCOME, "工资", "工资"),
        L10(BillingType.TRANSFER, "现金", "现金"),
        L11(BillingType.BORROW, "现金", "现金"),
        L12(BillingType.LOAN, "现金", "现金"),
        L13(BillingType.RECEIVE, "现金", "现金"),
        L14(BillingType.PAYBACK, "现金", "现金"),
        ;

        public BillingType type;
        public String name;
        public List<String> subcategories = new ArrayList<>();

        DefaultBillingCategory(BillingType type, String name, String... subcategories) {
            this.type = type;
            this.name = name;
            if (subcategories != null) {
                this.subcategories = Arrays.asList(subcategories);
            }
        }

        public DefaultBillingCategory add(String subcategory) {
            this.subcategories.add(subcategory);
            return this;
        }
    }

    public enum DefaultBillingAccount {
        L1(BillingAccountType.CASH, "现金账户"),
        L2(BillingAccountType.CREDIT_CARD, "信用卡账户"),
        L3(BillingAccountType.DEPOSIT_CARD, "银行卡"),
        L4(BillingAccountType.VIRTUAL, "支付宝", "微信"),
        L5(BillingAccountType.INDEBTED, "负债账户"),
        L6(BillingAccountType.LOAN, "债权账户"),
        ;

        public BillingAccountType type;
        public List<String> names = new ArrayList<>();

        DefaultBillingAccount(BillingAccountType type, String... names) {
            this.type = type;
            if (names != null) {
                this.names = Arrays.asList(names);
            }
        }
    }

    public enum DefaultArticleCategory {
        L1("随记"),;

        public String name;

        DefaultArticleCategory(String name) {
            this.name = name;
        }
    }

    public enum DefaultMomentType {
        L1("学习"),
        L2("阅读"),
        L3("运动"),
        L4("睡眠"),
        L5("交通"),
        L6("工作"),
        L7("用餐"),
        L8("购物"),
        L9("娱乐"),
        ;

        public String name;

        DefaultMomentType(String name) {
            this.name = name;
        }
    }
}
