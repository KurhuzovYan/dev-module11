package global.goit.utils;

import global.goit.entities.Client;
import global.goit.entities.Planet;
import global.goit.entities.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static final HibernateUtils INSTANCE;

    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtils();
    }

    private HibernateUtils() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

    public static HibernateUtils getInstance() {
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        sessionFactory.close();
    }
}
