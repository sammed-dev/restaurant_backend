package com.datagrokr.controller;

import com.datagrokr.service.BookingService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("booking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingController {
    
    private BookingService bookingService = new BookingService();

    @GET
    @Path("/welcome")
    public String greetUser(){
        return bookingService.greetUser();
    }
    
}
