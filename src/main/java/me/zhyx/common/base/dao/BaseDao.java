package me.zhyx.common.base.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Author: zhyx
 * Date:2018/5/7
 * Time:15:15
 */
@Repository
public interface BaseDao {
    int insert(Object o);
    List<Map<String,Object>> query(Object o);

    int update(Map<String, Object> map);

    int delete(Object o);
}
