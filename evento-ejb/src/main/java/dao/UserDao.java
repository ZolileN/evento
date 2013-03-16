package dao;

import entities.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sahmed
 * Date: 2/17/13
 * Time: 9:30 AM
 * To change this template use File | Settings | File Templates.
 */

@Local
public interface UserDao {
    public void insertUser(User user);

    public User getUser(User user);

    public User getUserById(int userId);

    public void updateUserInfo(User user);

    public User getUserWithContactList(Integer userId);

    public List<User> getUserForAutoComplete(Integer userId, String query);
}
