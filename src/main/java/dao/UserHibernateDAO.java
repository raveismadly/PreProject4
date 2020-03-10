//package dao;
//
//import model.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import util.DBHelper;
//
//import java.util.List;
//
//public class UserHibernateDAO implements UserDAO {
//    private SessionFactory sessionFactory;
//
//    public UserHibernateDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = DBHelper.getSessionFactory();
//    }
//
//    @Override
//    public void addUser(User user) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.getTransaction();
//            transaction.begin();
//            session.save(user);
//            transaction.commit();
//        }
//    }
//
//    @Override
//    public Boolean deleteUser(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.delete(getUserById(id));
//            transaction.commit();
//            return true;
//        }
//    }
//
//    @Override
//    public Boolean updateUser(User user) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.update(user);
//            transaction.commit();
//            return true;
//        }
//
//    }
//
//
//    @Override
//    public User getUserById(Long id) {
//        User user = null;
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            Query query = session.createQuery("from User where id = :id")
//                    .setParameter("id", id);
//            user = (User) query.uniqueResult();
//            transaction.commit();
//        }
//        return user;
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            List<User> users = session.createQuery("from User").list();
//            transaction.commit();
//            return users;
//        }
//
//    }
//}
