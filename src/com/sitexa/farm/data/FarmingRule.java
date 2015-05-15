package com.sitexa.farm.data;

/**
 * FarmingRule entity. @author MyEclipse Persistence Tools
 */

public class FarmingRule implements java.io.Serializable {

	// Fields

	private String ruleId;
	private RuleType ruleType;
	private Service svc;
	private Service svc2;
	private String ruleValue;
	private String remark;

	// Constructors

	/** default constructor */
	public FarmingRule() {
	}

	// Property accessors

	public String getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

    public Service getSvc() {
        return svc;
    }

    public void setSvc(Service svc) {
        this.svc = svc;
    }

    public Service getSvc2() {
        return svc2;
    }

    public void setSvc2(Service svc2) {
        this.svc2 = svc2;
    }

    public RuleType getRuleType() {
		return this.ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public String getRuleValue() {
		return this.ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}