package me.zhyx.dao;

//import com.alibaba.fastjson.JSON;
import me.zhyx.ApplicationTests;
import me.zhyx.common.base.entity.Condition;
import me.zhyx.common.base.entity.PageBean;
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
        userInfo.setAge("1");
        userInfo.setGrade("1");
        userInfo.setName("ss");
        userInfo.setSex("1");

        UserInfo userInfo1 = new UserInfo();
        userInfo.setId(3);
        userInfo.setAge("1");
        userInfo.setGrade("1");
        userInfo.setName("ss");
        userInfo.setSex("1");

        baseDBService.operaClazz(UserInfo.class).insert(userInfo);
        baseDBService.operaClazz(UserInfo.class).insert(userInfo1);
    }

    @Test
    public void testUpdate() {
        UserInfo userInfo = new UserInfo();

        baseDBService.operaClazz(UserInfo.class).conditions(new Condition("userName", ConditionEnum.LIKE, "张")).update(userInfo);
    }
    @Test
    public void testDelete() {
        UserInfo userInfo = new UserInfo();
        baseDBService.operaClazz(UserInfo.class).conditions(new Condition("id", ConditionEnum.EQ, "2")).delete(userInfo);
    }
    @Test
    public void testSelect() throws InstantiationException, IllegalAccessException {

        PageBean pageBean = baseDBService.operaClazz(UserInfo.class).conditions(new Condition("name", ConditionEnum.LIKE, "张")).queryByPage(1,2);

//        logger.info("Result is {}，size is {}", JSON.toJSONString(pageBean.getItems()),pageBean.getTotalNum());
    }
}
