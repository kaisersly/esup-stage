package org.esup_portail.esup_stage.repository;

import org.esup_portail.esup_stage.model.TypeConvention;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

@Repository
public class TypeConventionRepository extends PaginationRepository<TypeConvention> {

    public TypeConventionRepository(EntityManager em) {
        super(em, TypeConvention.class, "tc");
        this.predicateWhitelist = Arrays.asList("id", "libelle");
    }

    public boolean exists(String codeCtrl, int id) {
        String queryString = "SELECT id FROM " + this.typeClass.getName() + " WHERE codeCtrl = :codeCtrl";
        TypedQuery<Integer> query = em.createQuery(queryString, Integer.class);
        query.setParameter("codeCtrl", codeCtrl);
        List<Integer> results = query.getResultList();
        if (id == 0 && results.size() > 0) {
            return true;
        }

        return results.stream().anyMatch(i -> i != id);
    }
}
