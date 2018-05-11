package com.jk.login.dao.impl;

import com.jk.login.dao.LoginDao;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginDaoImpl implements LoginDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List getData() {
        String sql="SELECT DISTINCT " +
                "p.p_id AS pid, " +
                "p.p_text AS text," +
                "p.p_parentid AS parentid," +
                "p.p_url AS url," +
                "p.p_icon AS icon " +
                "FROM user u " +
                "LEFT JOIN user_role ur ON ur.u_id = u.u_id " +
                "LEFT JOIN role r ON r.r_id=ur.r_id " +
                "LEFT JOIN role_power rp ON rp.r_id = r.r_id " +
                "LEFT JOIN POWER p ON p.p_id = rp.p_id " +
                "WHERE u.u_id = 1";

        return sessionFactory.openSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Override
    public List findData() {
        //sql语句
        String sql="SELECT m.*,ma.areaname,mt.timename,mp.typename FROM movie m " +
                "LEFT JOIN moviearea ma ON m.areaid = ma.areaid " +
                "LEFT JOIN movietime mt ON m.timeid = mt.timeid " +
                "LEFT JOIN movietype mp ON m.typeid = mp.typeid ";

        //1获取session
        Session session = sessionFactory.openSession();
        //2创建一个sql查询对象
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        //3设置sqlQuery返回的数据类型
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        //4查询
        List list = sqlQuery.list();
        return list;
    }
}
