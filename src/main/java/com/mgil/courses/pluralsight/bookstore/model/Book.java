package com.mgil.courses.pluralsight.bookstore.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@ApiModel("Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 500)
    private String title;

    @NotNull
    @Size(min=1 ,max = 10000)
    private String description;

    private Language language;

    @Min(1)
    @Column(name="unit_cost")
    private Float unitCost;


    @ApiModelProperty("ISBN Number")
    @Size(min = 1 , max = 50)
    private String isbn;

    //@NotNull
    @Column(name="publication_date")
    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @Min(1)
    @Column(name="nb_of_pages")
    private Integer nbOfPages;

    @NotNull
    @ApiModelProperty("URL of the image cover")
    @Column(name="image_url")
    private String imageUrl;

    public Book() {
    }

    public Book(String title, String description, Language language, Float unitCost, String isbn, Date publicationDate, Integer nbOfPages, String imageUrl) {
        this.title = title;
        this.description = description;
        this.language = language;
        this.unitCost = unitCost;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.nbOfPages = nbOfPages;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Float unitCost) {
        this.unitCost = unitCost;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getNbOfPages() {
        return nbOfPages;
    }

    public void setNbOfPages(Integer nbOfPages) {
        this.nbOfPages = nbOfPages;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
