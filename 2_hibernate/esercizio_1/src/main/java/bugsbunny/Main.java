package bugsbunny;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import model.Contact;

public class Main {

	public static void main(String[] args) {
		//PUNTO 1
		Configuration configuration = new Configuration().configure();
		ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
		registry.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry.buildServiceRegistry());
		
		//PUNTO 2
		Session session = sessionFactory.openSession();
		
		session.beginTransaction(); //PUNTO 9
		
		//PUNTO 3
		Contact contact1 = new Contact();
		contact1.setCognome("test");
		contact1.setNome("test");
		contact1.setEmail("email");
		contact1.setIndirizzo("via del test");
		contact1.setTelefono("testtest21234");
		
		Contact contact2 = new Contact();
		contact2.setCognome("test2");
		contact2.setNome("test2");
		contact2.setEmail("email2");
		contact2.setIndirizzo("via del test 2");
		contact2.setTelefono("12345");
		
		session.persist(contact1);
		session.getTransaction().commit();
		
		session.persist(contact2);
		session.getTransaction().commit();

		//PUNTO 4
		Contact contact3 = (Contact) session.get(Contact.class, 250);
		if (contact3 == null) {
			System.out.println("Il dato non esiste. 404 not found");
		} else {
			System.out.println("contatto con id = " + contact3.getId());	
		}
		session.getTransaction().commit();

		//PUNTO 5
		Contact contact4 = (Contact) session.get(Contact.class, 1);
		if (contact4 == null) {
			System.out.println("Il dato non esiste. 404 not found");
		} else {
			System.out.println("contatto con id = " + contact4.getId());	
		}
		session.getTransaction().commit();

		//PUNTO 6
		contact4.setCognome("cognome modificato");
		contact4.setTelefono("Telefono modificato");
		session.update(contact4);
		session.getTransaction().commit();

		//PUNTO 7
		session.delete(contact4);
		session.getTransaction().commit();

		//PUNTO 8
		session.close();
		sessionFactory.close();
				
	}
}
