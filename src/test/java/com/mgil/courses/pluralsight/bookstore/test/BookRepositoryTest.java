package com.mgil.courses.pluralsight.bookstore.test;

import com.mgil.courses.pluralsight.bookstore.model.Book;
import com.mgil.courses.pluralsight.bookstore.model.Language;
import com.mgil.courses.pluralsight.bookstore.repository.BookRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BookRepositoryTest {


    @Inject
    private BookRepository bookRepository;

    @Deployment
    public static Archive<?> createDeploymentPackage(){

        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true,"com.mgil.courses.pluralsight.bookstore")
                .addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml","persistence.xml");

    }

    @Test
    @InSequence(1)
    public void repositoryShouldBeDeployed(){
        assertNotNull(bookRepository);
    }


    @Test
    @InSequence(2)
    public void shouldGetNoBook(){
        assertEquals(Long.valueOf(0),bookRepository.countAll());
    }

    @Test
    @InSequence(3)
    public void shouldCreateABook() {

        Book newBook = new Book("My   Book","This is a sample book",Language.SPANISH,1.99f,null,new Date(),15,"http://www.me.com");

        Book createdBook = bookRepository.create(newBook);


        /*
        *
        * Conditions to check:
        *
        * (1) The book ID is not null
        * (2) The ISBN is not null
        * (3) The title is sanitize for avoid extra spaces
        *
        * */

        assertNotNull(createdBook.getId());
        assertNotNull(createdBook.getIsbn());
        assertEquals("My Book",createdBook.getTitle());

    }


    @Test
    @InSequence(4)
    public void countAllTheBooks() {

        Book newBook = new Book("Another Book","This is a sample book",Language.ENGLISH,1.99f,"000110456789",new Date(),150,"http://www.me.com");

        Book createdBook = bookRepository.create(newBook);

        Long booksCount = bookRepository.countAll();

        assertTrue(booksCount > new Long(0));
    }

}
