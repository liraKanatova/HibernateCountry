package peaksoft.repositories;

import peaksoft.model.Project;

import java.util.List;

public interface ProjectRepository {
    String saveProject(Project project);
    String saveAllProject(List<Project>projects);
    List<Project> getAllProject();
    Project findByProjectId(Long id);
    String deleteProjectId(Long id);
    void deleteAllProject();
    Project updateProject(Long id,Project project);
    String assignProgrammerToProject(Long programmerId,Long projectId);
    Project findTheBestExpensiveProject();
    Project findTheFastWrittenProject();

}
