package com.datagrokr.controller;

import com.datagrokr.entity.Restaurant;
import com.datagrokr.service.BookingService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("restaurant")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class BookingController {
    
    private BookingService bookingService = new BookingService();

    @GET
    @Path("/welcome")
    public String greetUser(){
        return bookingService.greetUser();
    }
    
    @POST
    @Path("/book")
    public Response bookTable(Restaurant restaurant){
        return bookingService.bookTable(restaurant);
    } 


}
