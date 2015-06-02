package com.work.dao.hibernate;

import com.work.dao.AdminDAO;
import com.work.entity.Book;
import com.work.entity.Request;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Eugine Sokirka on 29.05.2015.
 */
public class AdminHibernateImpl implements AdminDAO {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public boolean addBook(String title, String author, int year, int pages) {
        Book book = new Book(title, author, year, pages, 0);
        session.getTransaction().begin();
        session.save(book);
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return book != null;
    }

    @Override
    public boolean deleteBook(String title, String author) {
        Query queryBook = session.createQuery("from Book where title=:title and author=:author");
        queryBook.setParameter("title", title);
        queryBook.setParameter("author", author);
        Book book = (Book) queryBook.list().get(0);

        session.getTransaction().begin();
        session.delete(book);
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return book != null;
    }

    @Override
    public List<Request> allRequests() {
        Query queryRequest = session.createQuery("from Request where done=0");
        List<Request> requestList = queryRequest.list();
        return requestList;
    }

    @Override
    public boolean updateRequest(int request_id, int home, int library) {
        session.getTransaction().begin();
        Request request = (Request) session.get(Request.class, request_id);
        if(request != null){
            request.setDone(1);
            request.setHome(home);
            request.setLibrary(library);
            session.merge(request);
            session.getTransaction().commit();

            changeBookStatus(request_id);
            return true;
        }
        HibernateUtil.getSessionFactory().close();
        return false;
    }

    @Override
    public void changeBookStatus(int request_id) {
        Query queryBook_Id = session.createQuery("select book_id from Request where request_id=:request_id");
        queryBook_Id.setParameter("request_id", request_id);
        Integer book_id = (Integer) queryBook_Id.list().get(0);

        session.getTransaction().begin();
        Book book = (Book) session.get(Book.class, book_id);
        book.setBusy(1);
        session.merge(book);
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }
}
