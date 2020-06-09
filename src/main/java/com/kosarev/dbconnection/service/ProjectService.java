package com.kosarev.dbconnection.service;

import com.kosarev.dbconnection.domain.Developer;
import com.kosarev.dbconnection.domain.Project;
import com.kosarev.dbconnection.error.InternalException;
import com.kosarev.dbconnection.repository.ProjectDAO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectService {

    private final ProjectDAO projectDAO = new ProjectDAO();

    public Project getProject(int id) throws InternalException {
        return projectDAO.getProject(id).orElseThrow(() -> {throw new RuntimeException("Project not found by id " + id);});
    }

    public Project getProject(String name) throws InternalException {
        return projectDAO.getProject(name).orElseThrow(() -> {throw new RuntimeException("Project not found by name " + name);});
    }

    public List<Project> getAllProjects() throws InternalException {
        return projectDAO.getAllProjects();
    }

    public void addProject(Project project)throws InternalException {
        projectDAO.addProject(project);
    }

    public void deleteProject(Project project) throws InternalException {
        projectDAO.deleteProject(project);
    }

    public void editProject(Project project, String name, String comment, double cost, LocalDate createDate) throws InternalException {
        projectDAO.editProject(project, name, comment, cost, createDate);
    }

    public List<String> formatedProjectList() throws InternalException {
        ArrayList<String> projects = new ArrayList<>();
        for (Project project : getAllProjects()) {
            String projectName = project.getName();
            projects.add(String.format("%s - %s - %s", Optional.ofNullable(project.getCreateDate()).map(LocalDate::toString).orElse("empty date"),
                    projectName, countDevelopersInProject(projectName)));
        }
        return projects;
    }

    private int countDevelopersInProject(String projectName) throws InternalException {
        return new DeveloperService().projectDevelopers(projectName).size();
    }
}
