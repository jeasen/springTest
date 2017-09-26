package com.ssm.dto;

/**
 * Created by mm on 2017/9/25.
 */
public class HelpCategory {
    private  long    helpCategoryId;
    private  String  name;
    private  long    parentCategoryId;

    public long getHelpCategoryId() {
        return helpCategoryId;
    }

    public void setHelpCategoryId(long helpCategoryId) {
        this.helpCategoryId = helpCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
