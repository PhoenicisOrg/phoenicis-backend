package org.phoenicis.website.database.manipulation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Function;

/*
 * Database manipulation service
 */
public class DatabaseManipulationService {
    private final SessionFactory sessionFactory;

    /**
     *
     * @param sessionFactory A Hibernate session factory
     */
    public DatabaseManipulationService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * Open a database transaction and execute a code.
     * @param transactionHandler The code to execute
     * @param <R> The type of the result to expect
     * @return The result
     */
    public <R> R executeInTransaction(Function<Session, R> transactionHandler) {
        try (Session session = sessionFactory.openSession()) {
            final Transaction tx = session.beginTransaction();
            try {
                R result = transactionHandler.apply(session);
                session.flush();
                tx.commit();
                return result;
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

}
