package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;// DBHelper.getSessionFactory();
    }

    @Override
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(user);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Boolean deleteUser(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(getUserById(id));
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public Boolean updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(user);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }

    }


    @Override
    public User getUserById(Long id) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User where id = :id")
                    .setParameter("id", id);
            user = (User) query.uniqueResult();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            List<User> users = session.createQuery("from User").list();
            return users;
        }

    }

    @Override
    public boolean validateUser(String mail, String password) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User where mail = :mail and password=:password")
                    .setParameter("mail", mail)
                    .setParameter("password", password);
            user = (User) query.uniqueResult();
            user.getPassword().equals(password);
            return true;
        } catch (Exception e) {
            System.out.println("Wrong Password in Validate");
        }
        return false;
    }

    @Override
    public User getUserByMail(String mail) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User where mail = :mail")
                    .setParameter("mail", mail);
            user = (User) query.uniqueResult();
        }
        return user;
    }
}
