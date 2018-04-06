package com.mgil.courses.pluralsight.bookstore.repository;

import com.mgil.courses.pluralsight.bookstore.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import static javax.transaction.Transactional.TxType.*;

@Transactional(SUPPORTS)
public class BookRepository {

    @PersistenceContext(unitName = "bookStorePU")
    private EntityManager entityManager;


    @Transactional(REQUIRED)
    public Book create(@NotNull Book book){
       entityManager.persist(book);
       return book;
    }

    public Book find(@NotNull Long id){
        return entityManager.find(Book.class,id);
    }

    @Transactional(REQUIRED)
    public void remove(@NotNull @Min(1) Long id){
        entityManager.remove(entityManager.getReference(Book.class,id));
    }

    public List<Book> findAll(){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.title DESC",Book.class);
        return query.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long>  query = entityManager.createQuery("SELECT COUNT(b) FROM Book b",Long.class);
        return query.getSingleResult();
    }


}
