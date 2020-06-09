package com.kosarev.dbconnection.service;

import com.kosarev.dbconnection.domain.Developer;
import com.kosarev.dbconnection.domain.Project;
import com.kosarev.dbconnection.repository.ProjectDAO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectService {

    private final ProjectDAO projectDAO = new ProjectDAO();

    public Project getProject(int id) {
        return projectDAO.getProject(id).orElseThrow(() -> {throw new RuntimeException("Project not found by id " + id);});
    }

    public Project getProject(String name) {
        return projectDAO.getProject(name).orElseThrow(() -> {throw new RuntimeException("Project not found by name " + name);});
    }

    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    public void addProject(Project project) {
        projectDAO.addProject(project);
    }

    public void deleteProject(Project project) {
        projectDAO.deleteProject(project);
    }

    public void editProject(Project project, String name, String comment, double cost, LocalDate createDate) {
        projectDAO.editProject(project, name, comment, cost, createDate);
    }

    public List<String> formatedProjectList() {
        ArrayList<String> projects = new ArrayList<>();
        for (Project project : getAllProjects()) {
            String projectName = project.getName();
            projects.add(String.format("%s - %s - %s", Optional.ofNullable(project.getCreateDate()).map(LocalDate::toString).orElse("empty date"),
                    projectName, countDevelopersInProject(projectName)));
        }
        return projects;
    }

    private int countDevelopersInProject(String projectName) {
        return new DeveloperService().projectDevelopers(projectName).size();
    }
}
