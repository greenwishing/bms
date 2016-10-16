package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.BillingAccount;
import cn.greenwishing.bms.domain.billing.BillingAccountType;
import cn.greenwishing.bms.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Wufan
 * Date: 2016/10/16
 */
public class BillingAccountDTO {

    private String guid;
    private String name;
    private BillingAccountType type;
    private String balance;

    public BillingAccountDTO() {
    }

    public BillingAccountDTO(BillingAccount account) {
        this.guid = account.guid();
        this.name = account.name();
        this.type = account.type();
        this.balance = NumberUtils.toString(account.balance());
    }

    public static List<BillingAccountDTO> toDTOs(List<BillingAccount> accounts) {
        List<BillingAccountDTO> accountDTOs = new ArrayList<>();
        for (BillingAccount account : accounts) {
            BillingAccountDTO accountDTO = new BillingAccountDTO(account);
            accountDTOs.add(accountDTO);
        }
        return accountDTOs;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BillingAccountType getType() {
        return type;
    }

    public void setType(BillingAccountType type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
