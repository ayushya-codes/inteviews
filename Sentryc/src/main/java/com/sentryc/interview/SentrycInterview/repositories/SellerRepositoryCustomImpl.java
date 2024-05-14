package com.sentryc.interview.SentrycInterview.repositories;

import com.sentryc.interview.SentrycInterview.dtos.Params;
import com.sentryc.interview.SentrycInterview.models.Seller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Ayushya
 */
@Repository
public class SellerRepositoryCustomImpl implements SellerRepositoryCustom {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Page<Seller> findCustomSellerInformation(Params param, Pageable pageable) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Seller> criteriaQuery = criteriaBuilder.createQuery(Seller.class);
        Root<Seller> mainSellerRoot = criteriaQuery.from(Seller.class);
//        Root<SellerProducerStateInfo> sellerProducerStateRoot = criteriaQuery.from(SellerProducerStateInfo.class);
        mainSellerRoot.fetch("sellerProducerStateInfos", JoinType.LEFT);


        List<Predicate> predicates = new ArrayList<>();

        if (param.getName() != null && !param.getName().isEmpty()) {
            predicates.add(criteriaBuilder.like(mainSellerRoot.get("name"), param.getName()));
        }
        // Add market place filter!
        if (param.getMarketPlaceIds() != null && param.getMarketPlaceIds().length > 0) {
            predicates.add(criteriaBuilder.in(mainSellerRoot.get("marketPlace").get("id").in(Arrays.asList(param.getMarketPlaceIds()))));
        }

        if (param.getProducerIds() != null && param.getProducerIds().length > 0) {
            predicates.add(criteriaBuilder.in(mainSellerRoot.get("sellerProducerStateInfos").get("producer").get("id").in(Arrays.asList(param.getProducerIds()))));
        }

        if (!CollectionUtils.isEmpty(predicates)) {
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        List<Seller> result = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result);
    }

}
