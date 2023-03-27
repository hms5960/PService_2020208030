package kr.ac.jejunu.user;
import java.sql.*;
public class UserDao {//DAO data access object: db에 접근하는 객체를 생성하는 클래스
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User findById(Long id) throws ClassNotFoundException, SQLException {
        //getConnection()는 중복 코드
        Connection connection = connectionMaker.getConnection();
        //sql 을 만들고
        PreparedStatement preparedStatement = connection.prepareStatement
                ("select id, name, password from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        //sql 을 실행하고
        ResultSet resultSet = preparedStatement.executeQuery();
        //결과를 user 에 잘 매핑하고요
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원을 해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과를 리턴
        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        //sql 을 만들고
        PreparedStatement preparedStatement = connection.prepareStatement
                ("insert into userinfo (name,password) values(?,?)"
                ,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());


        //sql 을 실행하고
        preparedStatement.executeUpdate();
        //결과를 user 에 잘 매핑하고요
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getLong(1));

        //자원을 해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
    //리펙토링 규칙 : 중복을 피하라
    // refactor -> Extract -> method
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //데이터 어디에 있나? mysql
        //localhost jeju   => jeju/jejupw => userinfo
        //jdbc driver 클래스 로딩
        //커넥션 을 맺고
        return connectionMaker.getConnection();
    }

    //public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
