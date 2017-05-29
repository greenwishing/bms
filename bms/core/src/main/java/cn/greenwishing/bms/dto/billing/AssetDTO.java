package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.dto.SigNum;
import cn.greenwishing.bms.utils.parser.SqlResultParser;

/**
 * User: Wufan
 * Date: 2016/10/30
 */
public class AssetDTO {

    // 账户总余额
    private SigNum asset;
    // 债权
    private SigNum credit;
    // 负债
    private SigNum debt;
    // 净资产
    private SigNum netAsset;

    public AssetDTO(SqlResultParser parser) {
        this.asset = parser.nextBmsNumber();
        this.credit = parser.nextBmsNumber();
        this.debt = parser.nextBmsNumber();
        this.netAsset = parser.nextBmsNumber();
    }

    public SigNum getAsset() {
        return asset;
    }

    public SigNum getCredit() {
        return credit;
    }

    public SigNum getDebt() {
        return debt;
    }

    public SigNum getNetAsset() {
        return netAsset;
    }
}
