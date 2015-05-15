package com.sitexa.farm.data;

import java.util.HashSet;
import java.util.Set;

/**
 * RuleType entity. @author MyEclipse Persistence Tools
 */

public class RuleType implements java.io.Serializable {

	// Fields

	private String typeId;
	private String typeName;
	private String description;
	private Set rules = new HashSet(0);

	// Constructors

	/** default constructor */
	public RuleType() {
	}

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set getRules() {
        return rules;
    }

    public void setRules(Set rules) {
        this.rules = rules;
    }
}