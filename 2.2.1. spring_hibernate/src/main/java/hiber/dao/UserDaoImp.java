package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@EnableTransactionManagement
@Transactional
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> findUserByCar(String model, int series) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(
                "from Car where model = '" + model + "' and series = " + series);
        List<User> user = new ArrayList<>();
        for (Car car : query.getResultList()) {
            TypedQuery<User> queryUser = sessionFactory.getCurrentSession().createQuery(
                    "from User where id =" + car.getId()
            );
            user.addAll(queryUser.getResultList());
        }
        return user;
    }
}
