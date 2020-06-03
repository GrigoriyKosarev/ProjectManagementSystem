package com.kosarev.dbconnection.service;

import com.kosarev.dbconnection.domain.Developers;
import com.kosarev.dbconnection.domain.Projects;
import com.kosarev.dbconnection.repository.ProjectsDAO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectsService {

    private final ProjectsDAO projectsDAO = new ProjectsDAO();

    public Projects getProjects(int id) {
        return projectsDAO.getProjects(id).orElseThrow(() -> {throw new RuntimeException("Projects not found by id " + id);});
    }

    public Projects getProjects(String name) {
        return projectsDAO.getProjects(name).orElseThrow(() -> {throw new RuntimeException("Projects not found by name " + name);});
    }

    public List<Projects> getAllProjects() {
        return projectsDAO.getAllProjects();
    }

    public void addProjects(Projects projects) {
        projectsDAO.addProjects(projects);
    }

    public void deleteProjects(Projects projects) {
        projectsDAO.deleteProjects(projects);
    }

    public void editProjects(Projects projects, String name, String comment, double cost, LocalDate createDate) {
        projectsDAO.editProjects(projects, name, comment, cost, createDate);
    }

    public List<String> formatedProjectList() {
        ArrayList<String> projects = new ArrayList<>();
        for (Projects project : getAllProjects()) {
            projects.add(formatProjectAsString(project));
        }
        return projects;
    }

    private String formatProjectAsString(Projects project) {
        if (project.getCreateDate() != null)
            return project.getCreateDate().toString() + " - " + project.getName() + " - " + countDevelopersInProject(project.getName());
        else
            return "empty date - " + project.getName() + " - " + countDevelopersInProject(project.getName());
    }

    private int countDevelopersInProject(String projectName) {
        return new DevelopersService().projectDevelopers(projectName).size();
    }
}
