package com.kosarev.dbconnection.repository;

import com.kosarev.dbconnection.domain.Developers;
import com.kosarev.dbconnection.domain.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DevelopersDAO extends ConnectorDB{

    private PreparedStatement insertStatement;
    private PreparedStatement editStatement;
    private PreparedStatement deleteStatement;

    public Optional<Developers> getDevelopers(int id) {
        initConnection();
        String selectQwery = "SELECT id, name, age,  sex, salary " +
                "FROM goit.developers " +
                "WHERE id = " + id;
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            if (resultSet.first()) {
                String name = resultSet.getNString("name");
                int age = resultSet.getInt("age");
                Sex sex = Sex.valueOf(resultSet.getNString("sex"));
                double salary = resultSet.getDouble("salary");
                return Optional.ofNullable(new Developers(id, name, age, sex, salary));
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return Optional.ofNullable(null);
        } finally {
            closeConnection();
        }
        return Optional.ofNullable(null);
    }

    public Optional<Developers> getDevelopers(String name) {
        initConnection();
        String selectQwery = "SELECT id, name, age,  sex, salary " +
                "FROM goit.developers " +
                "WHERE name = '" + name + "'";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            if (resultSet.first()) {
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                Sex sex = Sex.valueOf(resultSet.getNString("sex"));
                double salary = resultSet.getDouble("salary");
                return Optional.ofNullable(new Developers(id, name, age, sex, salary));
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return Optional.ofNullable(null);
        } finally {
            closeConnection();
        }
        return Optional.ofNullable(null);
    }

    public List<Developers> getAllDevelopers() {
        initConnection();
        List<Developers> developers = new ArrayList<>();
        String selectQwery = "Select id, name, age, sex, salary FROM developers";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            while (resultSet.next()) {
                Developers developer = new Developers();
                developer.setId(resultSet.getInt("id"));
                developer.setName(resultSet.getNString("name"));
                developer.setAge(resultSet.getInt("age"));
                developer.setSex(Sex.valueOf(resultSet.getNString("sex")));
                developer.setSalary(resultSet.getDouble("salary"));
                developers.add(developer);
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return developers;
        } finally {
            closeConnection();
        }
        return developers;
    }

    public void addDevelopers(Developers developers) {
        initConnection();
        initPreparedStatements();
        developers.setId((getLastId()+1));
        try {
            insertStatement.setInt(1, developers.getId());
            insertStatement.setString(2, developers.getName());
            insertStatement.setInt(3, developers.getAge());
            insertStatement.setString(4, developers.getSex().toString());
            insertStatement.setDouble(5, developers.getSalary());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execute qwery error. funtion: addDevelopers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deleteDevelopers(Developers developers) {
        initConnection();
        initPreparedStatements();

        try {
            deleteStatement.setInt(1, developers.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execute qwery error. funtion: deleteDevelopers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void editDevelopers(Developers developers, String name, int age, Sex sex, double salary) {
        initConnection();
        initPreparedStatements();

        try {
            editStatement.setInt(5, developers.getId());
            editStatement.setString(1, name);
            editStatement.setInt(2, age);
            editStatement.setString(3,sex.toString());
            editStatement.setDouble(4, salary);
            editStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execute qwery error. funtion: editDevelopers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public double salaryFromAllDevelopersInProject(String projectName){
        initConnection();
        String selectQwery = "SELECT " +
                "sum(developers.salary) as salary " +
                "FROM projects " +
                "Left JOIN developers_projects ON developers_projects.project_id = projects.id " +
                "Left JOIN developers ON developers_projects.developer_id = developers.id " +
                "where projects.name = '" + projectName + "'";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            if (resultSet.first()) {
                return resultSet.getDouble("salary");
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return 0;
        } finally {
            closeConnection();
        }
        return 0;
    }

    public List<String> projectDevelopers(String projectName){
        initConnection();
        List<String> developers = new ArrayList<>();
        String selectQwery = "SELECT projects.name as project_name, " +
                "developers.name as developer_name " +
                "FROM developers_projects " +
                "Left join developers on developers.id = developers_projects.developer_id " +
                "Left join projects on projects.id = developers_projects.project_id " +
                "where projects.name = '" + projectName + "'";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            while (resultSet.next()){
                developers.add(resultSet.getString("developer_name"));
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return developers;
        } finally {
            closeConnection();
        }
        return developers;
    }

    public List<String> developersSkill(String skillName){
        initConnection();
        List<String> developers = new ArrayList<>();
        String selectQwery = "SELECT " +
                "skills.name as skill_name, " +
                "developers.name as developer_name " +
                "FROM skills_developers " +
                "left join skills on skills_developers.skill_id = skills.id " +
                "left join developers on skills_developers.developer_id = developers.id " +
                "where skills.name = '" + skillName + "'";

        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            while (resultSet.next()){
                developers.add(resultSet.getString("developer_name"));
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return developers;
        } finally {
            closeConnection();
        }
        return developers;
    }

    public List<String> developersSkillLevel(String skillLevel){
        initConnection();
        List<String> developers = new ArrayList<>();
        String selectQwery = "SELECT " +
                "skill_levels.name as skill_levels_name, " +
                "developers.name as developer_name " +
                "FROM skills_developers " +
                "left join skill_levels on skills_developers.skill_level_id = skill_levels.id " +
                "left join developers on skills_developers.developer_id = developers.id " +
                "where skill_levels.name = '" + skillLevel + "'";

        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            while (resultSet.next()){
                developers.add(resultSet.getString("developer_name"));
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return developers;
        } finally {
            closeConnection();
        }
        return developers;
    }

    private void initPreparedStatements() {
        try {
            insertStatement = connection.prepareStatement("INSERT INTO developers (id, name, age, sex, salary) VALUES (?, ?, ?, ?, ?)");
            deleteStatement = connection.prepareStatement("DELETE FROM developers WHERE id = ?");
            editStatement = connection.prepareStatement("UPDATE developers SET name = ?, age = ?, sex = ?, salary = ? WHERE id = ?");
        } catch (SQLException e) {
            System.out.println("init statement error. funtion: initPreparedStatements" +  e.getMessage());
        }
    }

    private int getLastId() {
        String selectQwery = "SELECT id FROM developers ORDER BY id DESC LIMIT 1";
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
