package com.ssm.service.impl;

import com.ssm.dto.Person;
import com.ssm.dto.SeeUser;
import com.ssm.service.RedisBaiseTakes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by mm on 2017/9/14.
 */
@Component("seeUserRedisTakes")
public class SeeUserRedisTakes implements RedisBaiseTakes<String,String,SeeUser>{
    @Autowired
    private  RedisTemplate redisTemplate;

    private Logger logger = Logger.getLogger(String.valueOf(SeeUserRedisTakes.class));

    @Override
    public void addObj(String objectKey, String value) {

        if(redisTemplate==null){
            logger.warning("redisTemplate 实例化失败");
            return;
        }else{
            redisTemplate.opsForValue().set(objectKey,value);
        }
    }

    @Override
    public void addObj1(String objectKey, String value, SeeUser seeUser) {
        if (redisTemplate == null){
            logger.warning("redisTemplate 实例化失败");
            System.out.println("===========失败了===========");
            return;
        }else {
            redisTemplate.opsForHash().put(objectKey,value,seeUser);
            System.out.println("===========成功了===========");
        }

    }

    @Override
    public void getObj(String objectKey) {
        if (redisTemplate == null){
            logger.warning("redisTemplate获取数据失败");
            System.out.println("=====获取数据失败了====");
            return;
        }else{
            System.out.println("=====获取数据之前=====");
            String key = String.valueOf(redisTemplate.opsForHash().keys(objectKey));
            String value = String.valueOf(redisTemplate.opsForHash().values(objectKey));
            System.out.println("====之后===="+key);
            System.out.println("====之后后===="+value);
        }
    }
}
