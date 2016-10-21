package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.utils.NumberUtils;
import cn.greenwishing.bms.utils.parser.SqlResultParser;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Wu Fan
 */
@Component
public class SuggestTemplateDTO {

    private Integer id;
    private String guid;
    private String name;
    private BillingType type;
    private String categoryGuid;
    private String subcategoryGuid;
    private String srcAccountGuid;
    private String targetAccountGuid;
    private String amount;

    public SuggestTemplateDTO(){
    }

    public SuggestTemplateDTO(SqlResultParser parser) {
        this.id = parser.nextInt();
        this.guid = parser.nextString();
        this.name = parser.nextString();
        this.type = parser.nextEnumWithName(BillingType.class);
        this.categoryGuid = parser.nextString();
        this.subcategoryGuid = parser.nextString();
        this.srcAccountGuid = parser.nextString();
        this.targetAccountGuid = parser.nextString();
        this.amount = NumberUtils.toString(parser.nextDecimal());
    }

    public static List<SuggestTemplateDTO> valueOf(List<SqlResultParser> parsers) {
        List<SuggestTemplateDTO> tplList = new ArrayList<>();
        for (SqlResultParser parser : parsers) {
            SuggestTemplateDTO tpl = new SuggestTemplateDTO(parser);
            tplList.add(tpl);
        }
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

    public String getCategoryGuid() {
        return categoryGuid;
    }

    public String getSubcategoryGuid() {
        return subcategoryGuid;
    }

    public String getSrcAccountGuid() {
        return srcAccountGuid;
    }

    public String getTargetAccountGuid() {
        return targetAccountGuid;
    }

    public String getAmount() {
        return amount;
    }
}
