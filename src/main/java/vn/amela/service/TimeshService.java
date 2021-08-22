package vn.amela.service;

import org.springframework.stereotype.Service;
import vn.amela.entity.Task;
import vn.amela.entity.Timesh;
import vn.amela.entity.TimeshTask;
import vn.amela.repository.TaskRepository;
import vn.amela.repository.TimeshRepository;
import vn.amela.repository.TimeshTaskRepository;

@Service
public class TimeshService {

    private final TimeshRepository timeshRepository;
    private final TimeshTaskRepository timeshTaskRepository;
    private final TaskRepository taskRepository;

    public TimeshService(TimeshRepository timeshRepository,
        TimeshTaskRepository timeshTaskRepository,
        TaskRepository taskRepository) {
        this.timeshRepository = timeshRepository;
        this.timeshTaskRepository = timeshTaskRepository;
        this.taskRepository = taskRepository;
    }

    public Timesh createTimesheet(Timesh timesh) {
        return timeshRepository.save(timesh);
    }

    public Task createTask(Long timeshId, Task task) {
        task = taskRepository.save(task);
        TimeshTask timeshTask = new TimeshTask(timeshId, task.getId());
        timeshTaskRepository.save(timeshTask);
        return task;
    }
}