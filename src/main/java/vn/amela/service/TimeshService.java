package vn.amela.service;

import org.springframework.stereotype.Service;
import vn.amela.entity.Timesh;
import vn.amela.repository.TimeshRepository;

@Service
public class TimeshService {

    private final TimeshRepository timeshRepository;

    public TimeshService(TimeshRepository timeshRepository) {
        this.timeshRepository = timeshRepository;
    }

    public void createTimesheet(Timesh timesh) {
        timeshRepository.save(timesh);
    }
}