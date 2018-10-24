package me.zhyx.common.base.service.impl;

import me.zhyx.common.annotation.Column;
import me.zhyx.common.annotation.Id;
import me.zhyx.common.annotation.Table;
import me.zhyx.common.base.dao.BaseDao;
import me.zhyx.common.base.entity.Condition;
import me.zhyx.common.base.entity.PageBean;
import me.zhyx.common.base.enums.ConditionEnum;
import me.zhyx.common.base.service.BaseDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: zhyx
 * Date:2018/5/7
 * Time:13:58
 */
@Transactional
@Service
public class BaseDBServiceImpl implements BaseDBService {
    private static final Logger logger = LoggerFactory.getLogger(BaseDBServiceImpl.class);
    private String conditions;
    private Map<String, Object> queryFileds;
    private Class c;

    @Autowired
    private BaseDao baseDao;

    @Override
    public int insert(Object o) {
        Map<String, Object> map = parsingParams(o);
        logger.info(new StringBuffer("Function Insert.Transformed Params:").append(map).toString());
        return baseDao.insert(map);
    }

    @Override
    public int update(Object o) {
        Map<String, Object> map = parsingParams(o);
        logger.info(new StringBuffer("Function Update.Transformed Params:").append(map).toString());
        return baseDao.update(map);
    }

    @Override
    public List<Map<String, Object>> query() throws IllegalAccessException, InstantiationException {
        Map<String, Object> map = parsingParams(c.newInstance());
        logger.info(new StringBuffer("Function Query.Transformed Params:").append(map).toString());
        if(map.get("_QUERY_FILED")==null){
            this.queryFiled(getFields());
            map.put("_QUERY_FILED",this.queryFileds);
        }
        return baseDao.query(map);
    }

    private String[] getFields() {
        if(c==null){
            throw new RuntimeException("Please set the operation object first");
        }
        Field[] declaredFields = this.c.getDeclaredFields();
        String[] fieldName=new String[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            fieldName[i]=declaredFields[i].getName();
        }
        return fieldName;
    }

    @Override
    public int delete(Object o) {
        Map<String, Object> map = parsingParams(o);
        logger.info(new StringBuffer("Function Delete.Transformed Params:").append(map).toString());
        return baseDao.delete(map);
    }

    @Override
    public BaseDBService conditions(Condition... conditions) {
        if (this.c == null) {
            throw new RuntimeException("Please set the operation object first");
        }
        StringBuilder conditionBuilder = new StringBuilder();
        for (Condition condition : conditions) {
            conditionBuilder.append(getCondition(this.c, condition));
        }
        this.conditions = conditionBuilder.toString();
        return this;
    }

    @Override
    public BaseDBService queryFiled(String... fileds) {
        if (fileds == null) {
            throw new RuntimeException("Error params!,The query fileds is null");
        }
        this.queryFileds = checkFiled(fileds);
        return this;
    }

    private Map<String, Object> parsingParams(Object o) {
        //get Table name
        if (null == o.getClass().getAnnotation(Table.class)) {
            throw new RuntimeException("Error Input Object! @Table is Empty!");
        }
        Map<String, Object> res = new HashMap<>();
        res.put("TABLE_NAME", o.getClass().getAnnotation(Table.class).value());
        Method[] methods = o.getClass().getMethods();
        if (null == methods || methods.length <= 0) {
            throw new RuntimeException("Error Input Object,Method Empty!");
        }
        List k = new ArrayList();
        List v = new ArrayList();
        for (Method method : methods) {
            try {
                //获取列名和值
                if (null != method.getAnnotation(Column.class)) {
                    k.add(method.getAnnotation(Column.class).value());
                    v.add(method.invoke(o, null));
                }
                //获取主键
                if (null != method.getAnnotation(Id.class)) {
                    res.put("KEY_ID", method.getAnnotation(Id.class).value());
                    res.put("KEY_VALUE", method.invoke(o, null));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error Input Object! Error Invoke Get Method.", e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Error Input Object! Error Invoke Get Method.", e);
            }
        }
        res.put("COLUMNS", k);
        res.put("VALUES", v);
        if (k.size() != v.size()) {
            throw new RuntimeException("Error Input Object! Internal Error.");
        }
        if (this.conditions != null) {
            res.put("_CONDITIONS", conditions);
        }
        if (this.queryFileds != null) {
            res.put("_QUERY_FILED", queryFileds);
        }
        return res;
    }



    @Override
    public BaseDBService operaClazz(Class c) {
        this.c = c;
        return this;
    }

    @Override
    public BaseDBService queryPagePlugin(PageBean pageBean) {
//        if (null == c) {
//            throw new RuntimeException("Please set the operation object first");
//        }
//        this.conditions=this.conditions+"limit ("+(pageBean.getCurrPage()-1)* pageBean.getPageSize()+","+ pageBean.getCurrPage()* pageBean.getPageSize()+")";
        return this;
    }

    private Map<String, Object> checkFiled(String[] fileds) {
        Map<String, Object> res = new HashMap<>();
        Method[] methods = this.c.getMethods();
        for (Method method : methods) {
            for (String filed : fileds) {
                if (("get" + filed).equalsIgnoreCase(method.getName())) {
                    String columnName = getColumnName(method);
                    res.put(columnName, filed);
                }
            }
        }
        return res;
    }

    private synchronized String getColumnName(Method method) {
        String columnName = null;
        try {
            columnName = method.getAnnotation(Column.class).value();
        } catch (NullPointerException e) {
            try {
                columnName = method.getAnnotation(Id.class).value();
            } catch (NullPointerException e1) {
                throw new RuntimeException("Error Filed! Can't not find @Column or @Id annotation on the " + method.getName() + "() method");
            }
        }
        return columnName;
    }

    private String getCondition(Class c, Condition condition) {
        String column = condition.getColumn();
        if (column != null) {
            //获取查询字段
            String field = getField(column, c);
            Map<String, Object> operator = ConditionEnum.operator(condition.getConditionEnum(), condition.getValue());
            Boolean hasSetVal = (Boolean) operator.get("hasSetVal");
            String val = condition.getValue();
            if (hasSetVal) {
                val = "";
            }
            return field + " " + operator.get("condition") + " " + val;
        } else {
            return condition.getConditionEnum().getSign();
        }
    }

    private String getField(String column, Class c) {
        if (column == null) {
            return null;
        }
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            if (("get" + column).equalsIgnoreCase(method.getName())) {
                return getColumnName(method);
            }
        }
        throw new RuntimeException("Error Field! Please verify the query field");
    }

}
