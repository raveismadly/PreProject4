package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;

import java.util.List;

public class ServiceImpl implements Service {
    private static ServiceImpl service;
    private static UserDAO userDao;

    public static ServiceImpl getInstance() {
        if (service == null) {
            service = new ServiceImpl();
        }
        userDao = new UserDaoFactory().getFactory();
        return service;
    }


    public void addUser(User user) {
        userDao.addUser(user);
    }

    public Boolean deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    public Boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public boolean validateUser(String mail, String password) {
        return userDao.validateUser(mail, password);
    }
    public User getUserByMail(String mail) {
        return userDao.getUserByMail(mail);
    }
}
