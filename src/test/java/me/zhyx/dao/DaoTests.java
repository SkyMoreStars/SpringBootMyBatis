package me.zhyx.dao;

import com.alibaba.fastjson.JSON;
import me.zhyx.ApplicationTests;
import me.zhyx.common.base.entity.Condition;
import me.zhyx.common.base.enums.ConditionEnum;
import me.zhyx.common.base.service.BaseDBService;
import me.zhyx.dto.UserInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author: zhyx
 * Date:2018/3/28
 * Time:14:24
 */
public class DaoTests extends ApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(DaoTests.class);

    @Autowired
    BaseDBService baseDBService;

    @Test
    public void testInsert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(2);
        userInfo.setUserCode("3123");
        userInfo.setUserName("王5");
        userInfo.setRemark("133");
        userInfo.setUserPwd("333");
        baseDBService.operaClazz(UserInfo.class).insert(userInfo);
    }

    @Test
    public void testUpdate() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserCode("456");
        userInfo.setUserName("李四");
        userInfo.setRemark("123");
        userInfo.setUserPwd("1234");
        baseDBService.operaClazz(UserInfo.class).conditions(new Condition("userName", ConditionEnum.LIKE, "张")).update(userInfo);
    }
    @Test
    public void testDelete() {
        UserInfo userInfo = new UserInfo();
        baseDBService.operaClazz(UserInfo.class).conditions(new Condition("id", ConditionEnum.EQ, "2")).delete(userInfo);
    }
    @Test
    public void testSelect() {
        UserInfo userInfo = new UserInfo();
       logger.info("Result is {}", JSON.toJSONString(baseDBService.operaClazz(UserInfo.class).conditions(new Condition("id", ConditionEnum.EQ, "2")).query(userInfo)));
    }
}
