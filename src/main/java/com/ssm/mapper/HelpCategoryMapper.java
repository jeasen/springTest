package com.ssm.mapper;

import com.ssm.dto.HelpCategory;

import java.util.List;

/**
 * Created by mm on 2017/9/25.
 */
public interface HelpCategoryMapper {
     List<HelpCategory> selectByCondition(HelpCategory helpCategory);
}
