package kr.ac.jejunu.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Bean
    public UserDao userDao() throws ClassNotFoundException {
        UserDao userDao = new UserDao(dataSource());
        return userDao;
    }
    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        //3.31 db는 여기에서
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
