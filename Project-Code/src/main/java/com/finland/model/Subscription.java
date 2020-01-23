package com.finland.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@SequenceGenerator(name = "subscription_id_seq", initialValue = 1000)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_id_seq")
    private int id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

    @Column(name = "localDate", columnDefinition = "Date")
    private LocalDate localDate;
    private boolean isSubscribed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }
}
