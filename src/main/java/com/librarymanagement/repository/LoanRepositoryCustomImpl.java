package com.librarymanagement.repository;

import com.librarymanagement.db.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanRepositoryCustomImpl implements LoanRepositoryCustom {
    @Autowired
    private ReaderRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Loan> FindAllLoansByCriteria(LoanSearchRequest request) {

        return getBookTypedQuery(request).getResultList();
    }

    @Override
    public Page<Loan> FindAllLoanByCriteriaWithPages(LoanSearchRequest request, Pageable page, long totalRows) {
        TypedQuery<Loan> query = getBookTypedQuery(request);
        return new PageImpl<>(query.getResultList(), page, totalRows);
    }

    private TypedQuery<Loan> getBookTypedQuery(LoanSearchRequest request) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Loan> criteriaQuery = criteriaBuilder.createQuery(Loan.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Loan> root = criteriaQuery.from(Loan.class);
        if (request.getId() != null) {
            predicates.add(criteriaBuilder
                    .equal(root.get("id"), request.getId()));
        }
        if (request.getBook() != null){
            Join<Loan, Book> book = root.join("book");
            predicates.add(criteriaBuilder.equal(book.get("id"), request.getBook()));
        }
        if (request.getReader() != null){
            Join<Loan, Reader> reader = root.join("reader");
            predicates.add(criteriaBuilder.equal(reader.get("id"), request.getReader()));
        }

        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );
        return em.createQuery(criteriaQuery);
    }


}
