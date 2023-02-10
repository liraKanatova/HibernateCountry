package peaksoft.services;


import peaksoft.config.enums.Country;
import peaksoft.model.Programmer;
import peaksoft.model.Project;
import peaksoft.repositories.ProgrammerRepository;
import peaksoft.repositories.ProgrammerRepositoryImpl;
import peaksoft.repositories.ProjectRepository;
import peaksoft.repositories.ProjectRepositoryImpl;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    ProjectRepository projectRepository = new ProjectRepositoryImpl();
    @Override
    public String saveProject(Project project) {
        projectRepository.saveProject(project);
        return "Successfully saved";
    }

    @Override
    public String saveAllProject(List<Project> projects) {
        projectRepository.saveAllProject(projects);
        return "Successfully saved!";
    }

    @Override
    public List<Project> getAllProject() {
        return projectRepository.getAllProject();
    }

    @Override
    public Project findByProjectId(Long id) {
        return projectRepository.findByProjectId(id);
    }

    @Override
    public String deleteProjectId(Long id) {
        projectRepository.deleteProjectId(id);
        return "is deleted";
    }

    @Override
    public void deleteAllProject() {
        projectRepository.deleteAllProject();

    }

    @Override
    public Project updateProject(Long id, Project project) {
        return projectRepository.updateProject(id, project);
    }

    @Override
    public String assignProgrammerToProject(Long programmerId,Long projectId) {
        projectRepository.assignProgrammerToProject(programmerId, projectId);
        return "Successfully assign";
    }

    @Override
    public Project findTheBestExpensiveProject() {
        return projectRepository.findTheBestExpensiveProject();
    }

    @Override
    public Project findTheFastWrittenProject() {
        return projectRepository.findTheFastWrittenProject();
    }
}

