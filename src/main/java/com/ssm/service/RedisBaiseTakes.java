package com.ssm.service;

import org.springframework.stereotype.Service;

/**
 * Created by mm on 2017/9/14.
 * redis测试一
 */
public interface RedisBaiseTakes<H,K,I> {
     void addObj(H objectKey,K value);
     void addObj1(H objectKey,K value,I seeUser);
}
