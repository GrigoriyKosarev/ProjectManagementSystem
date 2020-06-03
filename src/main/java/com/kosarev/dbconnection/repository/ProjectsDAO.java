package com.kosarev.dbconnection.repository;

import com.kosarev.dbconnection.domain.Projects;

import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectsDAO extends ConnectorDB {

    private PreparedStatement insertStatement;
    private PreparedStatement editStatement;
    private PreparedStatement deleteStatement;

    public ProjectsDAO() {
    }

    public Optional<Projects> getProjects(int id) {
        initConnection();
        String selectQwery = "SELECT name, comment, cost, createDate " +
                "FROM goit.projects " +
                "WHERE id = " + id;
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            if (resultSet.first()) {
                String name = resultSet.getNString("name");
                String comment = resultSet.getNString("comment");
                double cost = resultSet.getDouble("cost");
                LocalDate createDate = resultSet.getDate("createDate").toLocalDate();
                return Optional.ofNullable(new Projects(id, name, comment, cost, createDate));
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return Optional.ofNullable(null);
        } finally {
            closeConnection();
        }
        return Optional.ofNullable(null);
    }

    public Optional<Projects> getProjects(String name) {
        initConnection();
        String selectQwery = "SELECT id, name, comment, cost, createDate " +
                "FROM goit.projects " +
                "WHERE name = '" + name + "'";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            if (resultSet.first()) {
                int id = resultSet.getInt("id");
                String comment = resultSet.getNString("comment");
                double cost = resultSet.getDouble("cost");
                if (resultSet.getDate("createDate") == null) {
                    return Optional.ofNullable(new Projects(id, name, comment, cost));
                } else {
                    LocalDate createDate = resultSet.getDate("createDate").toLocalDate();
                    return Optional.ofNullable(new Projects(id, name, comment, cost, createDate));
                }
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return Optional.ofNullable(null);
        } finally {
            closeConnection();
        }
        return Optional.ofNullable(null);
    }

    public List<Projects> getAllProjects() {
        initConnection();
        List<Projects> projects = new ArrayList<>();
        String selectQwery = "Select id, name, comment, cost, createDate FROM projects";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            while (resultSet.next()) {
                Projects project = new Projects();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getNString("name"));
                project.setComment(resultSet.getNString("comment"));
                project.setCost(resultSet.getDouble("cost"));
                if (resultSet.getDate("createDate") != null)
                    project.setCreateDate(resultSet.getDate("createDate").toLocalDate());
                projects.add(project);
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return projects;
        } finally {
            closeConnection();
        }
        return projects;
    }

    public void addProjects(Projects projects) {
        initConnection();
        initPreparedStatements();
        projects.setId((getLastId()+1));
        try {
            insertStatement.setInt(1, projects.getId());
            insertStatement.setString(2, projects.getName());
            insertStatement.setString(3, projects.getComment());
            insertStatement.setDouble(4, projects.getCost());
            insertStatement.setObject(5, projects.getCreateDate());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execute qwery error. funtion: addProjects" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deleteProjects(Projects projects) {
        initConnection();
        initPreparedStatements();

        try {
            deleteStatement.setInt(1, projects.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execute qwery error. funtion: deleteProjects" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void editProjects(Projects projects, String name, String comment, double cost, LocalDate createDate) {
        initConnection();
        initPreparedStatements();

        try {
            editStatement.setInt(5, projects.getId());
            editStatement.setString(1, name);
            editStatement.setString(2, comment);
            editStatement.setDouble(3, cost);
            editStatement.setDate(4, java.sql.Date.valueOf(createDate));
            editStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execute qwery error. funtion: editProjects" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void initPreparedStatements() {
        try {
            insertStatement = connection.prepareStatement("INSERT INTO projects (id, name, comment, cost, createDate) VALUES (?, ?, ?, ?, ?)");
            deleteStatement = connection.prepareStatement("DELETE FROM projects WHERE id = ?");
            editStatement = connection.prepareStatement("UPDATE projects SET name = ?, comment = ?, cost = ?, createDate = ? WHERE id = ?");
        } catch (SQLException e) {
            System.out.println("init statement error. funtion: initPreparedStatements" +  e.getMessage());
        }
    }

    private int getLastId() {
        String selectQwery = "SELECT id FROM projects ORDER BY id DESC LIMIT 1";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            if (resultSet.first()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Result set error. funtion: getLastId" + e.getMessage());
            return 0;
        } finally {
            closeResultSet();
        }
        return 0;
    }


}
