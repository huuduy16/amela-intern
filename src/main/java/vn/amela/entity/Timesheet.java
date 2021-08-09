package vn.amela.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity(name = "timesheet")
@Data
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "problems")
    private String problems;

    @Column(name = "plan")
    private String plan;

    @Column(name = "approved")
    private boolean approved;
}
