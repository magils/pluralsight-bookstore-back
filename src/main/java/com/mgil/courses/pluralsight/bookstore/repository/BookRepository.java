package com.mgil.courses.pluralsight.bookstore.repository;

import com.mgil.courses.pluralsight.bookstore.model.Book;
import com.mgil.courses.pluralsight.bookstore.util.NumberGenerator;
import com.mgil.courses.pluralsight.bookstore.util.TextUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class BookRepository {

    @PersistenceContext(unitName = "bookStorePU")
    private EntityManager entityManager;

    @Inject
    private NumberGenerator numberGenerator;

    @Inject
    private TextUtil textUtil;


    @Transactional(REQUIRED)
    public Book create(@NotNull Book book) {

        book.setTitle(textUtil.sanitize(book.getTitle()));
        book.setIsbn(numberGenerator.generateNumber());

        entityManager.persist(book);
        return book;
    }

    public Book find(@NotNull Long id) {
        return entityManager.find(Book.class, id);
    }

    @Transactional(REQUIRED)
    public void delete(@NotNull @Min(1) Long id) {
        entityManager.remove(entityManager.getReference(Book.class, id));
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }


}
