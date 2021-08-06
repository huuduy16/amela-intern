package vn.amela.entity;

import javax.persistence.Entity;
import lombok.Data;

@Entity(name = "admin")
@Data
public class Admin extends Account {

}
