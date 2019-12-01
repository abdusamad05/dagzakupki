package com.folivora.dagzakupki.domain;

import javax.persistence.*;

@Entity

public class Bid {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String object;
    private Long price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;
    public Bid() {
    }
    public Bid(String object, Long price, User user) {
        this.author = user;
        this.object = object;
        this.price = price;
    }
    public String getAuthorName(){
        return author !=null ? author.getUsername():"<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
