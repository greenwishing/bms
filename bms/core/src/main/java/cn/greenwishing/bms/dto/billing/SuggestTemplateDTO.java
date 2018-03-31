package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.utils.NumberUtils;
import cn.greenwishing.bms.utils.parser.SqlResultParser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 */
@Component
public class SuggestTemplateDTO {

    private Integer id;
    private String guid;
    private String name;
    private BillingType type;
    private Integer categoryId;
    private Integer subcategoryId;
    private Integer srcAccountId;
    private Integer targetAccountId;
    private String amount;
    private String desc;

    public SuggestTemplateDTO(){
    }

    public SuggestTemplateDTO(SqlResultParser parser) {
        this.id = parser.nextInt();
        this.guid = parser.nextString();
        this.name = parser.nextString();
        this.type = parser.nextEnumWithName(BillingType.class);
        this.categoryId = parser.nextInt();
        this.subcategoryId = parser.nextInt();
        this.srcAccountId = parser.nextInt();
        this.targetAccountId = parser.nextInt();
        this.amount = NumberUtils.toString(parser.nextDecimal());
        this.desc = parser.nextString();
    }

    public static List<SuggestTemplateDTO> valueOf(List<SqlResultParser> parsers) {
        List<SuggestTemplateDTO> tplList = new ArrayList<>();
        parsers.forEach(parser -> tplList.add(new SuggestTemplateDTO(parser)));
        return tplList;
    }

    public Integer getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public BillingType getType() {
        return type;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public Integer getSrcAccountId() {
        return srcAccountId;
    }

    public Integer getTargetAccountId() {
        return targetAccountId;
    }

    public String getAmount() {
        return amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
