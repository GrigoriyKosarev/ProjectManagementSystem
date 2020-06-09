package com.kosarev.dbconnection.service;

import com.kosarev.dbconnection.domain.Developer;
import com.kosarev.dbconnection.domain.Sex;
import com.kosarev.dbconnection.error.InternalException;
import com.kosarev.dbconnection.repository.DeveloperDAO;

import java.util.ArrayList;
import java.util.List;

public class DeveloperService {

    private final DeveloperDAO developerDAO = new DeveloperDAO();

    public Developer getDeveloper(int id) throws InternalException {
        return developerDAO.getDeveloper(id).orElseThrow(() -> {throw new RuntimeException("Developer not found by id " + id);});
    }

    public Developer getDeveloper(String name) throws InternalException {
        return developerDAO.getDeveloper(name).orElseThrow(() -> {throw new RuntimeException("Developer not found by name " + name);});
    }

    public List<Developer> getAllDevelopers() throws InternalException {
        return developerDAO.getAllDevelopers();
    }

    public void addDeveloper(Developer developers) throws InternalException {
        developerDAO.addDeveloper(developers);
    }

    public void deleteDeveloper(Developer developers) throws InternalException {
        developerDAO.deleteDeveloper(developers);
    }

    public void editDeveloper(Developer developers, String name, int age, Sex sex, double salary) throws InternalException {
        developerDAO.editDeveloper(developers, name, age, sex, salary);
    }

    public double salaryFromAllDevelopersInProject(String projectName) throws InternalException {
        return developerDAO.salaryFromAllDevelopersInProject(projectName);
    }

    public List<Developer> projectDevelopers(String projectName) throws InternalException {
        return developerDAO.projectDevelopers(projectName);
    }

    public List<Developer> developersSkill(String skillName) throws InternalException {
        return developerDAO.developerSkill(skillName);
    }

    public List<Developer> developersSkillLevel(String skillLevel) throws InternalException {
        return developerDAO.developersSkillLevel(skillLevel);
    }

}
