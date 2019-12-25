
package com.linzd.pc.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

    /**
     *   获取sqlsession
     * @return SqlSession
     */
    public SqlSession getDb();


    /**
     * 查询
     *
     * @param t
     * @param sqlId
     * @return
     */
    public <E> List<E> findAll(T t, String sqlId);

    /**
     * 查询
     *
     * @param t
     * @param map
     * @param sqlId
     * @return
     */
    public <E> List<E> findAll(T t, Map map, String sqlId);



    /**
     * 查询  一条记录
     *
     * @param t
     * @param sqlId
     * @return
     */
    public T findOne(T t, String sqlId);

    /**
     * 查询一条记录
     *
     * @param t
     * @param map
     * @param sqlId
     * @return
     */
    public T findOne(T t, Map map, String sqlId);



    /**
     * 查询  结果不是实体类 是map
     *
     * @param t
     * @param sqlId
     * @param key  查询结果集的某个字段  例如id   这个结果的键为 id的具体的值
     * @return
     */
    public Map<String,Map<String,Object>> findMap(T t, Map map, String key, String sqlId);



    /**
     * 新增
     *
     * @param t
     * @param sqlId
     * @return
     */
    public Integer add(T t, String sqlId);

    /**
     * 新增
     *
     * @param t
     * @param map
     * @param sqlId
     * @return
     */
    public Integer add(T t, Map map, String sqlId);

    /**
     * 新增
     *
     * @param t
     * @param list
     * @param sqlId
     * @return
     */
    public Integer addMany(T t, List list, String sqlId);

    /**
     * 删除
     *
     * @param t
     * @param sqlId
     * @return
     */
    public Integer delete(T t, String sqlId);

    /**
     * 删除
     *
     * @param t
     * @param map
     * @param sqlId
     * @return
     */
    public Integer delete(T t, Map map, String sqlId);

    /**
     * 删掉 多个
     *
     * @param t
     * @param list
     * @param sqlId
     * @return
     */
    public Integer deleteMany(T t, List list, String sqlId);

    /**
     * 更新
     *
     * @param t
     * @param sqlId
     * @return
     */
    public Integer update(T t, String sqlId);

    /**
     * 更新
     *
     * @param t
     * @param map
     * @param sqlId
     * @return
     */
    public Integer update(T t, Map map, String sqlId);

    /**
     * 更新多个
     *
     * @param t
     * @param list
     * @param sqlId
     * @return
     */
    public Integer updateMany(T t, List list, String sqlId);

    /**
     * 查询数量
     *
     * @param t
     * @param sqlId
     * @return
     */
    public double findFunc(T t, String sqlId);

    /**
     * 查询数量
     *
     * @param t
     * @param map
     * @param sqlId
     * @return
     */
    public double findFunc(T t, Map map, String sqlId);


}

