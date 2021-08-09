package vn.amela.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.amela.entity.Timesheet;
import vn.amela.response.ResponseUtil;
import vn.amela.response.Status;
import vn.amela.service.TimesheetService;
import vn.amela.service.UserService;

@RestController
public class TimesheetController {

    private final UserService userService;
    private final TimesheetService timesheetService;

    public TimesheetController(UserService userService,
        TimesheetService timesheetService) {
        this.userService = userService;
        this.timesheetService = timesheetService;
    }

    @RequestMapping(value = "/create-timesheet", method = RequestMethod.POST)
    public ResponseEntity<?> createTimesheet(@RequestBody Timesheet timesheet) {
        timesheet.setApproved(false);
        timesheetService.createTimesheet(timesheet);
        //notify leader, some users...
        return ResponseUtil.getResponseEntity(timesheet, new Status("000", "Truy van thanh cong"));
    }
}
