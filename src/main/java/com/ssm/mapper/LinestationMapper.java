package com.ssm.mapper;

import com.ssm.dto.Linestation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LinestationMapper {


    int deleteByPrimaryKey(Integer sid);

    int insert(Linestation record);

    int insertSelective(Linestation record);

    Linestation selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Linestation record);

    int updateByPrimaryKey(Linestation record);
}