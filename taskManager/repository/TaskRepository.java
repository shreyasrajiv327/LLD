package repository;
import models.*;
import java.util.*;

public class TaskRepository {

    private final Map<Integer, task> tasks;

    public TaskRepository() {
        this.tasks = new HashMap<>();
    }

    public void addTask(task task) {
        tasks.put(task.getTaskID(), task);
    }

    public task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public boolean exists(int taskId) {
        return tasks.containsKey(taskId);
    }

    public List<task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void removeTask(int taskId) {
        tasks.remove(taskId);
    }
}