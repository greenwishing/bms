package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.DefaultData;
import cn.greenwishing.bms.domain.billing.*;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.billing.*;
import cn.greenwishing.bms.dto.statistics.highcharts.Series;
import cn.greenwishing.bms.dto.statistics.mobiscroll.Wheel;
import cn.greenwishing.bms.dto.statistics.mobiscroll.WheelData;
import cn.greenwishing.bms.dto.statistics.tree.TreeNode;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.shared.EnumUtils;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.NumberUtils;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import cn.greenwishing.bms.utils.parser.SqlResultParser;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Frank wu
 */
@Service("billService")
public class BillingServiceImpl implements BillingService {

    @Resource
    private BillingRepository billingRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public BillingPagingDTO loadBillingPaging(BillingPagingDTO pagingDTO) {
        BillingPaging paging = billingRepository.findBillingByPaging(pagingDTO.toPaging());
        return pagingDTO.convertResult(paging);
    }

    @Override
    public void saveOrUpdateBilling(BillingDTO billingDTO) {
        String amountStr = billingDTO.getAmount();
        BigDecimal amount = NumberUtils.parseDecimal(amountStr, BigDecimal.ZERO);

        BillingType billingType = billingDTO.getType();
        String categoryGuid = billingDTO.getCategoryGuid();
        String subcategoryGuid = billingDTO.getSubcategoryGuid();
        String srcAccountGuid = billingDTO.getSrcAccountGuid();
        String targetAccountGuid = billingDTO.getTargetAccountGuid();

        BillingCategory category = billingRepository.findByGuid(BillingCategory.class, categoryGuid);;
        BillingSubcategory subcategory = billingRepository.findByGuid(BillingSubcategory.class, subcategoryGuid);;
        BillingAccount srcAccount = null;
        BillingAccount targetAccount = null;
        switch (billingType) {
            case EXPEND: // 支出
                srcAccount = accountSubtractAmount(srcAccountGuid, amount);
                break;
            case INCOME: // 收入
                srcAccount = accountAddAmount(srcAccountGuid, amount);
                break;
            case TRANSFER: // 转账
                srcAccount = accountSubtractAmount(srcAccountGuid, amount);
                targetAccount = accountAddAmount(targetAccountGuid, amount);
                break;
            case BORROW: // 借入
                srcAccount = accountAddAmount(srcAccountGuid, amount);
                targetAccount = accountSubtractAmount(targetAccountGuid, amount);
                break;
            case LOAN: // 借出/代付
                srcAccount = accountSubtractAmount(srcAccountGuid, amount);
                targetAccount = accountAddAmount(targetAccountGuid, amount);
                break;
            case RECEIVE: // 收款
                srcAccount = accountAddAmount(srcAccountGuid, amount);
                targetAccount = accountSubtractAmount(targetAccountGuid, amount);
                break;
            case PAYBACK: // 还款
                srcAccount = accountSubtractAmount(srcAccountGuid, amount);
                targetAccount = accountAddAmount(targetAccountGuid, amount);
                break;
            default:
                throw new RuntimeException("不支持的类型：" + billingType.getLabel());
        }
        billingRepository.saveOrUpdate(srcAccount);
        if (targetAccount != null) {
            billingRepository.saveOrUpdate(targetAccount);
        }

        BillingStatus status = BillingStatus.NORMAL;
        String guid = SecurityHolder.getUserGuid();
        User user = userRepository.findByGuid(User.class, guid);

        DateTime occurredTime = JodaUtils.now();
        String occurredTimeStr = billingDTO.getOccurredTime();
        if (ValidationUtils.isNotEmpty(occurredTimeStr)) {
            occurredTime = JodaUtils.parseDateTime(occurredTimeStr, JodaUtils.DATE_TIME_FORMAT);
        }

        String name = billingDTO.getName();
        BillingType type = billingDTO.getType();
        Billing billing = new Billing(name, type, category, subcategory, srcAccount, targetAccount, amount,
                billingDTO.getDescription(), occurredTime, user);
        billing.updateStatus(status);
        billingRepository.saveOrUpdate(billing);
    }

    @Override
    public BillingDTO loadBillingByGuid(String guid) {
        Billing billing = billingRepository.findByGuid(Billing.class, guid);
        if (billing != null) {
            return new BillingDTO(billing);
        }
        return null;
    }

    private BillingAccount accountAddAmount(String accountGuid, BigDecimal amount) {
        BillingAccount billingAccount = billingRepository.findByGuid(BillingAccount.class, accountGuid);
        billingAccount.addAmount(amount);
        billingRepository.saveOrUpdate(billingAccount);
        return billingAccount;
    }

    private BillingAccount accountSubtractAmount(String accountGuid, BigDecimal amount) {
        BillingAccount billingAccount = billingRepository.findByGuid(BillingAccount.class, accountGuid);
        billingAccount.subtractAmount(amount);
        billingRepository.saveOrUpdate(billingAccount);
        return billingAccount;
    }

    @Override
    public void deleteBillingByGuid(String guid) {
        billingRepository.remove(Billing.class, guid);
    }

    @Override
    public List<Series> loadNearestStatistics(Integer size, BillingType billingType) {
        List<Series> series = new ArrayList<>();

        Series result = loadSeriesObject(billingType, size);
        series.add(result);

        return series;
    }

    private Series loadSeriesObject(BillingType billingType, Integer size) {
        List<Object[]> results = billingRepository.loadNearestStatistics(billingType, size);
        return Series.valueOf(billingType, results);
    }

    @Override
    public List<BillingCategoryDTO> loadBillingCategory() {
        String userGuid = SecurityHolder.getUserGuid();
        List<SqlResultParser> categories = billingRepository.findBillingCategoryByUserGuid(userGuid);
        return BillingCategoryDTO.toDTOs(categories);
    }

    @Override
    public BillingCategoryDTO loadBillingCategoryByGuid(String guid) {
        BillingCategory category = billingRepository.findByGuid(BillingCategory.class, guid);
        return new BillingCategoryDTO(category);
    }

    @Override
    public void saveOrUpdateBillingCategory(BillingCategoryDTO categoryDTO) {
        BillingCategory category;
        String guid = categoryDTO.getGuid();
        if (ValidationUtils.isNotEmpty(guid)) {
            category = billingRepository.findByGuid(BillingCategory.class, guid);
        } else {
            String userGuid = SecurityHolder.getUserGuid();
            User user = userRepository.findByGuid(User.class, userGuid);
            category = new BillingCategory(user);
        }
        category.update(categoryDTO.getType(), categoryDTO.getName());
        billingRepository.saveOrUpdate(category);
    }

    @Override
    public BillingSubcategoryDTO loadBillingSubcategoryByGuid(String guid) {
        BillingSubcategory subcategory = billingRepository.findByGuid(BillingSubcategory.class, guid);
        return new BillingSubcategoryDTO(subcategory);
    }

    @Override
    public void saveOrUpdateBillingSubcategory(BillingSubcategoryDTO subcategoryDTO) {
        BillingSubcategory subcategory;
        String guid = subcategoryDTO.getGuid();
        if (ValidationUtils.isNotEmpty(guid)) {
            subcategory = billingRepository.findByGuid(BillingSubcategory.class, guid);
        } else {
            String categoryGuid = subcategoryDTO.getCategoryGuid();
            BillingCategory category = billingRepository.findByGuid(BillingCategory.class, categoryGuid);
            subcategory = new BillingSubcategory(category);
        }
        subcategory.update(subcategoryDTO.getName());
        billingRepository.saveOrUpdate(subcategory);
    }

    @Override
    public List<BillingSubcategoryDTO> loadBillingSubcategory(String categoryGuid) {
        List<BillingSubcategory> subcategories = billingRepository.findBillingSubcategory(categoryGuid);
        return BillingSubcategoryDTO.toDTO(subcategories);
    }

    @Override
    public List<BillingCategoryDTO> loadBillingCategoryByType(BillingType billingType) {
        String userGuid = SecurityHolder.getUserGuid();
        List<SqlResultParser> categories = billingRepository.findBillingCategoryByType(billingType, userGuid);
        return BillingCategoryDTO.toDTOs(categories);
    }

    @Override
    public List<BillingStatistics> loadBillingStatistics(String type, String fromDateStr, String toDateStr) {
        BillingType billingType = EnumUtils.nameOf(BillingType.class, type);
        if (billingType == null) {
            billingType = BillingType.EXPEND;
        }
        LocalDate startDate = null;
        LocalDate endDate = null;
        if (ValidationUtils.isValidDate(fromDateStr) && ValidationUtils.isValidDate(toDateStr)) {
            startDate = JodaUtils.parseLocalDate(fromDateStr);
            endDate = JodaUtils.parseLocalDate(toDateStr);
        }
        if (startDate == null || endDate == null) {
            return Collections.emptyList();
        }
        String userGuid = SecurityHolder.getUserGuid();
        return billingRepository.loadBillingStatistics(userGuid, billingType, startDate, endDate);
    }

    @Override
    public void changeStatus(String guid, BillingStatus status) {
        Billing billing = billingRepository.findByGuid(Billing.class, guid);
        if (billing != null) {
            billing.updateStatus(status);
            billingRepository.saveOrUpdate(billing);
        }
    }

    @Override
    public void generateDefaultCategory() {
        String userGuid = SecurityHolder.getUserGuid();
        User user = userRepository.findByGuid(User.class, userGuid);
        if (user == null) {
            return;
        }

        for (DefaultData.DefaultBillingCategory defaultBillingCategory : DefaultData.DefaultBillingCategory.values()) {
            BillingCategory billingCategory = new BillingCategory(user);
            billingCategory.update(defaultBillingCategory.type, defaultBillingCategory.name);
            userRepository.saveOrUpdate(billingCategory);
            for (String defaultSubcategory : defaultBillingCategory.subcategories) {
                BillingSubcategory billingSubcategory = new BillingSubcategory(billingCategory);
                billingSubcategory.update(defaultSubcategory);
                userRepository.saveOrUpdate(billingSubcategory);
            }
        }
    }

    @Override
    public List<BillingAccountDTO> loadBillingAccounts() {
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingAccount> accounts = billingRepository.findBillingAccounts(userGuid);
        return BillingAccountDTO.toDTOs(accounts);
    }

    @Override
    public BillingAccountDTO loadBillingAccountByGuid(String guid) {
        BillingAccount billingAccount = billingRepository.findByGuid(BillingAccount.class, guid);
        return new BillingAccountDTO(billingAccount);
    }

    @Override
    public void saveOrUpdateBillingAccount(BillingAccountDTO accountDTO) {
        String guid = accountDTO.getGuid();
        BillingAccount account;
        if (ValidationUtils.isNotEmpty(guid)) {
            account = billingRepository.findByGuid(BillingAccount.class, guid);
        } else {
            String userGuid = SecurityHolder.getUserGuid();
            User user = userRepository.findByGuid(User.class, userGuid);
            account = new BillingAccount(user);
        }
        BillingAccountType type = accountDTO.getType();
        String name = accountDTO.getName();
        BigDecimal balance = BigDecimal.ZERO;
        if (ValidationUtils.isAllNumber(accountDTO.getBalance())) {
            balance = new BigDecimal(accountDTO.getBalance());
        }
        account.update(type, name, balance);
        userRepository.saveOrUpdate(account);
    }

    @Override
    public List<SuggestTemplateDTO> loadSuggestTemplate(BillingType type, Integer size) {
        Integer userId = SecurityHolder.getUserId();
        List<SqlResultParser> parsers = billingRepository.findSuggestTemplate(type, userId, size);
        return SuggestTemplateDTO.valueOf(parsers);
    }

    @Override
    public AssetDTO loadAsset() {
        Integer userId = SecurityHolder.getUserId();
        SqlResultParser parser = billingRepository.findAssertData(userId);
        return new AssetDTO(parser);
    }

    @Override
    public Map<String, Float> loadBillingMapData(String typeStr, String yearStr) {
        BillingType billingType = EnumUtils.nameOf(BillingType.class, typeStr);
        if (billingType == null) {
            billingType = BillingType.EXPEND;
        }
        Integer year = JodaUtils.now().getYear();
        if (ValidationUtils.isPositiveNumber(yearStr)) {
            year = Integer.valueOf(yearStr);
        }
        Integer userId = SecurityHolder.getUserId();
        return billingRepository.findBillingMapData(userId, billingType, year);
    }

    @Override
    public List<Wheel> loadBillingWheels(String userGuid) {
        Wheel firstLevel = new Wheel("类型");
        firstLevel.add(new WheelData(-1, "", "全部"));
        for (BillingType billingType : BillingType.values()) {
            firstLevel.add(new WheelData(billingType.ordinal(), billingType.getValue(), billingType.getLabel()));
        }
        Wheel secondLevel = new Wheel("分类");
        secondLevel.add(new WheelData(-1, "", "全部").setSign(""));
        List<BillingCategoryDTO> categories = loadBillingCategory();
        for (BillingCategoryDTO category : categories) {
            secondLevel.add(new WheelData(category.getId(), category.getGuid(), category.getName()).setSign(category.getType().getValue()));
        }
        Wheel thirdLevel = new Wheel("子分类");
        thirdLevel.add(new WheelData(-1, "", "全部").setSign(""));
        List<BillingSubcategoryDTO> subcategories = loadBillSubcategories(userGuid);
        for (BillingSubcategoryDTO subcategory : subcategories) {
            thirdLevel.add(new WheelData(subcategory.getId(), subcategory.getGuid(), subcategory.getName()).setSign(subcategory.getCategoryGuid()));
        }
        return Arrays.asList(firstLevel.sort(), secondLevel.sort(), thirdLevel.sort());
    }

    @Override
    public List<TreeNode> loadBillingTreeNodes(String userGuid) {
        List<TreeNode> typeNodes = new ArrayList<>();
        List<BillingCategoryDTO> categories = loadBillingCategory();
        List<BillingSubcategoryDTO> subcategories = loadBillSubcategories(userGuid);
        for (BillingType type : BillingType.values()) {
            TreeNode typeNode = new TreeNode(type.getValue(), type.getLabel());
            for (BillingCategoryDTO category : categories) {
                if (type == category.getType()) {
                    TreeNode categoryNode = new TreeNode(category.getGuid(), category.getName());
                    for (BillingSubcategoryDTO subcategory : subcategories) {
                        if (category.getGuid().equals(subcategory.getCategoryGuid())) {
                            TreeNode subcategoryNode = new TreeNode(subcategory.getGuid(), subcategory.getName());
                            categoryNode.addChild(subcategoryNode);
                        }
                    }
                    typeNode.addChild(categoryNode);
                }
            }
            typeNodes.add(typeNode);
        }
        return typeNodes;
    }

    private List<BillingSubcategoryDTO> loadBillSubcategories(String userGuid) {
        List<SqlResultParser> parsers = billingRepository.findBillingSubcategoryByUserGuid(userGuid);
        return BillingSubcategoryDTO.valueOf(parsers);
    }
}
