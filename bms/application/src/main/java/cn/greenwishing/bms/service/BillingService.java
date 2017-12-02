package cn.greenwishing.bms.service;

import cn.greenwishing.bms.domain.billing.BillingStatus;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.dto.billing.*;
import cn.greenwishing.bms.dto.statistics.highcharts.Series;
import cn.greenwishing.bms.dto.statistics.mobiscroll.Wheel;
import cn.greenwishing.bms.dto.statistics.tree.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * @author Frank wu
 */
public interface BillingService {

    BillingPagingDTO loadBillingPaging(BillingPagingDTO pagingDTO);

    void saveOrUpdateBilling(BillingDTO billingDTO);

    BillingDTO loadBillingByGuid(String guid);

    void deleteBillingByGuid(String guid);

    List<Series> loadNearestStatistics(Integer size, BillingType billingType);

    List<BillingCategoryDTO> loadBillingCategory();

    BillingCategoryDTO loadBillingCategoryByGuid(String guid);

    void saveOrUpdateBillingCategory(BillingCategoryDTO categoryDTO);

    BillingSubcategoryDTO loadBillingSubcategoryByGuid(String guid);

    void saveOrUpdateBillingSubcategory(BillingSubcategoryDTO subcategoryDTO);

    List<BillingSubcategoryDTO> loadBillingSubcategory(String categoryGuid);

    List<BillingCategoryDTO> loadBillingCategoryByType(BillingType billingType);

    List<BillingStatistics> loadBillingStatistics(String type, String fromDateStr, String toDateStr);

    void changeStatus(String guid, BillingStatus status);

    void generateDefaultCategory();

    List<BillingAccountDTO> loadBillingAccounts();

    BillingAccountDTO loadBillingAccountByGuid(String guid);

    void saveOrUpdateBillingAccount(BillingAccountDTO accountDTO);

    List<SuggestTemplateDTO> loadSuggestTemplate(BillingType type, Integer size);

    AssetDTO loadAsset();

    Map<String, Float> loadBillingMapData(String typeStr, String yearStr);

    List<Wheel> loadBillingWheels(String userGuid);

    List<TreeNode> loadBillingTreeNodes(String userGuid);
}
