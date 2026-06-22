import models.*;
import repository.*;
import service.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Create repositories
        userRepository userRepository = new userRepository();
        TaskRepository taskRepository = new TaskRepository();

        // Add users
        userRepository.addUser(new user("Shreyas", 1));
        userRepository.addUser(new user("Alice", 2));

        // Create service
        TaskService taskService =
                new TaskService(taskRepository, userRepository);

        // Create tasks
        taskService.createTask(101, 1);
        taskService.createTask(102, 2);

        // Assign task 101 to Alice
        taskService.assignTask(101, 2);

        // Fetch task
        task t = taskRepository.getTask(101);

        System.out.println(
                "Task ID : " + t.getTaskID()
        );

        System.out.println(
                "Assigned User ID : " + t.getAssignedUserId()
        );

        // Get all tasks assigned to Alice
        List<task> aliceTasks =
                taskService.getTasksByUser(2);

        System.out.println(
                "Tasks assigned to user 2 : "
                        + aliceTasks.size()
        );

        for(task currentTask : aliceTasks) {
            System.out.println(
                    "Task ID : "
                            + currentTask.getTaskID()
            );
        }

        // Delete task
        taskService.deleteTask(102);

        System.out.println(
                "Total tasks after deletion : "
                        + taskRepository.getAllTasks().size()
        );
    }
}