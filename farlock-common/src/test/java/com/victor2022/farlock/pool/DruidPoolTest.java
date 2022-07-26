package com.victor2022.farlock.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午3:49
 * @description:
 */
public class DruidPoolTest {

    public static void main(String[] args) throws SQLException, InterruptedException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("victor2022");
        dataSource.setPassword("1045899571");
        DruidPooledConnection dataSourceConnection = dataSource.getConnection();
        PreparedStatement ps = dataSourceConnection.getConnection().prepareStatement("select * from t_farlock");
        ResultSet res = ps.executeQuery();

        System.out.println("finish");

    }
}
