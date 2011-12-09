package com.stkiller.hibexample.test;

import com.stkiller.hibexample.dal.entities.Address;
import com.stkiller.hibexample.dal.entities.User;
import com.stkiller.hibexample.dal.helpers.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Andrei Podoprigora
 * Date: 09/12/11
 * Time: 07:40
 */
public class HibernateUtilTest {

    private Session session;
    private User user;

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Test
    public void testGetSessionFactory() throws Exception {
        assert session != null;
    }

    @Test
    public void testWriteUser() throws Exception {
        user = new User();
        user.setName("First user");
        user.setPassword("First Password");
        Address address = new Address();
        address.setStreet("First street");
        address.setBlockNumber("First block number");
        user.setAddress(address);
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }
    
    @Test
    public void testReadUser() throws Exception {
        user = readFirstUser();
        assert user != null;
        System.out.println(user);
    }

    @Test
    public void testDeleteUser() throws Exception {
        if (user == null) {
            user = readFirstUser();
        }
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        assert user==null;
    }

    @Test
    public void testShutdown() throws Exception {
        HibernateUtil.shutdown();
        assert session == null;
    }
    
    private User readFirstUser() {
        List<User> users = session.createQuery("from User").list();
        assert users != null;
        assert 0 < users.size();
        return users.get(0);
    }
}
