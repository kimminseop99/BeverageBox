package com.beverage.BeverageBox.repository;

import com.beverage.BeverageBox.dto.request.BeverageSearchCondition;
import com.beverage.BeverageBox.entity.Beverage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BeverageRepositoryImpl implements BeverageRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Beverage> searchWithCondition(BeverageSearchCondition condition) {
        // 동적 JPQL 작성
        StringBuilder jpql = new StringBuilder("SELECT b FROM Beverage b WHERE 1=1");

        if (condition.getKeyword() != null && !condition.getKeyword().isBlank()) {
            jpql.append(" AND b.name LIKE :keyword");
        }
        if (condition.getMinPrice() != null) {
            jpql.append(" AND b.price >= :minPrice");
        }
        if (condition.getMaxPrice() != null) {
            jpql.append(" AND b.price <= :maxPrice");
        }

        switch (condition.getSortBy()) {
            case "priceAsc" -> jpql.append(" ORDER BY b.price ASC");
            case "name" -> jpql.append(" ORDER BY b.name ASC");
            case "newest" -> jpql.append(" ORDER BY b.createdAt DESC");
            default -> jpql.append(" ORDER BY b.id DESC");
        }

        TypedQuery<Beverage> query = em.createQuery(jpql.toString(), Beverage.class);

        if (condition.getKeyword() != null && !condition.getKeyword().isBlank()) {
            query.setParameter("keyword", "%" + condition.getKeyword() + "%");
        }
        if (condition.getMinPrice() != null) {
            query.setParameter("minPrice", condition.getMinPrice());
        }
        if (condition.getMaxPrice() != null) {
            query.setParameter("maxPrice", condition.getMaxPrice());
        }

        return query.getResultList();
    }
}
