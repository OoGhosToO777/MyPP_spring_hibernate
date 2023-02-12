package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@EnableTransactionManagement
@Transactional
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
        return getCars(model, series)
                .getResultList()
                .stream()
                .map(this::getUserByCar)
                .collect(Collectors.toList());
    }

    private Query<Car> getCars(String model, int series) {
        return sessionFactory.getCurrentSession().createQuery(
                "from Car where model = :model and series =:series")
                .setParameter("model", model)
                .setParameter("series", series);
    }

    private User getUserByCar(Car car) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("from User where id = :id")
                .setParameter("id", car.getId());
    }

}
