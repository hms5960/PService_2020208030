package kr.ac.jejunu.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionMaker {
//    public ConnectionMaker() {
//    }//리펙토링 규칙 : 중복을 피하라
//
//    // refactor -> Extract -> method
//    public Connection getConnection() throws ClassNotFoundException, SQLException {
//        //데이터 어디에 있나? mysql
//        //localhost jeju   => jeju/jejupw => userinfo
//        //jdbc driver 클래스 로딩
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        //커넥션 을 맺고
//        Connection connection = DriverManager.getConnection
//                ("jdbc:mysql://127.0.0.1/jeju", "jeju", "jejupw");
//        return connection;
//    }
    public Connection getConnection() throws ClassNotFoundException, SQLException;
}