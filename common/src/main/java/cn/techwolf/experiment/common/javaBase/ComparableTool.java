package cn.techwolf.experiment.common.javaBase;

public class ComparableTool implements Comparable<ComparableTool> {


    public long id;

    public String title;

    public String subTitle = "";

    public String icon;

    public String shopUrl;

    //item的小红点数字，null/0不显示红点,1+显示红点数字

    private Integer redDotCount;

    /**
     * 排序权重
     */
    private Integer weight;

    /**
     * 业务id
     */

    private long businessId;

    /**
     * 命中校验规则code
     */

    private int ruleCode;


    private String subRuleCode;

    public Integer getRedDotCount() {
        return redDotCount;
    }

    public void setRedDotCount(Integer redDotCount) {
        this.redDotCount = redDotCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public int getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(int ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getSubRuleCode() {
        return subRuleCode;
    }

    public void setSubRuleCode(String subRuleCode) {
        this.subRuleCode = subRuleCode;
    }


    @Override
    public int compareTo(ComparableTool o) {
        return this.weight.compareTo(o.getWeight());
    }
}
