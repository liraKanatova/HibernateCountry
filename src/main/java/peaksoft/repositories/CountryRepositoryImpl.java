package peaksoft.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.model.Country;

import java.util.List;

public class CountryRepositoryImpl implements CountryRepository,AutoCloseable {
    private EntityManagerFactory entityManagerFactory = HibernateConfig.createEntityManagerFactory();

    @Override
    public String saveCountry(Country country) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(country);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully saved!";
    }

    @Override
    public String saveAllCountry(List<Country> countries) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for (Country country : countries) {
            entityManager.persist(country);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully saved all!";
    }

    @Override
    public List<Country> getAllCountry() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Country> resultList = entityManager.createQuery("select c from Country c", Country.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public Country findByCountryId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country result = entityManager.createQuery("select c from Country c " +
                        "where c.id= :id", Country.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result ;
    }

    @Override
    public String deleteCountryById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country id1 = entityManager.createQuery(
                        "select c from Country c" +
                                " where c.id= :id",
                        Country.class).
                setParameter("id", id)
                .getSingleResult();
        entityManager.remove(id1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "successfully deleted!";
    }

    @Override
    public void deleteAllCounty() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Country> resultList = entityManager.createQuery("select c from Country c", Country.class)
                .getResultList();
        for (Country country : resultList) {
            entityManager.remove(country);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(" Successfully deleted!");

    }

    @Override
    public List<Country> findTheLongestDescription() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Country> resultList = entityManager.createQuery("from Country where length(description)=" +
                        "(select max(length(description)) from Country)",
                Country.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList ;
    }

    @Override
    public Country updateCountry(Long id, Country country) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country id1 = entityManager.createQuery("select c from Country c" +
                        " where c.id= :id", Country.class)
                .setParameter("id", id)
                .getSingleResult();
        id1.setCountry(country.getCountry());
        id1.setDescription(country.getDescription());
        entityManager.getTransaction().commit();
        entityManager.close();
        return id1;
    }

    @Override
    public int CountryProgrammerInCountry(peaksoft.config.enums.Country countryName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List name = entityManager.createQuery("select c from Country c" +
                        " where c.country=:name")
                .setParameter("name", countryName)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return name.size();
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
