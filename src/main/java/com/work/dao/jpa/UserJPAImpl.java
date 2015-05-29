package com.work.dao.jpa;

import com.work.dao.UserDAO;
import com.work.entity.Book;
import com.work.entity.Request;
import com.work.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugine Sokirka on 28.05.2015.
 */
public class UserJPAImpl implements UserDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("weblogicDatasource");
    private EntityManager em = emf.createEntityManager();

    @Override
    public void addUser(String first_name, String second_name, String login, String password) {
        em.getTransaction().begin();
        User user = new User(first_name, second_name, login, password);
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public boolean validateUser(String login, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> userLiSt = query.getResultList();

        for(User user : userLiSt){
            if(user.getLogin().equals(login) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> getAllFreeBook() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        List<Book> tmpList = query.getResultList();
        return tmpList;
    }

    @Override
    public Book findBook(String title) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.title=:title", Book.class);
        query.setParameter("title", title);
        Book book = query.getSingleResult();
        return book;
    }

    @Override
    public boolean addRequest(String title, String login) {
        TypedQuery<Integer> bookTypedQuery = em.createQuery("SELECT b.book_id FROM Book b WHERE b.title=:title", Integer.class);
        bookTypedQuery.setParameter("title", title);
        Integer book_id = bookTypedQuery.getSingleResult();

        TypedQuery<Integer> userTypedQuery = em.createQuery("SELECT u.user_id FROM User u WHERE u.login=:login", Integer.class);
        userTypedQuery.setParameter("login", login);
        Integer user_id = userTypedQuery.getSingleResult();

        em.getTransaction().begin();
        Request request = new Request(book_id, user_id, 0, 0, 0);
        em.persist(request);
        em.getTransaction().commit();

        return false;
    }

    @Override
    public List<Book> getUsersBook(String login) {
        List<Book> tmpBookList = new ArrayList<>();

        TypedQuery<Integer> userTypedQuery = em.createQuery("SELECT u.user_id FROM User u WHERE u.login=:login", Integer.class);
        userTypedQuery.setParameter("login", login);
        Integer user_id = userTypedQuery.getSingleResult();

        TypedQuery<Integer> bookTypedQuery = em.createQuery("SELECT b.book_id FROM Request b WHERE b.user_id=:user_id AND b.done=1 AND (b.home=1 OR b.library=1)", Integer.class);
        bookTypedQuery.setParameter("user_id", user_id);
        List<Integer> book_id = bookTypedQuery.getResultList();

            for(Integer b_id: book_id){
                em.getTransaction().begin();
                Book book = em.find(Book.class, b_id);
                tmpBookList.add(book);
                em.getTransaction().commit();
            }
        return tmpBookList;
    }

    @Override
    public boolean deleteUsersBook(String title, String login) {
        TypedQuery<Integer> bookTypedQuery = em.createQuery("SELECT b.book_id FROM Book b WHERE b.title=:title", Integer.class);
        bookTypedQuery.setParameter("title", title);
        Integer book_id = bookTypedQuery.getSingleResult();

        TypedQuery<Integer> userTypedQuery = em.createQuery("SELECT u.user_id FROM User u WHERE u.login=:login", Integer.class);
        userTypedQuery.setParameter("login", login);
        Integer user_id = userTypedQuery.getSingleResult();

        TypedQuery<Integer> query = em.createQuery("SELECT r.request_id FROM Request r WHERE r.book_id =:book_id AND r.user_id=:user_id", Integer.class);
        query.setParameter("book_id", book_id);
        query.setParameter("user_id", user_id);
        Integer request_id = query.getSingleResult();

        em.getTransaction().begin();
        Request request = em.find(Request.class, request_id);
        request.setHome(0);
        request.setLibrary(0);
        em.merge(request);
        em.getTransaction().commit();

        changeBookStatus(title);
        return true;
    }

    @Override
    public void changeBookStatus(String title) {
        TypedQuery<Integer> bookTypedQuery = em.createQuery("SELECT b.book_id FROM Book b WHERE b.title=:title", Integer.class);
        bookTypedQuery.setParameter("title", title);
        Integer book_id = bookTypedQuery.getSingleResult();

        Book book = em.find(Book.class, book_id);

        if(book != null){
            book.setBusy(0);
        }

        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
    }
}
