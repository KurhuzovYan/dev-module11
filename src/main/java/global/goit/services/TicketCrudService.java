package global.goit.services;

import global.goit.entities.Client;
import global.goit.entities.Planet;
import global.goit.entities.Ticket;
import global.goit.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;

import java.util.List;

public class TicketCrudService {

    public void createTicket(Ticket ticket) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(ticket);
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException();
        }
    }

    public Ticket readTicket(Long ticketId) {
        Ticket ticket;
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            ticket = session.get(Ticket.class, ticketId);
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException();
        }
        return ticket;
    }

    public void updateTicker(Long ticketId, Client client, Planet fromPlanetId, Planet toPlanetId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("UPDATE Ticket SET client =: client, " +
                            "fromPlanetId =: fromPlanetId," +
                            "toPlanetId =: toPlanetId WHERE id =: planet_id")
                    .setParameter("client", client)
                    .setParameter("fromPlanetId", fromPlanetId)
                    .setParameter("toPlanetId", toPlanetId)
                    .setParameter("planet_id", ticketId)
                    .executeUpdate();
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException();
        }
    }

    public void deleteTicket(Long ticketId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Ticket WHERE id= :ticket_id")
                    .setParameter("ticket_id", ticketId)
                    .executeUpdate();
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException();
        }
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets;
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            tickets = session.createQuery("from Ticket", Ticket.class).list();
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException();
        }
        return tickets;
    }
}
