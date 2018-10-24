package me.zhyx.common.base.service;

import me.zhyx.common.base.entity.Condition;
import me.zhyx.common.base.entity.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Author: zhyx
 * Date:2018/5/7
 * Time:11:14
 */
public interface BaseDBService {
    int insert(Object o);

    List<Map<String,Object>> query() throws IllegalAccessException, InstantiationException;

    int update(Object o);

    int delete(Object o);

    BaseDBService conditions(Condition... conditions);

    BaseDBService queryFiled(String... fileds);

    BaseDBService operaClazz(Class c);

    BaseDBService queryPagePlugin(PageBean pageBean);
}
