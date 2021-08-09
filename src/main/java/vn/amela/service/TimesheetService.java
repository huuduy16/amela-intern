package vn.amela.service;

import org.springframework.stereotype.Service;
import vn.amela.entity.Timesheet;
import vn.amela.repository.TimesheetRepository;

@Service
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;

    public TimesheetService(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    public void createTimesheet(Timesheet timesheet) {
        timesheetRepository.save(timesheet);
    }
}