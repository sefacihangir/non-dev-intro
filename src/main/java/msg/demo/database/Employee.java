package msg.demo.database;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class for representing employees.
 * 
 * @author Serban Petrescu
 *
 */
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private Role role;

	private Date hiredOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getHiredOn() {
		return hiredOn;
	}

	public void setHiredOn(Date hiredOn) {
		this.hiredOn = hiredOn;
	}
}
