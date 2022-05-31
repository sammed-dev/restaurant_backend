package com.datagrokr.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.datagrokr.entity.Restaurant;

public class RestaurantRepository {

    private final EntityManager entityManager;
    private final EntityManagerFactory emf;

    public RestaurantRepository(){
        emf = Persistence.createEntityManagerFactory("restaurant_pu");
        entityManager = emf.createEntityManager();
    }

    public Restaurant addReservation(Restaurant restaurant){
        entityManager.getTransaction().begin();
        entityManager.persist(restaurant);
        entityManager.getTransaction().commit();
        return restaurant;
    }

    public Restaurant updateReservation(Restaurant restaurant){
        return null;
    }
    
}
