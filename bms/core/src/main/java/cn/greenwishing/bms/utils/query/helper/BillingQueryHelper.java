package cn.greenwishing.bms.utils.query.helper;

import cn.greenwishing.bms.domain.billing.Billing;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import org.hibernate.Query;
import org.joda.time.LocalDate;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author Wu Fan
 */
public class BillingQueryHelper extends AbstractQueryHelper<Billing, BillingPaging> {

    public BillingQueryHelper(HibernateTemplate hibernateTemplate, BillingPaging paging) {
        super(hibernateTemplate);
        this.paging = paging;

        String key = paging.getKey();
        if (ValidationUtils.isNotEmpty(key)) {
            addFilter(keyFilter(key));
        }

        BillingType type = paging.getType();
        if (type != null) {
            addFilter(typeFilter(type));
        }

        String categoryGuid = paging.getCategoryGuid();
        if (ValidationUtils.isNotEmpty(categoryGuid)) {
            addFilter(categoryFilter(categoryGuid));
        }
        String subcategoryGuid = paging.getSubcategoryGuid();
        if (ValidationUtils.isNotEmpty(subcategoryGuid)) {
            addFilter(subcategoryFilter(subcategoryGuid));
        }

        String dateFrom = paging.getDateFrom();
        String dateTo = paging.getDateTo();
        if (ValidationUtils.isValidDate(dateFrom) && ValidationUtils.isValidDate(dateTo)) {
            LocalDate from = JodaUtils.parseLocalDate(dateFrom);
            LocalDate to = JodaUtils.parseLocalDate(dateTo).plusDays(1);
            addFilter(dateRangeFilter(from, to));
        }
    }

    private Filter subcategoryFilter(final String subcategoryGuid) {
        return new ParameterFilter() {
            @Override
            public void setParameter(Query query) {
                query.setParameter("subcategoryGuid", subcategoryGuid);
            }

            @Override
            public String getSubHql() {
                return " and b.subcategory.guid=:subcategoryGuid";
            }
        };
    }

    private Filter categoryFilter(final String categoryGuid) {
        return new ParameterFilter() {
            @Override
            public void setParameter(Query query) {
                query.setParameter("categoryGuid", categoryGuid);
            }

            @Override
            public String getSubHql() {
                return " and b.category.guid=:categoryGuid";
            }
        };
    }

    private Filter dateRangeFilter(final LocalDate from, final LocalDate to) {
        return new ParameterFilter() {
            @Override
            public void setParameter(Query query) {
                query.setParameter("from", from);
                query.setParameter("to", to);
            }

            @Override
            public String getSubHql() {
                return " and (b.occurredTime >= :from and b.occurredTime < :to)";
            }
        };
    }

    private Filter typeFilter(final BillingType type) {
        return new ParameterFilter() {
            @Override
            public void setParameter(Query query) {
                query.setParameter("type", type);
            }

            @Override
            public String getSubHql() {
                return " and b.type=:type";
            }
        };
    }

    private Filter keyFilter(final String key) {
        return new ParameterFilter() {
            @Override
            public void setParameter(Query query) {
                query.setParameter("key", "%" + key + "%");
            }

            @Override
            public String getSubHql() {
                return " and (b.name like :key or b.description like :key)";
            }
        };
    }

    @Override
    public String getResultHql() {
        return "from Billing b where 1=1 " + getSubHql() + " order by b.occurredTime desc";
    }

    @Override
    public String getTotalCountHql() {
        return "select count(*) from Billing b where 1=1 " + getSubHql();
    }

    @Override
    public int getStartIndex() {
        return paging.getStartIndex();
    }

    @Override
    public int getPageSize() {
        return paging.getPageSize();
    }
}
