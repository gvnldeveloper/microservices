package com.finland.daoImpl;

import com.finland.dao.SubscriptionDao;
import com.finland.model.Category;
import com.finland.model.Subscription;
import com.finland.model.User;
import com.finland.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class SubscriptionDaoImpl implements SubscriptionDao {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SubscriptionRepository subscribeRepository;

    @Override
    public Subscription subscribeCategories(Subscription subscription) {
        return subscribeRepository.save(subscription);
    }

    @Override
    public List<Subscription> getUserSubscription(int userId) {
        //Log trace
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Subscription> criteriaQuery = criteriaBuilder.createQuery(Subscription.class);

        Root<Subscription> subscription = criteriaQuery.from(Subscription.class);

        Join<Subscription, User> user = subscription.join("user");
        List<Predicate> conditions = new ArrayList();
        conditions.add(criteriaBuilder.equal(user.get("id"), userId));
        conditions.add(criteriaBuilder.equal(subscription.get("isSubscribed"), true));

        TypedQuery<Subscription> typedQuery = entityManager
                .createQuery(criteriaQuery
                        .select(subscription)
                        .where(conditions.toArray(new Predicate[]{})));

        return typedQuery.getResultList();
    }


    @Override
    public Subscription checkCategory(Category categoryId, User userId , String mainSub) {
        //Log trace
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Subscription> criteriaQuery = criteriaBuilder.createQuery(Subscription.class);

        Root<Subscription> subscriptionRoot = criteriaQuery.from(Subscription.class);
        List<Predicate> predicates = new ArrayList<>();

        if (categoryId != null) {
            predicates.add(criteriaBuilder.equal(subscriptionRoot.get("category"), categoryId));
        }
        if (userId != null) {
            predicates.add(criteriaBuilder.equal(subscriptionRoot.get("user"), userId));
        }
        if (mainSub != null && mainSub .equals("existingSubscription")) {
            predicates.add(criteriaBuilder.equal(subscriptionRoot.get("isSubscribed"), true));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            //Log exception
            return null;
        }
    }


// need to do..............................................

    @Override
    @Transactional
    public void updateSubscription(Subscription subscription) {
        //Log trace
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<Subscription> subscriptionCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Subscription.class);
        Root<Subscription> subscriptionRoot = subscriptionCriteriaUpdate.from(Subscription.class);
        subscriptionCriteriaUpdate.set("isSubscribed", true);
        subscriptionCriteriaUpdate.set("localDate",LocalDate.now());
        Predicate predicates
                = criteriaBuilder.equal(subscriptionRoot.get("id"), subscription.getId());
        subscriptionCriteriaUpdate.where(criteriaBuilder.and(predicates));
        //CriteriaQuery<Subscription> criteriaQuery = criteriaBuilder.createQuery(Subscription.class);

        try {
             entityManager.createQuery(subscriptionCriteriaUpdate).executeUpdate();
        } catch (NoResultException e) {
            //Log exception

        }
    }


    @Override
    @Transactional
    public void subscriptionJob() {
        //Log trace
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<Subscription> subscriptionCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Subscription.class);
        Root<Subscription> subscriptionRoot = subscriptionCriteriaUpdate.from(Subscription.class);
        subscriptionCriteriaUpdate.set("isSubscribed", false);
        LocalDate futureDate = LocalDate.now().minusMonths(1);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(subscriptionRoot.get("isSubscribed"), true));
        predicates.add(criteriaBuilder.lessThan(subscriptionRoot.get("localDate"), futureDate));
        subscriptionCriteriaUpdate.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        //CriteriaQuery<Subscription> criteriaQuery = criteriaBuilder.createQuery(Subscription.class);

        try {
            entityManager.createQuery(subscriptionCriteriaUpdate).executeUpdate();
        } catch (NoResultException e) {
            //Log exception

        }
    }


}
