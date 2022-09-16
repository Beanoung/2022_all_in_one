package com.yang.all_in_one.dao.impl;

import com.yang.all_in_one.dao.BaseDao;
import com.yang.all_in_one.dao.api.MemorialsDao;
import com.yang.all_in_one.entity.Memorials;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MemorialsDaoImpl extends BaseDao<Memorials> implements MemorialsDao {
    @Override
    public List<Memorials> selectAllMemorialsDigest() {

        String sql = "select memorials_id memorialsID," +
                "memorials_title memorialsTitle," +
                "concat(left(memorials_content,10),\"...\") memorialsContentDigest," +
                "emp_name memorialsEmpName," +
                "memorials_create_time memorialsCreateTime," +
                "memorials_status memorialsStatus " +
                "from t_memorials m left join t_emp e on m.memorials_emp = e.emp_id;";

        return getBeanList(sql, Memorials.class);
    }

    @Override
    public Memorials selectMemorialsByID(String memorialsID) {

        String sql="select memorials_id memorialsID," +
                "memorials_title memorialsTitle," +
                "memorials_content memorialsContent," +
                "emp_name memorialsEmpName," +
                "memorials_create_time memorialsCreateTime," +
                "memorials_status memorialsStatus," +
                "feedback_time feedbackTime," +
                "feedback_content feedbackContent " +
                "from t_memorials m left join t_emp e on m.memorials_emp = e.emp_id " +
                "where memorials_id=?";
        return getSingleBean(sql, Memorials.class,memorialsID);
    }

    @Override
    public void updateMemorialsStatusToRead(String memorialsID) {
        String sql = "update t_memorials set memorials_status=1 where memorials_id=?";
        update(sql, memorialsID);
    }

    @Override
    public void updateMemorialsFeedBack(String memorialsID, String feedbackContent) {
        String feedbackTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String sql = "update t_memorials set memorials_status=2,feedback_content=?,feedback_time=? where memorials_id=?";

        update(sql, feedbackContent, feedbackTime, memorialsID);
    }
}
