package peaksoft.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.model.Programmer;
import peaksoft.model.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository,AutoCloseable{
    private  EntityManagerFactory entityManagerFactory = HibernateConfig.createEntityManagerFactory();

    @Override
    public String saveProject(Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(project);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully saved";
    }

    @Override
    public String saveAllProject(List<Project> projects) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for (Project project : projects) {
            entityManager.persist(project);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully saved!";
    }

    @Override
    public List<Project> getAllProject() {
     EntityManager entityManager = entityManagerFactory.createEntityManager();
     entityManager.getTransaction().begin();
        List resultList = entityManager.createQuery("from Project ", Project.class).getResultList();
        entityManager.getTransaction().commit();
     entityManager.close();
        return resultList;
    }

    @Override
    public Project findByProjectId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Project result = entityManager.createQuery("select p from Project p where id=:id", Project.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public String deleteProjectId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Object id1 = entityManager.createQuery("select p from Project p where id=:id", Project.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.remove(id1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "is deleted";
    }

    @Override
    public void deleteAllProject() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Project> resultList = entityManager.createQuery("select p from Project p", Project.class)
                .getResultList();
        for (Project project : resultList) {
            entityManager.remove(project);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(" Successfully deleted!");

    }

    @Override
    public Project updateProject(Long id, Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Project project1 = entityManager.getReference(Project.class, id);
        project1.setProjectName(project.getProjectName());
        project1.setDescription(project.getDescription());
        project1.setDateOfStart(project.getDateOfStart());
        project1.setDateOfFinish(project.getDateOfFinish());
        project1.setPrice(project.getPrice());
        entityManager.merge(project1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return project1;
    }

    @Override
    public String assignProgrammerToProject(Long programmerId, Long projectId) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Project project = entityManager.find(Project.class, projectId);
    Programmer programmer = entityManager.find(Programmer.class,programmerId);
    List<Project> projects = new ArrayList<>(Arrays.asList(project));
    List<Programmer> programmers = new ArrayList<>(Arrays.asList(programmer));
    project.setProgrammers(programmers);
    programmer.setProjects(projects);
    entityManager.merge(programmer);
    entityManager.getTransaction().commit();
    entityManager.close();
    return "Successfully assign";
    }

    @Override
    public Project findTheBestExpensiveProject() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Project result = entityManager.createQuery("select p from Project p " +
                        "order by price desc limit 1", Project.class)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public Project findTheFastWrittenProject() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Project project = entityManager
                .createQuery("from Project where dateOfFinish - dateOfStart = " +
                "(select min(dateOfFinish - dateOfStart) from Project )", Project.class)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return project;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
