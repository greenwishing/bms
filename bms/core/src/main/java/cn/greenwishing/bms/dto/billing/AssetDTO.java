package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.dto.BmsNumber;
import cn.greenwishing.bms.utils.parser.SqlResultParser;

/**
 * User: Wufan
 * Date: 2016/10/30
 */
public class AssetDTO {

    // 账户总余额
    private BmsNumber asset;
    // 债权
    private BmsNumber credit;
    // 负债
    private BmsNumber debt;
    // 净资产
    private BmsNumber netAsset;

    public AssetDTO(SqlResultParser parser) {
        this.asset = parser.nextBmsNumber();
        this.credit = parser.nextBmsNumber();
        this.debt = parser.nextBmsNumber();
        this.netAsset = parser.nextBmsNumber();
    }

    public BmsNumber getAsset() {
        return asset;
    }

    public BmsNumber getCredit() {
        return credit;
    }

    public BmsNumber getDebt() {
        return debt;
    }

    public BmsNumber getNetAsset() {
        return netAsset;
    }
}
