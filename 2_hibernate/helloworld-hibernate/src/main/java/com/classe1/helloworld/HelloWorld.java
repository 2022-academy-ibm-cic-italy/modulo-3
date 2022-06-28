package com.classe1.helloworld;

import com.classe1.helloworld.persistence.HibernateUtil;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }

    public void sayHello() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Message message = new Message("Hello World");
        Long helloWorldMessageId = (Long) session.save(message);
        LOGGER.debug("{} message id", helloWorldMessageId);
        tx.commit();
        session.close();

        getAllMessages();

        HibernateUtil.getSessionFactory().close();
    }

    private void getAllMessages() throws HibernateException {
        Session newSession = HibernateUtil.getSessionFactory().openSession();
        Transaction newTransaction = newSession.beginTransaction();

        List<Message> messages = newSession.createQuery("from Message m order by m.text asc").list();
        LOGGER.debug("{} message(s) found", messages.size());
        for (Message msg : messages) {
            LOGGER.debug(msg.toString());
        }
        newTransaction.commit();
        newSession.close();
    }
}
