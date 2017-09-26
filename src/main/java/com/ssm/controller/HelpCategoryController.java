package com.ssm.controller;

import com.ssm.dto.HelpCategory;
import com.ssm.service.IHelpCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mm on 2017/9/25.
 */
@Controller
@RequestMapping("/getDate")
public class HelpCategoryController {
    @Resource
    private IHelpCategoryService iHelpCategoryService;

    @RequestMapping("/getHelp")
    @ResponseBody
    public Map<String,Object> getHelpCateDate(int cateId){
        Map<String,Object> maptest = new HashMap<String,Object>();
        HelpCategory help = new HelpCategory();
        help.setParentCategoryId(cateId);
        List<HelpCategory> helpCate = iHelpCategoryService.selectByCondition(help);
        maptest.put("rows",helpCate);
        return maptest;
    }

}
