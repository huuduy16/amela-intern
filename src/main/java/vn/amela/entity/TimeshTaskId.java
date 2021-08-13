package vn.amela.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class TimeshTaskId implements Serializable {

    private Long timeshId;
    private Long taskId;
}
