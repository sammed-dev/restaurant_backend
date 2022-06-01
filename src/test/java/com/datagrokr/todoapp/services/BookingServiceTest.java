package com.datagrokr.todoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.datagrokr.controller.BookingController;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import jakarta.ws.rs.core.Application;
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
   }


}
