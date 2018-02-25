package com.sapling.spiderMans.dao;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;

/**
 * 
 * @author weichao <gorilla@aliyun.com>
 * @date 2016-1-25
 */
public class ExtendSqlSessionFactoryBean extends SqlSessionFactoryBean {

    @Override
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {

        SqlSessionFactory sessionFactory = super.buildSqlSessionFactory();

        sessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);

        return sessionFactory;
    }
}
