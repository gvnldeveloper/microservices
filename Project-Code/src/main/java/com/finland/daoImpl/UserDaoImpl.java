package com.finland.daoImpl;

import com.finland.dao.UserDao;
import com.finland.model.User;
import com.finland.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findUser(String email, String password) {
        //Log trace
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> user = criteriaQuery.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        if (email != null) {
            predicates.add(criteriaBuilder.equal(user.get("email"), email));
        }
        if (password != null) {
            predicates.add(criteriaBuilder.equal(user.get("password"), password));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            //Log exception
            return null;
        }
    }

    @Override
    public User findUserByToken(String token) {
        //Log trace
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> user = criteriaQuery.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        if (token != null) {
            predicates.add(criteriaBuilder.equal(user.get("token"), token));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            //Log exception
            return null;
        }
    }
}
