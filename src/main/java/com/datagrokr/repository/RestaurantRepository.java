package com.datagrokr.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.datagrokr.entity.HelperEntity;
import com.datagrokr.entity.Restaurant;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

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

    public Response updateReservation(Restaurant restaurant){
        Restaurant toBeUpdated = entityManager.find(Restaurant.class, restaurant.getId());
        entityManager.getTransaction().begin();
        if(restaurant.getNoOfPeople() == 0 || restaurant.getBookingTime() == 0)
            return Response.status(400).entity(new HelperEntity(400, "invalid details")).build();
        toBeUpdated.setNoOfPeople(restaurant.getNoOfPeople());
        toBeUpdated.setBookingTime(restaurant.getBookingTime());
        toBeUpdated.setName(restaurant.getName());

        entityManager.persist(toBeUpdated);
        entityManager.getTransaction().commit();
        return Response.status(Status.OK).entity(toBeUpdated).build();
    }

    public void deleteReservation(Long id){
        entityManager.getTransaction().begin();
        Query query =  entityManager.createQuery("DELETE FROM Restaurant WHERE id="+id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
    
    public String count(){
        Query query = entityManager.createQuery("SELECT count(r) FROM Restaurant r");
        return query.getSingleResult().toString();
    }

    public String twoPersonsGroup(){
        Query query = entityManager.createQuery("SELECT count(r) FROM Restaurant r WHERE noOfPeople=2");
        return query.getSingleResult().toString();
    }

    public String fourPersonsGroup(){
        Query query = entityManager.createQuery("SELECT count(r) FROM Restaurant r WHERE noOfPeople=4");
        return query.getSingleResult().toString();
    }

}
