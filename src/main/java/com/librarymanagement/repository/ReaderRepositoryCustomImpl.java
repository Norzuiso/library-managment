package com.librarymanagement.repository;

import com.librarymanagement.db.Book;
import com.librarymanagement.db.BookSearchRequest;
import com.librarymanagement.db.Reader;
import com.librarymanagement.db.ReaderSearchRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderRepositoryCustomImpl implements ReaderRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Reader> FindAllReaderByCriteria(ReaderSearchRequest request) {
        return getBookTypedQuery(request).getResultList();
    }

    @Override
    public Page<Reader> FindAllReaderByCriteriaWithPages(ReaderSearchRequest request, Pageable page, long totalRows) {
        TypedQuery<Reader> query = getBookTypedQuery(request);
        return new PageImpl<>(query.getResultList(), page, totalRows);
    }

    private TypedQuery<Reader> getBookTypedQuery(ReaderSearchRequest request) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Reader> criteriaQuery = criteriaBuilder.createQuery(Reader.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<Book> root = criteriaQuery.from(Book.class);
        if (request.getId() != null) {
            predicates.add(criteriaBuilder
                    .equal(root.get("id"), request.getId()));
        }
        if (request.getName() != null) {
            predicates.add(criteriaBuilder
                    .like(root.get("name"), "%" + request.getName() + "%"));
        }
        if (request.getAddress() != null) {
            predicates.add(criteriaBuilder
                    .like(root.get("address"), "%" + request.getAddress() + "%"));
        }

        if (request.getPhone() != null) {
            predicates.add(criteriaBuilder
                    .like(root.get("phone"), "%" + request.getPhone() + "%"));
        }

        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );
        return em.createQuery(criteriaQuery);
    }

}
