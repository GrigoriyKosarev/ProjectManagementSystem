package com.kosarev.dbconnection.repository;

import com.kosarev.dbconnection.domain.Project;
import com.kosarev.dbconnection.error.InternalException;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class ProjectDAO extends ConnectorDB {

    private PreparedStatement insertStatement;
    private PreparedStatement editStatement;
    private PreparedStatement deleteStatement;

    public Optional<Project> getProject(int id) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        String selectQuery = "SELECT name, comment, cost, createDate " +
                "FROM projects " +
                "WHERE id = " + id;
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQuery);
            if (resultSet.first()) {
                String name = resultSet.getNString("name");
                String comment = resultSet.getNString("comment");
                BigDecimal cost = resultSet.getBigDecimal("cost");
                LocalDate createDate = resultSet.getDate("createDate").toLocalDate();
                return Optional.of(new Project(id, name, comment, cost, createDate));
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return Optional.empty();
        } finally {
            closeConnection();
        }
        return Optional.empty();
    }

    public Optional<Project> getProject(String name) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        String selectQuery = "SELECT id, name, comment, cost, createDate " +
                "FROM projects " +
                "WHERE name = '" + name + "'";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQuery);
            if (resultSet.first()) {
                int id = resultSet.getInt("id");
                String comment = resultSet.getNString("comment");
                BigDecimal cost = resultSet.getBigDecimal("cost");
                if (resultSet.getDate("createDate") == null) {
                    return Optional.of(new Project(id, name, comment, cost));
                } else {
                    LocalDate createDate = resultSet.getDate("createDate").toLocalDate();
                    return Optional.of(new Project(id, name, comment, cost, createDate));
                }
            }
        } catch (SQLException e) {
            log.error("Result set error. " + e.getMessage());
            return Optional.empty();
        } finally {
            closeConnection();
        }
        return Optional.empty();
    }

    public List<Project> getAllProjects() throws InternalException {
        List<Project> projects = new ArrayList<>();
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        String selectQuery = "Select id, name, comment, cost, createDate FROM projects";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getNString("name"));
                project.setComment(resultSet.getNString("comment"));
                project.setCost(resultSet.getBigDecimal("cost"));
                if (resultSet.getDate("createDate") != null)
                    project.setCreateDate(resultSet.getDate("createDate").toLocalDate());
                projects.add(project);
            }
        } catch (SQLException e) {
            log.error("Result set error. " + e.getMessage());
            return projects;
        } finally {
            closeConnection();
        }
        return projects;
    }

    public void addProject(Project project) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        initPreparedStatements();
        try {
            insertStatement.setString(1, project.getName());
            insertStatement.setString(2, project.getComment());
            insertStatement.setBigDecimal(3, project.getCost());
            insertStatement.setObject(4, project.getCreateDate());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("execute query error. funtion: addProject" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deleteProject(Project project) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        initPreparedStatements();

        try {
            deleteStatement.setInt(1, project.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("execute query error. funtion: deleteProject" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void editProject(Project project, String name, String comment, double cost, LocalDate createDate) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        initPreparedStatements();

        try {
            editStatement.setInt(5, project.getId());
            editStatement.setString(1, name);
            editStatement.setString(2, comment);
            editStatement.setDouble(3, cost);
            editStatement.setDate(4, java.sql.Date.valueOf(createDate));
            editStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("execute query error. funtion: editProjects" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void initPreparedStatements() {
        try {
            insertStatement = connection.prepareStatement("INSERT INTO projects (name, comment, cost, createDate) VALUES (?, ?, ?, ?)");
            deleteStatement = connection.prepareStatement("DELETE FROM projects WHERE id = ?");
            editStatement = connection.prepareStatement("UPDATE projects SET name = ?, comment = ?, cost = ?, createDate = ? WHERE id = ?");
        } catch (SQLException e) {
            log.error("init statement error. funtion: initPreparedStatements" +  e.getMessage());
        }
    }

}
