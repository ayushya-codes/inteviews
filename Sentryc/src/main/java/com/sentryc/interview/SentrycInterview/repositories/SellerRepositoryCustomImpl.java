package com.sentryc.interview.SentrycInterview.repositories;

import com.sentryc.interview.SentrycInterview.dtos.Params;
import com.sentryc.interview.SentrycInterview.models.Seller;
import com.sentryc.interview.SentrycInterview.models.SellerProducerStateInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Ayushya
 */
@Repository
public class SellerRepositoryCustomImpl implements SellerRepositoryCustom {

    @Autowired
    private EntityManager em;


    @Override
    public Page<Seller> findCustomSellerInformation(Params param, Pageable pageable) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Seller> criteriaQuery = criteriaBuilder.createQuery(Seller.class);
        Root<Seller> mainSellerRoot = criteriaQuery.from(Seller.class);
        Root<SellerProducerStateInfo> sellerProducerStateRoot = criteriaQuery.from(SellerProducerStateInfo.class);
        mainSellerRoot.fetch("sellerProducerStateInfos", JoinType.LEFT);


        // Add market place filter!
        if (param.getMarketPlaceIds() != null && param.getMarketPlaceIds().length > 0) {
            criteriaQuery.where(criteriaBuilder.in(mainSellerRoot.get("marketPlace").get("id")).in(Arrays.asList(param.getMarketPlaceIds())));
        }

        if (param.getProducerIds() != null && param.getProducerIds().length > 0) {
            criteriaQuery.where(criteriaBuilder.in(sellerProducerStateRoot.get("producer").get("id")).in(Arrays.asList(param.getProducerIds())));
        }

        List<Seller> result = em.createQuery(criteriaQuery).getResultList();

        // Pagination to be implemented yet!
        // This would require manual implementation
        return new PageImpl<>(result);
    }

}
