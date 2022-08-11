package edu.ygc.qf;

import sun.security.krb5.SCDynamicStoreConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {
    //连接数据库需要四个必备条件
    //1、驱动
    private final String DRIVER="com.mysql.cj.jdbc.Driver";
    //2、连接路径
    private final String PATH="jdbc:mysql://localhost:3306/game?serverTimezone=UTC";
    //3、用户名
    private final String USER="root";
    //4、密码
    private final String PASS="123456";
    private static DBUtils dbUtils=null;

    private DBUtils(){
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*单例模式对外暴露的方法*/
    public static DBUtils getInstance(){
        if (dbUtils==null){
            dbUtils=new DBUtils();
        }
        return dbUtils;
    }

    /*连接数据库*/
    private Connection getConn(){
        Connection conn=null;
        try {
            //连接数据库
            conn= DriverManager.getConnection(PATH,USER,PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /*存储数据*/
    public void save(int score,String name) throws SQLException {
        //调用方法获取数据库连接
        Connection conn=getConn();
        //准备sql语句
        String sql="insert into gamescore(name,score) values(?,?)";
        //预编译sql语句
        PreparedStatement ps=conn.prepareStatement(sql);
        //设置占位符的内容
        ps.setString(1,name);
        ps.setInt(2, score);
        ps.executeUpdate();
    }



}
