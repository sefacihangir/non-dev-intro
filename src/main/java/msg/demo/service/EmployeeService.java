package msg.demo.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;

import msg.demo.database.Employee;
import msg.demo.database.Repository;

/**
 * REST service for managing employees.
 * 
 * @author Serban Petrescu
 */
@Singleton
@Path("/employee")
public class EmployeeService {

	private final Repository repository;

	public EmployeeService() {
		this.repository = new Repository();
	}

	/**
	 * Reads all the existing employees.
	 * 
	 * @return The list of all employees.
	 */
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> readAll() {
		return repository.selectAll(Employee.class);
	}

	/**
	 * Creates a new employee.
	 * 
	 * @param employee
	 *            The new employee data.
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(Employee employee) {
		repository.create(employee);
	}

}
