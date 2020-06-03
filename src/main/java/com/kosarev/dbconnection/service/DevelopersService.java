package com.kosarev.dbconnection.service;

import com.kosarev.dbconnection.domain.Developers;
import com.kosarev.dbconnection.domain.Sex;
import com.kosarev.dbconnection.repository.DevelopersDAO;

import java.util.ArrayList;
import java.util.List;

public class DevelopersService {

    private final DevelopersDAO developersDAO = new DevelopersDAO();

    public Developers getDevelopers(int id) {
        return developersDAO.getDevelopers(id).orElseThrow(() -> {throw new RuntimeException("Developer not found by id " + id);});
    }

    public Developers getDevelopers(String name) {
        return developersDAO.getDevelopers(name).orElseThrow(() -> {throw new RuntimeException("Developer not found by name " + name);});
    }

    public List<Developers> getAllDevelopers() {
        return developersDAO.getAllDevelopers();
    }

    public void addDevelopers(Developers developers) {
        developersDAO.addDevelopers(developers);
    }

    public void deleteDevelopers(Developers developers) {
        developersDAO.deleteDevelopers(developers);
    }

    public void editDevelopers(Developers developers, String name, int age, Sex sex, double salary) {
        developersDAO.editDevelopers(developers, name, age, sex, salary);
    }

    public double salaryFromAllDevelopersInProject(String projectName) {
        return developersDAO.salaryFromAllDevelopersInProject(projectName);
    }

    public List<Developers> projectDevelopers(String projectName) {
        List<Developers> developers = new ArrayList<>();
        for (String developerName : developersDAO.projectDevelopers(projectName)) {
            developers.add(getDevelopers(developerName));
        }
        return developers;
    }

    public List<Developers> developersSkill(String skillName) {
        List<Developers> developers = new ArrayList<>();
        for (String developerName : developersDAO.developersSkill(skillName)) {
            developers.add(getDevelopers(developerName));
        }
        return developers;
    }

    public List<Developers> developersSkillLevel(String skillLevel) {
        List<Developers> developers = new ArrayList<>();
        for (String developerName : developersDAO.developersSkillLevel(skillLevel)) {
            developers.add(getDevelopers(developerName));
        }
        return developers;
    }

}
