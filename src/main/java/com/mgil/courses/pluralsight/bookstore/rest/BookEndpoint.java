package com.mgil.courses.pluralsight.bookstore.rest;

import com.mgil.courses.pluralsight.bookstore.model.Book;
import com.mgil.courses.pluralsight.bookstore.repository.BookRepository;
import io.swagger.annotations.*;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;



@SwaggerDefinition(
        info =  @Info(
                title = "BookStore API",
                description =  "BookStore API, version 1.0.",
                version = "v.1.0",
                contact =  @Contact(
                        name = "Moises Gil",
                        email = "mgil@gmail.com"

                )
        ),
        host = "http://localhost:8080",
        basePath =  "/bookstore-back-1.0/api",
        schemes = {SwaggerDefinition.Scheme.HTTP,SwaggerDefinition.Scheme.HTTPS},
        tags = {
                @Tag(name = "Book",description = "Book Tag")
        }
)
@Api("Book")
@Path("/books")
public class BookEndpoint {


    @Inject
    private BookRepository bookRepository;

    @GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Bring all the books registered", response = Book.class)
    public Response getAllBooks() {

        List<Book> books = bookRepository.findAll();

        if (books.size() == 0)
            return Response.noContent().build();

        return Response.ok(books).build();

    }

    @POST
    @ApiOperation(value = "Create a book with a given JSON representation of the book", response = Book.class)
    @ApiResponses({
            @ApiResponse(code = 201,message = "Book Created"),
            @ApiResponse(code = 500 ,message =  "Error")
    })
    @Consumes(APPLICATION_JSON)
    public Response createBook(Book book, @Context UriInfo uriInfo) {

        book = bookRepository.create(book);

        URI createdUri = uriInfo.getBaseUriBuilder().path(String.valueOf(book.getId())).build();

        return Response.created(createdUri).build();

    }


    @GET
    @Path("/{id: \\d+}")
    @ApiOperation(value = "Find a book with a given Book ID", response = Book.class)
    @ApiResponses({
            @ApiResponse(code = 200 , message = "Book Found"),
            @ApiResponse(code = 404, message = "Book not found")
    })
    @Produces(APPLICATION_JSON)
    public Response getBook(@ApiParam(required = true , type = "Numeric value", value = "Book ID")@PathParam("id") @Min(1) Long bookId) {

        Book book = bookRepository.find(bookId);

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(book).build();

    }

    @DELETE
    @ApiOperation(value = "Delete a book with a given ID" , response = Book.class)
    @ApiResponses({

            @ApiResponse(code = 200, message = "Book deleted")

    })
    @Path("{id:\\d+}")
    public Response deleteBook(@PathParam("id") Long bookId) {

        bookRepository.delete(bookId);

        return Response.ok().build();

    }


    @GET
    @Path("/count")
    @ApiOperation(value = "Returns the quantity of books registered in the system", response = Book.class)
    @ApiResponse(code = 200 ,message = "Quantity of books registered")
    @Produces(TEXT_PLAIN)
    public Response countBooks() {

        Long count = bookRepository.countAll();

        return Response.ok(count).build();

    }


}
