package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.BillingAccount;
import cn.greenwishing.bms.domain.billing.BillingAccountType;
import cn.greenwishing.bms.dto.AbstractDTO;
import cn.greenwishing.bms.dto.Selectable;
import cn.greenwishing.bms.utils.NumberUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 * @date 2016/10/16
 */
public class BillingAccountDTO extends AbstractDTO implements Selectable {

    private Integer id;
    private String guid;
    private String name;
    private BillingAccountType type;
    private String balance;
    private int signum;

    public BillingAccountDTO() {
    }

    public BillingAccountDTO(BillingAccount account) {
        this.id = account.id();
        this.guid = account.guid();
        this.name = account.name();
        this.type = account.type();
        BigDecimal balance = account.balance();
        this.balance = NumberUtils.toString(balance);
        this.signum = balance.signum();
    }

    public static List<BillingAccountDTO> toDTOs(List<BillingAccount> accounts) {
        List<BillingAccountDTO> accountDTOs = new ArrayList<>();
        accounts.forEach(account -> accountDTOs.add(new BillingAccountDTO(account)));
        return accountDTOs;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BillingAccountType getType() {
        return type;
    }

    public boolean isLoan() {
        return type.isLoan();
    }

    public String getTypeLabel() {
        return type.getLabel();
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

    public int getSignum() {
        return signum;
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
