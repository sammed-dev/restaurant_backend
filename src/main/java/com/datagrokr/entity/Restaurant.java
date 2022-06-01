package com.datagrokr.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurant implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private int noOfPeople;
    private String mobileNo;
    private LocalDateTime bookingTime;

    public Restaurant() {

    }

    public Restaurant(Long id, String name, String mobileNo, int noOfPeople, LocalDateTime bookingTime) {
        this.id = id;
        this.name = name;
        this.mobileNo = mobileNo;
        this.noOfPeople = noOfPeople;
        this.bookingTime = bookingTime;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public int getNoOfPeople() {
        return noOfPeople;
    }
    public void setNoOfPeople(int noOfPeople) {
        this.noOfPeople = noOfPeople;
    }
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "Restaurant [bookingTime=" + bookingTime + ", id=" + id + ", mobileNo=" + mobileNo + ", name=" + name
                + ", noOfPeople=" + noOfPeople + "]";
    }

    
    
}
