


package com.linzd.pc.dao.impl;

import com.linzd.pc.dao.BaseDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @param <T>
 * @author 林哲达
 * @date 2018/3/6
 */
@Repository
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    private String mapperLocation = "mapper.";

    @Override
    @Resource(name = "sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);

    }

    private String getMapperId(T t, String sqlId) {
        return mapperLocation + t.getClass().getSimpleName() + "Mapper." + sqlId;
    }


    @Override
    public SqlSession getDb() {
        return super.getSqlSession();
    }

    @Override
    public <E> List<E> findAll(T t, String sqlId) {
        return super.getSqlSession().selectList(getMapperId(t, sqlId), t);
    }

    @Override
    public <E> List<E> findAll(T t, Map map, String sqlId) {
        return super.getSqlSession().selectList(getMapperId(t, sqlId), map);
    }

    @Override
    public T findOne(T t, String sqlId) {
        return super.getSqlSession().selectOne(getMapperId(t, sqlId), t);
    }

    @Override
    public T findOne(T t, Map map, String sqlId) {
        return super.getSqlSession().selectOne(getMapperId(t, sqlId), map);
    }



    @Override
    public Map findMap(T t, Map map, String key,String sqlId) {
        return super.getSqlSession().selectMap(getMapperId(t, sqlId),map,key);
    }

    @Override
    public Integer add(T t, String sqlId) {
        return super.getSqlSession().insert(getMapperId(t, sqlId), t);
    }

    @Override
    public Integer add(T t, Map map, String sqlId) {
        return super.getSqlSession().insert(getMapperId(t, sqlId), map);
    }

    @Override
    public Integer addMany(T t, List list, String sqlId) {
        return super.getSqlSession().insert(getMapperId(t, sqlId), list);
    }

    @Override
    public Integer delete(T t, String sqlId) {
        return super.getSqlSession().delete(getMapperId(t, sqlId), t);
    }

    @Override
    public Integer delete(T t, Map map, String sqlId) {
        return super.getSqlSession().delete(getMapperId(t, sqlId), map);
    }

    @Override
    public Integer deleteMany(T t, List list, String sqlId) {
        return super.getSqlSession().delete(getMapperId(t, sqlId), list);
    }

    @Override
    public Integer update(T t, String sqlId) {
        return super.getSqlSession().update(getMapperId(t, sqlId), t);

    }

    @Override
    public Integer update(T t, Map map, String sqlId) {
        return super.getSqlSession().update(getMapperId(t, sqlId), map);
    }

    @Override
    public Integer updateMany(T t, List list, String sqlId) {
        return super.getSqlSession().update(getMapperId(t, sqlId), list);
    }

    @Override
    public double findFunc(T t, String sqlId) {
        return super.getSqlSession().selectOne(this.getMapperId(t, sqlId));
    }

    @Override
    public double findFunc(T t, Map map, String sqlId) {
        return super.getSqlSession().selectOne(this.getMapperId(t, sqlId), map);
    }


}
