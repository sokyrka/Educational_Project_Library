package com.work.dao.jpa;

import com.work.dao.AdminDAO;
import com.work.entity.Book;
import com.work.entity.Request;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Eugine Sokirka on 29.05.2015.
 */
public class AdminJPAImpl implements AdminDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("weblogicDatasource");
    private EntityManager em = emf.createEntityManager();

    @Override
    public boolean addBook(String title, String author, int year, int pages) {
        em.getTransaction().begin();
        Book book = new Book(title, author, year, pages, 0);
        em.persist(book);
        em.getTransaction().commit();
        return book != null;
    }

    @Override
    public boolean deleteBook(String title, String author) {
        TypedQuery<Integer> bookTypedQuery = em.createQuery("SELECT b.book_id FROM Book b WHERE b.title=:title AND b.author=:author", Integer.class);
        bookTypedQuery.setParameter("title", title);
        bookTypedQuery.setParameter("author", author);
        Integer book_id = bookTypedQuery.getSingleResult();

        em.getTransaction().begin();
        Book book = em.find(Book.class, book_id);
        em.remove(book);
        em.getTransaction().commit();
        return book != null;
    }

    @Override
    public List<Request> allRequests() {
        TypedQuery<Request> requestTypedQuery = em.createQuery("SELECT r FROM Request r WHERE r.done=0", Request.class);
        return requestTypedQuery.getResultList();
    }

    @Override
    public boolean updateRequest(int request_id, int home, int library) {
        Request request = em.find(Request.class, request_id);
        if(request != null){
            em.getTransaction().begin();
            request.setDone(1);
            request.setHome(home);
            request.setLibrary(library);
            em.merge(request);
            em.getTransaction().commit();
            changeBookStatus(request_id);
            return true;
        }
        return false;
    }

    @Override
    public void changeBookStatus(int request_id) {
        TypedQuery<Integer> bookTypedQuery = em.createQuery("SELECT b.book_id FROM Request b WHERE b.request_id=:request_id", Integer.class);
        bookTypedQuery.setParameter("request_id", request_id);
        Integer book_id = bookTypedQuery.getSingleResult();
        System.out.println(book_id);

        Book book = em.find(Book.class, book_id);

        if(book != null){
            book.setBusy(1);
        }

        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
    }
}
