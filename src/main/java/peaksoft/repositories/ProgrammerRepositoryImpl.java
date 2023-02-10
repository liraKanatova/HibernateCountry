package peaksoft.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.config.enums.Country;
import peaksoft.model.Address;
import peaksoft.model.Programmer;

import java.util.List;

public class ProgrammerRepositoryImpl implements ProgrammerRepository,AutoCloseable {
    private EntityManagerFactory entityManagerFactory = HibernateConfig.createEntityManagerFactory();

    @Override
    public String saveProgrammer(Programmer programmer, Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Address address = entityManager.find(Address.class, id);
        programmer.setLocation(address);
        entityManager.persist(programmer);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "successfully saved";
    }

    @Override
    public String saveAllProgrammer(List<Programmer> programmers, Long id) {
     EntityManager entityManager = entityManagerFactory.createEntityManager();
     entityManager.getTransaction().begin();
        Address address = entityManager.find(Address.class, id);
        for (Programmer programmer : programmers) {
            programmer.setLocation(address);
            entityManager.persist(programmer);
        }
     entityManager.getTransaction().commit();
     entityManager.close();
        return "successfully saved";
    }

    @Override
    public void addConstrainToEmail() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("alter table programmers" +
                " add  unique (email)", Programmer.class)
                        .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("successfully added");

    }

    @Override
    public List<Programmer> getAllProgrammer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Programmer> resultList = entityManager.createQuery("from Programmer ", Programmer.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public Programmer findByProgrammerId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Programmer id1 = entityManager.createQuery("select p from Programmer p" +
                        " where p.id= :id", Programmer.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return id1;
    }

    @Override
    public String deleteProgrammerId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Programmer id1 = entityManager.createQuery("select p from Programmer p" +
                        " where p.id = :id", Programmer.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.remove(id1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "successfully deleted";
    }

    @Override
    public void deleteAllProgrammer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Programmer> list = entityManager.createQuery("select p from Programmer p", Programmer.class)
                .getResultList();
        for (Programmer programmer : list) {
            entityManager.remove(programmer);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(" Successfully deleted!");

    }

    @Override
    public Programmer updateProgrammer(Long id, Programmer programmer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Programmer id1 = entityManager.createQuery("select p from Programmer p " +
                        "where p.id = :id", Programmer.class)
                .setParameter("id", id)
                .getSingleResult();
        id1.setFullName(programmer.getFullName());
        id1.setDateOfBirth(programmer.getDateOfBirth());
        id1.setStatus(programmer.getStatus());
        id1.setEmail(programmer.getEmail());
        entityManager.merge(id1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return id1;
    }

    @Override
    public int getProgrammerWithSameCountry(Country countryName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Country> name = entityManager.createQuery("select c from Country c " +
                        "where c.country= :name", Country.class)
                .setParameter("name", countryName)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return name.size();
    }

    @Override
    public Programmer findTheYoungestProgrammer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Programmer p = entityManager.createQuery("select p from Programmer p where dateOfBirth = min(dateOfBirth)", Programmer.class).getSingleResult();
        entityManager.getTransaction().commit();
        return p;

    }

    @Override
    public Programmer findTheOldestProgrammer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Programmer p = entityManager.createQuery("select p from  Programmer p where dateOfBirth = max (dateOfBirth)", Programmer.class).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return p;

    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
