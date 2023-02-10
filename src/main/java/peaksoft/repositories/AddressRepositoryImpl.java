package peaksoft.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.model.Address;
import peaksoft.model.Country;

import java.util.List;

public class AddressRepositoryImpl implements AddressRepository,AutoCloseable {
private EntityManagerFactory entityManagerFactory = HibernateConfig.createEntityManagerFactory();

    @Override
    public String saveAddress(Address address,Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country country = entityManager.find(Country.class, id);
        address.setCountry(country);
        entityManager.persist(address);
        entityManager.merge(address);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully saved!";
    }

    @Override
    public String saveAllAddress(List<Address> addresses,Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country country = entityManager.find(Country.class, id);
        for (Address address : addresses) {
            address.setCountry(country);
            entityManager.persist(address);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully saved all address!";
    }

    @Override
    public List<Address> getAllAddress() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Address> resultList = entityManager.createQuery
                ("select a from Address a", Address.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public Address findByAddressId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Address id1 = entityManager.createQuery("select a from Address a where a.id = :id", Address.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return id1;
    }

    @Override
    public String deleteAddressId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Address id1 = entityManager.createQuery("select a from Address a where id=:id", Address.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.remove(id1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "successfully deleted!";
    }

    @Override
    public void deleteAllAddress() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Address> resultList = entityManager.createQuery("select a from Address a", Address.class)
                .getResultList();
        for (Address address : resultList) {
            entityManager.remove(address);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(" Successfully deleted!");

    }

    @Override
    public Address updateAddress(Long id, Address address) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Address address1 = entityManager.getReference(Address.class, id);
        address1.setRegionName(address.getRegionName());
        address1.setStreet(address.getStreet());
        address1.setHomeNumber(address.getHomeNumber());
        entityManager.merge(address1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return address1;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
