package com.datagrokr.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.datagrokr.entity.Restaurant;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class RestaurantRepository {

    private final EntityManager entityManager;
    private final EntityManagerFactory emf;

    Map<String, String> env = System.getenv();
    Map<String, Object> configOverrides = new HashMap<String, Object>();

    


    public RestaurantRepository(){
        for(String envName : env.keySet()){
            if(envName.contains("USER_NAME")){
                configOverrides.put("javax.persistence.jdbc.user", env.get(envName));
            }
        }


        emf = Persistence.createEntityManagerFactory("restaurant_pu");
        entityManager = emf.createEntityManager();
    }

    public List<Restaurant> allReservations(){
        entityManager.getTransaction().begin();
        List<Restaurant> reservations = (List<Restaurant>) entityManager.createNativeQuery("SELECT * FROM Restaurant", Restaurant.class).getResultList();
        entityManager.getTransaction().commit();
        return reservations;
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
        toBeUpdated.setNoOfPeople(restaurant.getNoOfPeople());
        toBeUpdated.setBookingTime(restaurant.getBookingTime());

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

    public void deleteByPhoneNumber(String mobNo){
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("DELETE FROM Restaurant WHERE CAST( mobileno as varchar) =?");
        query.setParameter(1, mobNo);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public Restaurant findById(Long id){
        return entityManager.find(Restaurant.class, id);
    }

}
