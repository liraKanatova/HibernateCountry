package peaksoft.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.model.Address;
import peaksoft.model.Country;
import peaksoft.model.Programmer;
import peaksoft.model.Project;

import java.util.Properties;

public class HibernateConfig {

    public  static EntityManagerFactory createEntityManagerFactory(){
        Properties properties = new Properties();
        properties.put(Environment.DRIVER,"org.postgresql.Driver");
        properties.put(Environment.URL,"jdbc:postgresql://localhost:5432/java");
        properties.put(Environment.USER,"postgres");
        properties.put(Environment.PASS,"postgres");

        properties.put(Environment.HBM2DDL_AUTO,"update");
        properties.put(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL,"true");
        properties.put(Environment.FORMAT_SQL,"true");

        Configuration configuration = new Configuration();
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(Programmer.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Country.class);

      return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);

    }
}
