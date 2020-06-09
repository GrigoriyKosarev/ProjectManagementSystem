package com.kosarev.dbconnection.repository;

import com.kosarev.dbconnection.domain.Developer;
import com.kosarev.dbconnection.domain.Sex;
import com.kosarev.dbconnection.error.InternalException;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class DeveloperDAO extends ConnectorDB{

    private PreparedStatement insertStatement;
    private PreparedStatement editStatement;
    private PreparedStatement deleteStatement;

    public Optional<Developer> getDeveloper(int id) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        String selectQwery = "SELECT id, name, age,  sex, salary " +
                "FROM developers " +
                "WHERE id = " + id;
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            if (resultSet.first()) {
                String name = resultSet.getNString("name");
                int age = resultSet.getInt("age");
                Sex sex = Sex.valueOf(resultSet.getNString("sex"));
                BigDecimal salary = resultSet.getBigDecimal("salary");
                return Optional.of(new Developer(id, name, age, sex, salary));
            }
        } catch (SQLException e) {
            log.error("Result set error. " + e.getMessage());
            return Optional.empty();
        } finally {
            closeConnection();
        }
        return Optional.empty();
    }

    public Optional<Developer> getDeveloper(String name) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        String selectQwery = "SELECT id, name, age,  sex, salary " +
                "FROM developers " +
                "WHERE name = '" + name + "'";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            if (resultSet.first()) {
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                Sex sex = Sex.valueOf(resultSet.getNString("sex"));
                BigDecimal salary = resultSet.getBigDecimal("salary");
                return Optional.of(new Developer(id, name, age, sex, salary));
            }
        } catch (SQLException e) {
            log.error("Result set error. " + e.getMessage());
            return Optional.empty();
        } finally {
            closeConnection();
        }
        return Optional.empty();
    }

    public List<Developer> getAllDevelopers() throws InternalException {
        List<Developer> developers = new ArrayList<>();
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        String selectQwery = "Select id, name, age, sex, salary FROM developers";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            while (resultSet.next()) {
                Developer developer = new Developer();
                developer.setId(resultSet.getInt("id"));
                developer.setName(resultSet.getNString("name"));
                developer.setAge(resultSet.getInt("age"));
                developer.setSex(Sex.valueOf(resultSet.getNString("sex")));
                developer.setSalary(resultSet.getBigDecimal("salary"));
                developers.add(developer);
            }
        } catch (SQLException e) {
            log.error("Result set error. " + e.getMessage());
            return developers;
        } finally {
            closeConnection();
        }
        return developers;
    }

    public void addDeveloper(Developer developer) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        initPreparedStatements();
        try {
            insertStatement.setString(1, developer.getName());
            insertStatement.setInt(2, developer.getAge());
            insertStatement.setString(3, developer.getSex().toString());
            insertStatement.setBigDecimal(4, developer.getSalary());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("execute qwery error. funtion: addDevelopers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deleteDeveloper(Developer developer) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        initPreparedStatements();

        try {
            deleteStatement.setInt(1, developer.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("execute qwery error. funtion: deleteDevelopers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void editDeveloper(Developer developer, String name, int age, Sex sex, double salary) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        initPreparedStatements();

        try {
            editStatement.setInt(5, developer.getId());
            editStatement.setString(1, name);
            editStatement.setInt(2, age);
            editStatement.setString(3,sex.toString());
            editStatement.setDouble(4, salary);
            editStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("execute qwery error. funtion: editDevelopers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public double salaryFromAllDevelopersInProject(String projectName) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
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
            log.error("Result set error. " + e.getMessage());
            return 0;
        } finally {
            closeConnection();
        }
        return 0;
    }

    public List<Developer> projectDevelopers(String projectName) throws InternalException {
        List<Developer> developers = new ArrayList<>();
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        String selectQuery = "SELECT projects.name as project_name, " +
                "developers.name as developer_name, " +
                "developers.id as developer_id, " +
                "developers.age as developer_age, " +
                "developers.sex as developer_sex,  " +
                "developers.salary as developer_salary  " +
                "FROM developers_projects " +
                "Left join developers on developers.id = developers_projects.developer_id " +
                "Left join projects on projects.id = developers_projects.project_id " +
                "where projects.name = '" + projectName + "'";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()){
                Developer developer = new Developer();
                developer.setId(resultSet.getInt("developer_id"));
                developer.setName(resultSet.getNString("developer_name"));
                developer.setAge(resultSet.getInt("developer_age"));
                developer.setSex(Sex.valueOf(resultSet.getNString("developer_sex")));
                developer.setSalary(resultSet.getBigDecimal("developer_salary"));
                developers.add(developer);
            }
        } catch (SQLException e) {
            log.error("Result set error. " + e.getMessage());
            return developers;
        } finally {
            closeConnection();
        }
        return developers;
    }

    public List<Developer> developerSkill(String skillName) throws InternalException {
        List<Developer> developers = new ArrayList<>();
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        String selectQwery = "SELECT " +
                "skills.name as skill_name, " +
                "developers.id as developer_id, " +
                "developers.name as developer_name, " +
                "developers.age as developer_age, " +
                "developers.sex as developer_sex, " +
                "developers.salary as developer_salary " +
                "FROM skills_developers " +
                "left join skills on skills_developers.skill_id = skills.id " +
                "left join developers on skills_developers.developer_id = developers.id " +
                "where skills.name = '" + skillName + "'";

        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            while (resultSet.next()){
                Developer developer = new Developer();
                developer.setId(resultSet.getInt("developer_id"));
                developer.setName(resultSet.getNString("developer_name"));
                developer.setAge(resultSet.getInt("developer_age"));
                developer.setSex(Sex.valueOf(resultSet.getNString("developer_sex")));
                developer.setSalary(resultSet.getBigDecimal("developer_salary"));
                developers.add(developer);
            }
        } catch (SQLException e) {
            log.error("Result set error. " + e.getMessage());
            return developers;
        } finally {
            closeConnection();
        }
        return developers;
    }

    public List<Developer> developersSkillLevel(String skillLevel) throws InternalException {
        List<Developer> developers = new ArrayList<>();
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        String selectQwery = "SELECT " +
                "skill_levels.name as skill_levels_name, " +
                "developers.id as developer_id, " +
                "developers.name as developer_name, " +
                "developers.age as developer_age, " +
                "developers.sex as developer_sex, " +
                "developers.salary as developer_salary " +
                "FROM skills_developers " +
                "left join skill_levels on skills_developers.skill_level_id = skill_levels.id " +
                "left join developers on skills_developers.developer_id = developers.id " +
                "where skill_levels.name = '" + skillLevel + "'";

        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            while (resultSet.next()){
                Developer developer = new Developer();
                developer.setId(resultSet.getInt("developer_id"));
                developer.setName(resultSet.getNString("developer_name"));
                developer.setAge(resultSet.getInt("developer_age"));
                developer.setSex(Sex.valueOf(resultSet.getNString("developer_sex")));
                developer.setSalary(resultSet.getBigDecimal("developer_salary"));
                developers.add(developer);
            }
        } catch (SQLException e) {
            log.error("Result set error. " + e.getMessage());
            return developers;
        } finally {
            closeConnection();
        }
        return developers;
    }

    private void initPreparedStatements() {
        try {
            insertStatement = connection.prepareStatement("INSERT INTO developers (name, age, sex, salary) VALUES (?, ?, ?, ?)");
            deleteStatement = connection.prepareStatement("DELETE FROM developers WHERE id = ?");
            editStatement = connection.prepareStatement("UPDATE developers SET name = ?, age = ?, sex = ?, salary = ? WHERE id = ?");
        } catch (SQLException e) {
            log.error("init statement error. funtion: initPreparedStatements" +  e.getMessage());
        }
    }

}
