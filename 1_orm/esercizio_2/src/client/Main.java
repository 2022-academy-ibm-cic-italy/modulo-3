package client;

import org.hibernate.Session;

import entity.Book;
import entity.Chapter;
import entity.ChapterId;
import entity.Publisher;
import util.HibernateUtil;

public class Main {
	public static void main(String[] args) {
			
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();

		//persisting object graph of Book
		
		Publisher publisher = new Publisher("QWERY", "Media.");        			
		Book book = new Book("9781449334376", "Hibernate", publisher);			

		Chapter chapter1 = new Chapter(1, "Basics");
		book.addChapter(chapter1);
		
		Chapter chapter2 = new Chapter(2, "Fundamentals");
		book.addChapter(chapter2);

		session.persist(book);		 		
		
		
		session.getTransaction().commit();
		session.close();	
	}
}
