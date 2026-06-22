package service;

import repository.*;
import models.*;

import java.util.*;

public class TaskService {

    private final TaskRepository taskRepository;
    private final userRepository userRepository;

    public TaskService(TaskRepository taskRepository,
                       userRepository userRepository) {

        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void createTask(int taskId, int assignedUserId) {

        if(taskRepository.exists(taskId)) {
            throw new IllegalArgumentException("Task already exists");
        }

        task newTask = new task(taskId, assignedUserId);

        taskRepository.addTask(newTask);
    }

    public void assignTask(int taskId, int userId) {

        task currentTask = taskRepository.getTask(taskId);

        if(currentTask == null) {
            throw new IllegalArgumentException("Task does not exist");
        }

        if(!userRepository.userExists(userId)) {
            throw new IllegalArgumentException("User does not exist");
        }

        currentTask.assignedUserId = userId;
    }

    public List<task> getTasksByUser(int userId) {

        List<task> result = new ArrayList<>();

        for(task currentTask : taskRepository.getAllTasks()) {

            if(currentTask.getAssignedUserId() == userId) {
                result.add(currentTask);
            }
        }

        return result;
    }

    public void deleteTask(int taskId) {

        if(!taskRepository.exists(taskId)) {
            throw new IllegalArgumentException("Task does not exist");
        }

        taskRepository.removeTask(taskId);
    }
}