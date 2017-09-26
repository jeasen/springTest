package com.ssm.service;

import com.ssm.dto.HelpCategory;

import java.util.List;

/**
 * Created by mm on 2017/9/25.
 */
public interface IHelpCategoryService {
    public List<HelpCategory> selectByCondition(HelpCategory helpCategory);
}
