package vn.amela.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "timesheet_task")
@Data
@NoArgsConstructor
public class TimeshTask {

    @EmbeddedId
    private TimeshTaskId timeshTaskId;

    public TimeshTask(Long timeshId, Long taskId) {
        this.timeshTaskId.setTimeshId(timeshId);
        this.timeshTaskId.setTaskId(taskId);
    }
}
