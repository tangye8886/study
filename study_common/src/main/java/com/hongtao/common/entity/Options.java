package com.hongtao.common.entity;

import java.io.Serializable;
import java.util.List;

public class Options implements Serializable{

    private String value;

    private String label;

    private List<Options> children;

    public Options() {
    }

    public Options(String label,String value) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Options> getChildren() {
        return children;
    }

    public void setChildren(List<Options> children) {
        this.children = children;
    }
}
