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
        Restaurant restaurant = new Restaurant(21l, "sams", "1111122222", 2, LocalDateTime.of(2022, 6, 1, 18, 00, 00, 00));
        Response response = target("/restaurant/book").request(MediaType.APPLICATION_JSON).post(Entity.entity(restaurant, MediaType.APPLICATION_JSON));
        assertEquals(500, response.getStatus());
        assertNotNull(response.getEntity());
   }

   @Test
   public void updateReservation(){
        Restaurant toBeUpdated = new Restaurant();
        toBeUpdated.setNoOfPeople(2);
        toBeUpdated.setBookingTime(LocalDateTime.of(2022, 6, 1, 18, 00, 00, 00));
        Response response = target("/restaurant/update/"+1).request(MediaType.APPLICATION_JSON).put(Entity.entity(toBeUpdated, MediaType.APPLICATION_JSON));
        assertEquals(500, response.getStatus());
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

}
