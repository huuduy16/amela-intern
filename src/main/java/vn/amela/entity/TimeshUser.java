package vn.amela.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "timesheet_user")
@Data
@NoArgsConstructor
public class TimeshUser {

    @EmbeddedId
    private TimeshUserId timeshUserId;

    public TimeshUser(Long userId, Long timeshId) {
        this.timeshUserId.setUserId(userId);
        this.timeshUserId.setTimeshId(timeshId);
    }
}
