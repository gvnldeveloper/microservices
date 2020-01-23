package com.finland.daoImpl;

import com.finland.dao.CategoryDao;
import com.finland.model.Category;
import com.finland.repository.CategoryRepository;
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
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategory(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAvailableCategory(List<Category> categoryList) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);

        Root<Category> categoryRoot = criteriaQuery.from(Category.class);

        List<Predicate> predicates = new ArrayList<>();
        if (categoryList != null && !categoryList.isEmpty()) {
            predicates.add(criteriaBuilder.not(categoryRoot.get("id").in(getIds(categoryList))));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);

        Root<Category> categoryRoot = criteriaQuery.from(Category.class);
        List<Predicate> predicates = new ArrayList<>();

        if (categoryName != null) {
            predicates.add(criteriaBuilder.equal(categoryRoot.get("name"), categoryName));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException ex) {
            //Log exception
            return null;
        }
    }

    private List<Integer> getIds(List<Category> categoryList) {
        List<Integer> categoryIds = new ArrayList<>();
        for (Category category : categoryList) {
            categoryIds.add(category.getId());
        }
        return categoryIds;
    }
}
