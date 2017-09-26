package com.ssm.service.impl;

import com.ssm.dto.HelpCategory;
import com.ssm.mapper.HelpCategoryMapper;
import com.ssm.service.IHelpCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mm on 2017/9/25.
 */
@Service
public class HelpCategoryServiceImpl implements IHelpCategoryService {
    @Resource
    private HelpCategoryMapper helpCategoryMapper;


    @Override
    public List<HelpCategory> selectByCondition(HelpCategory helpCategory) {
        HelpCategory helpCate = new HelpCategory();
        return helpCategoryMapper.selectByCondition(helpCategory);
    }
}
