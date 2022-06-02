package com.datagrokr.restaurant.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import com.datagrokr.controller.BookingController;
import com.datagrokr.entity.Restaurant;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class BookingServiceTest extends JerseyTest {

    @Override
    protected Application configure() {
       return new ResourceConfig(BookingController.class);
   }

   @Test
   public void greetUser(){
        Response response = target("/restaurant/welcome").request().get();
        assertEquals(Status.OK.getStatusCode(), response.getStatus());

        String result = response.readEntity(String.class);
        assertEquals("Welcome to Domino's", result);
   }


   @Test
   public void addReservation(){
        Restaurant restaurant = new Restaurant("sams", "1111122222", 2, LocalDateTime.of(2022, 6, 2, 18, 00, 00, 00));
        Response response = target("/restaurant/book").request(MediaType.APPLICATION_JSON).post(Entity.entity(restaurant, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
        assertNotNull(response.getEntity());

        Restaurant restaurant1 = new Restaurant("sams", "4444455555", 4, LocalDateTime.of(2022, 6, 2, 18, 00, 00, 00));
        Response response1 = target("/restaurant/book").request(MediaType.APPLICATION_JSON).post(Entity.entity(restaurant1, MediaType.APPLICATION_JSON));
        assertEquals(200, response1.getStatus());
        assertNotNull(response1.getEntity());

        Restaurant restaurant2 = new Restaurant("sams", "2222233333", 2, LocalDateTime.of(2022, 6, 3, 18, 00, 00, 00));
        Response response2 = target("/restaurant/book").request(MediaType.APPLICATION_JSON).post(Entity.entity(restaurant2, MediaType.APPLICATION_JSON));
        assertEquals(500, response2.getStatus());
        
   }

   @Test
   public void updateReservation(){
        Restaurant restaurant = new Restaurant("sams", "1111122222", 0, LocalDateTime.of(2022, 6, 2, 18, 00, 00, 00));
        Response response = target("/restaurant/update/"+1).request(MediaType.APPLICATION_JSON).put(Entity.entity(restaurant, MediaType.APPLICATION_JSON));
        assertEquals(500, response.getStatus());

        Restaurant restaurant1 = new Restaurant("sams", "1111122222", 2, null);
        Response response1 = target("/restaurant/update/"+1).request(MediaType.APPLICATION_JSON).put(Entity.entity(restaurant1, MediaType.APPLICATION_JSON));
        assertEquals(500, response1.getStatus());

        Restaurant restaurant2 = new Restaurant("sams", "1111122222", 4, null);
        Response response2 = target("/restaurant/update/"+1).request(MediaType.APPLICATION_JSON).put(Entity.entity(restaurant2, MediaType.APPLICATION_JSON));
        assertEquals(500, response2.getStatus());

        // Restaurant restaurant2 = new Restaurant();
        // restaurant2.setNoOfPeople(2);
        // restaurant2.setBookingTime(LocalDateTime.of(2022, 6, 2, 18, 00, 00, 00));
        // Response response2 = target("/restaurant/update/"+1).request(MediaType.APPLICATION_JSON).put(Entity.entity(restaurant2, MediaType.APPLICATION_JSON));
        // assertEquals(200, response2.getStatus());

    }


   @Test
   public void deleteBooking(){
        Response response = target("/restaurant/delete/"+1).request().delete();
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void deleteBookingByMobileNo(){
        String mobNo = "9988776655";
        Response response = target("/restaurant/delete/mobile/"+mobNo).request().delete();
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void findReservationById(){
        Response response = target("/restaurant/"+1).request().get();
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }

}
