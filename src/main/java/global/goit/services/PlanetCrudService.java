package global.goit.services;

import global.goit.entities.Planet;
import global.goit.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudService {

    public void createPlanet(Planet planet) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(planet);
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException();
        }
    }

    public Planet readPlanet(String planetId) {
        Planet planet;
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            planet = session.get(Planet.class, planetId);
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException();
        }
        return planet;
    }

    public void updatePlanet(String planetId, String name) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("UPDATE Planet SET name =: name WHERE id =: planet_id")
                    .setParameter("name", name)
                    .setParameter("planet_id", planetId)
                    .executeUpdate();
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException();
        }
    }

    public void deletePlanet(String planetId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Planet WHERE id= :planet_id")
                    .setParameter("planet_id", planetId)
                    .executeUpdate();
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException();
        }
    }

    public List<Planet> getAllPlanets() {
        List<Planet> planets;
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            planets = session.createQuery("FROM Planet", Planet.class).list();
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException();
        }
        return planets;
    }
}
