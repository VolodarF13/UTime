package service;

import model.Task;
import repository.TaskRepository;

import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public void save(Task task) {
        if (task.getName() == null  || task.getName().isEmpty()) {
            return;
        }

        taskRepository.save(task);
    }

    public void update(Task task) {
        taskRepository.update(task);
    }

    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }
}
