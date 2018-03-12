package edu.spring.jaxws;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="HN_GROUP",uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class Group {

}
