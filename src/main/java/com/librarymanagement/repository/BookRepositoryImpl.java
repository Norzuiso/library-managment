package com.librarymanagement.repository;

import com.librarymanagement.db.Book;
import com.librarymanagement.db.BookSearchRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {

    EntityManager em;

    @Override
    public List<Book> FindAllBooksByCriteria(BookSearchRequest request) {
        return getBookTypedQuery(request).getResultList();
    }

    @Override
    public Page<Book> FindAllBooksByCriteriaWithPages(BookSearchRequest request, Pageable page, long totalRows) {
        TypedQuery<Book> query = getBookTypedQuery(request);
        return new PageImpl<>(query.getResultList(), page, totalRows);
    }

    private TypedQuery<Book> getBookTypedQuery(BookSearchRequest request) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<Book> root = criteriaQuery.from(Book.class);
        if (request.getId() != null) {
            predicates.add(criteriaBuilder
                    .equal(root.get("id"), request.getId()));
        }
        if (request.getAuthor() != null) {
            predicates.add(criteriaBuilder
                    .like(root.get("author"), "%" + request.getAuthor() + "%"));
        }
        if (request.getGender() != null) {
            predicates.add(criteriaBuilder
                    .like(root.get("gender"), "%" + request.getGender() + "%"));
        }

        if (request.getEditorial() != null) {
            predicates.add(criteriaBuilder
                    .like(root.get("editorial"), "%" + request.getEditorial() + "%"));
        }

        if (request.getPublishYear() != null) {
            predicates.add(criteriaBuilder
                    .like(root.get("publishYear"), "%" + request.getPublishYear() + "%"));
        }

        if (request.getEdition() != null) {
            predicates.add(criteriaBuilder
                    .like(root.get("edition"), "%" + request.getEdition() + "%"));
        }

        if (request.getSCDD() != null) {
            predicates.add(criteriaBuilder
                    .like(root.get("SCDD"), "%" + request.getSCDD() + "%"));
        }

        if (request.getIsAvailable() != null) {
            predicates.add(criteriaBuilder
                    .isFalse(root.get("isAvailable"))
            );

        }

        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );
        TypedQuery<Book> query = em.createQuery(criteriaQuery);
        return query;
    }
}
