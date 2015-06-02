package com.work.dao.hibernate;

import com.work.dao.UserDAO;
import com.work.entity.Book;
import com.work.entity.Request;
import com.work.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugine Sokirka on 29.05.2015.
 */
public class UserHibernateImpl implements UserDAO {

    @Override
    public void addUser(String first_name, String second_name, String login, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = new User(first_name, second_name, login, password);
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public boolean validateUser(String login, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User ");
        List<User> userLiSt = query.list();
        for(User user : userLiSt){
            if(user.getLogin().equals(login) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> getAllFreeBook() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Book ");
        List<Book> bookList = query.list();
        return bookList;
    }

    @Override
    public Book findBook(String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Book where title=:title");
        query.setParameter("title", title);
        List<Book> bookList = query.list();
        Book book = bookList.get(0);
        return book;
    }

    @Override
    public boolean addRequest(String title, String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryBook_Id = session.createQuery("from Book where title=:title");
        queryBook_Id.setParameter("title", title);
        Book book = (Book) queryBook_Id.list().get(0);
        int book_id = book.getBook_id();

        Query queryUser_Id = session.createQuery("from User where login=:login");
        queryUser_Id.setParameter("login", login);
        User user = (User) queryUser_Id.list().get(0);
        int user_id = user.getUser_id();
        Request request = new Request(book_id, user_id, 0, 0, 0);
        session.getTransaction().begin();
        session.save(request);
        session.getTransaction().commit();
        return false;
    }

    @Override
    public List<Book> getUsersBook(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryUser_Id = session.createQuery("from User where login=:login");
        queryUser_Id.setParameter("login", login);
        User user = (User) queryUser_Id.list().get(0);
        int user_id = user.getUser_id();

        Query queryBook_Id = session.createQuery("select book_id from Request where user_id=:user_id and done=1 and (home=1 or library=1)");
        queryBook_Id.setParameter("user_id", user_id);
        List<Integer> book_idList = queryBook_Id.list();

        System.out.println(book_idList.toString());

        List<Book> bookList = new ArrayList<>();
        for(Integer book_id: book_idList){

            Book book = (Book) session.get(Book.class, book_id);
            bookList.add(book);

        }

        return bookList;
    }

    @Override
    public boolean deleteUsersBook(String title, String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryBook_Id = session.createQuery("from Book where title=:title");
        queryBook_Id.setParameter("title", title);
        Book book = (Book) queryBook_Id.list().get(0);
        int book_id = book.getBook_id();

        Query queryUser_Id = session.createQuery("from User where login=:login");
        queryUser_Id.setParameter("login", login);
        User user = (User) queryUser_Id.list().get(0);
        int user_id = user.getUser_id();

        Query queryRequest = session.createQuery("from Request where book_id=:book_id and user_id=:user_id");
        queryRequest.setParameter("book_id", book_id);
        queryRequest.setParameter("user_id", user_id);
        Request request = (Request) queryRequest.list().get(0);

        request.setHome(0);
        request.setLibrary(0);

        System.out.println(request.getRequest_id());
        session.getTransaction().begin();
        session.merge(request);
        session.getTransaction().commit();
        changeBookStatus(title);
        return false;
    }

    @Override
    public void changeBookStatus(String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryBook = session.createQuery("from Book where title=:title");
        queryBook.setParameter("title", title);
        Book book = (Book) queryBook.list().get(0);

        if(book != null){
            book.setBusy(0);
        }
        session.getTransaction().begin();
        session.merge(book);
        session.getTransaction().commit();
    }
}
