package me.zhyx.common.base.service;

import me.zhyx.common.base.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * Author: zhyx
 * Date:2018/5/7
 * Time:11:14
 */
public interface BaseDBService {
    int insert(Object o);

    List<Map<String,Object>> query(Object o);

    int update(Object o);

    int delete(Object o);

    BaseDBService conditions(Condition... conditions);

    BaseDBService queryFiled(String... fileds);

    BaseDBService operaClazz(Class c);
}
