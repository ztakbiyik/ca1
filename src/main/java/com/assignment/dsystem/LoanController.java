package com.assignment.dsystem;

import com.assignment.dsystem.model.Deposit;
import com.assignment.dsystem.model.Loan;
import com.assignment.dsystem.service.LoanService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("loan")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanController {

    LoanService loanService;

    public LoanController() {
        loanService = new LoanService();
    }

    @GET
    public Response getAll() {
        return Response.ok().entity(loanService.getAllLoans()).build();
    }

    @GET
    @Path("/{id}")
    public Response getLoan(@PathParam("id") Long id) {
        return Response.ok().entity(loanService.getLoanById(id)).build();
    }

    @POST
    public Response insertLoan(Loan loan) {
        Loan l = loanService.createOrUpdateLoan(loan);
        return Response.ok().entity(l).build();
    }

    @PUT
    public Response updateLoan(Loan loan) {
        return Response.ok().entity(loanService.createOrUpdateLoan(loan)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLoan(@PathParam("id") Long id) {
        return Response.ok().entity(loanService.deleteLoanById(id)).build();
    }


    @POST
    @Path("/deposit")
    public Response deposit(Deposit deposit) {
        Deposit updated = loanService.deposit(deposit);
        return Response.ok().entity(updated).build();
    }

    @DELETE
    @Path("/deposit/{id}")
    public Response deleteDepositById(Deposit deposit) {
        Loan updated = loanService.deleteDeposit(deposit);
        return Response.ok().entity(updated).build();
    }

}
