package repository;

import model.Task;
import model.TaskStatus;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        String sql = "select * from tasks";
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        tasks.add(new Task(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("type"),
                                resultSet.getString("importance"),
                                resultSet.getObject("start_time", LocalDateTime.class),
                                resultSet.getObject("finish_time", LocalDateTime.class),
                                resultSet.getString("description"),
                                TaskStatus.valueOf(resultSet.getString("status"))
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while trying to get all tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void save(Task task) {
        String sql = "INSERT INTO tasks(name, type,importance, start_time, finish_time, description, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionManager.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1,task.getName());
                preparedStatement.setString(2,task.getType());
                preparedStatement.setString(3,task.getImportance());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(task.getStartTime()));
                preparedStatement.setTimestamp(5,Timestamp.valueOf(task.getFinishTime()));
                preparedStatement.setString(6,task.getDescription());
                preparedStatement.setString(7,task.getStatus().toString());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error while trying to save tasks: " + e.getMessage());
        }
    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET name = ?,  type = ?, importance = ?, start_time = ?, " +
                "finish_time = ?, description = ?, status = ? WHERE id = ?";

        try(Connection connection = ConnectionManager.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1,task.getName());
                preparedStatement.setString(2,task.getType());
                preparedStatement.setString(3,task.getImportance());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(task.getStartTime()));
                preparedStatement.setTimestamp(5,Timestamp.valueOf(task.getFinishTime()));
                preparedStatement.setString(6,task.getDescription());
                preparedStatement.setString(7,task.getStatus().toString());
                preparedStatement.setInt(8,task.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error while trying to update tasks: " + e.getMessage());
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try(Connection connection = ConnectionManager.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        }catch (SQLException e) {
            System.err.println("Error deleting tasks: " + e.getMessage());
        }

    }
}
