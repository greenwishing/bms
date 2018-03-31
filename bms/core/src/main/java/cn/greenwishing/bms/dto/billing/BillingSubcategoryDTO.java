package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.BillingSubcategory;
import cn.greenwishing.bms.dto.Selectable;
import cn.greenwishing.bms.utils.parser.SqlResultParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 * @date 2015/3/7.
 */
public class BillingSubcategoryDTO implements Selectable {

    private Integer id;
    private String guid;
    private String name;

    private String categoryGuid;

    public BillingSubcategoryDTO() {
    }

    public BillingSubcategoryDTO(BillingSubcategory subcategory) {
        this.id = subcategory.id();
        this.guid = subcategory.guid();
        this.name = subcategory.name();
    }

    private BillingSubcategoryDTO(SqlResultParser subcategory) {
        this.id = subcategory.nextInt();
        this.guid = subcategory.nextString();
        this.name = subcategory.nextString();
        this.categoryGuid = subcategory.nextString();
    }

    public static List<BillingSubcategoryDTO> toDTO(List<BillingSubcategory> subcategories) {
        List<BillingSubcategoryDTO> subcategoryDTOs = new ArrayList<>();
        subcategories.forEach(subcategory -> subcategoryDTOs.add(new BillingSubcategoryDTO(subcategory)));
        return subcategoryDTOs;
    }

    public static List<BillingSubcategoryDTO> valueOf(List<SqlResultParser> subcategories) {
        List<BillingSubcategoryDTO> subcategoryDTOs = new ArrayList<>();
        subcategories.forEach(subcategory -> subcategoryDTOs.add(new BillingSubcategoryDTO(subcategory)));
        return subcategoryDTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCategoryGuid() {
        return categoryGuid;
    }

    public void setCategoryGuid(String categoryGuid) {
        this.categoryGuid = categoryGuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Serializable getValue() {
        return guid;
    }

    @Override
    public String getLabel() {
        return name;
    }
}
