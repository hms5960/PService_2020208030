package kr.ac.jejunu.user;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
public class userDaoTests {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        //db에서 생성된 것을 확인 하는 경우
        long id= 1l;
        String name = "hulk";
        String password = "1234";
        ConnectionMaker connectionMaker = new JejuConnectionMaker();
        //userDao 객체를  통해 findById함수(testcase)를 이용해 db에서 갖고옴
        UserDao userDao = new UserDao(connectionMaker);
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id)); //assertThat(비교대상, )
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test  //객체 생성 후sql에 insert
    public void insert() throws SQLException, ClassNotFoundException {
        //user 객체 생성
        User user = new User();
        String name= "홍민석";
        user.setName(name);
        String password ="12345";
        user.setPassword(password);
        ConnectionMaker connectionMaker = new JejuConnectionMaker();
        //생성한 user객체를 userDao로 db에 insert
        UserDao userDao = new UserDao(connectionMaker);
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));


        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

    @Test
    public void getForHalla() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "hulk";
        String password = "1111";
        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insertForHalla() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name= "홍민석";
        user.setName(name);
        String password ="12345";
        user.setPassword(password);
        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(),  is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

}
