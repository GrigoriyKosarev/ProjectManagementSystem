package com.kosarev.dbconnection.service;

import com.kosarev.dbconnection.domain.Developer;
import com.kosarev.dbconnection.domain.Sex;
import com.kosarev.dbconnection.repository.DeveloperDAO;

import java.util.ArrayList;
import java.util.List;

public class DeveloperService {

    private final DeveloperDAO developerDAO = new DeveloperDAO();

    public Developer getDeveloper(int id) {
        return developerDAO.getDeveloper(id).orElseThrow(() -> {throw new RuntimeException("Developer not found by id " + id);});
    }

    public Developer getDeveloper(String name) {
        return developerDAO.getDeveloper(name).orElseThrow(() -> {throw new RuntimeException("Developer not found by name " + name);});
    }

    public List<Developer> getAllDevelopers() {
        return developerDAO.getAllDevelopers();
    }

    public void addDeveloper(Developer developers) {
        developerDAO.addDeveloper(developers);
    }

    public void deleteDeveloper(Developer developers) {
        developerDAO.deleteDeveloper(developers);
    }

    public void editDeveloper(Developer developers, String name, int age, Sex sex, double salary) {
        developerDAO.editDeveloper(developers, name, age, sex, salary);
    }

    public double salaryFromAllDevelopersInProject(String projectName) {
        return developerDAO.salaryFromAllDevelopersInProject(projectName);
    }

    public List<Developer> projectDevelopers(String projectName) {
        return developerDAO.projectDevelopers(projectName);
    }

    public List<Developer> developersSkill(String skillName) {
        return developerDAO.developerSkill(skillName);
    }

    public List<Developer> developersSkillLevel(String skillLevel) {
        return developerDAO.developersSkillLevel(skillLevel);
    }

}
