package pl.com.bottega.docflowjee.hr.rest;

import pl.com.bottega.docflowjee.hr.model.Employee;
import pl.com.bottega.docflowjee.hr.service.EmployeesService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/employees")
public class EmployeesResource {

    @Inject
    private EmployeesService employeesService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        return Response.ok(employeesService.find(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Employee employee) {
        Employee created = employeesService.save(employee);
        return Response.ok(created).build();
    }

}
