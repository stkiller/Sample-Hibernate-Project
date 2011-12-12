package com.stkiller.hibexample.test;

import com.stkiller.hibexample.dal.entities.Address;
import com.stkiller.hibexample.dal.entities.User;
import com.stkiller.hibexample.dal.helpers.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andrei Podoprigora
 * Date: 09/12/11
 * Time: 07:40
 */
public class HibernateUtilTest {

    private Session session;

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Test
    public void testGetSessionFactory() throws Exception {
        assertNotNull(session);
    }

    @Test
    public void testWriteUser() throws Exception {
        User user = new User();
        user.setName("First user");
        user.setPassword("First Password");
        Address address = new Address();
        address.setStreet("First street");
        address.setBlockNumber("First block number");
        user.addAddress(address);
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        List<Address> addresses = (List<Address>) session.createQuery("from Address where user.id = " +user.getId()).list();
        assertNotNull(addresses);
        assertTrue(0 < addresses.size());
        System.out.println(user);
    }
    
    @Test
    public void testWriteUserWithManyAddresses() {
        User user = new User();
        user.setName("Second user");
        user.setPassword("Second password");
        for (int i = 0; i < 5; i++) {
            Address address = new Address();
            address.setStreet("street" + i);
            address.setBlockNumber(String.valueOf(i));
            user.addAddress(address);
        }
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        System.out.println(user);
    }

    @Test
    public void testReadUser() throws Exception {
        User user = getUserWithOneAddress();
        assertNotNull(user);
        System.out.println(user);
    }

    private User getUserWithOneAddress() {
        List<User> users = session.createQuery("from User where addresses.size <= 1").list();
        User user = null;
        if ((users != null) && (0< users.size())) {
            user = users.get(0);
        } else {
            fail("Users are null or empty");
        }
        return user;
    }

    @Test
    public void testReadUserWithManyAddresses() {
        User user = getUserWithManyAddresses();
        System.out.println(user);
        assertNotNull(user);
        if (user != null) {
            user = deleteUser(user);
        }
    }

    private User getUserWithManyAddresses() {
        User user=null;
        List<User> users = session.createQuery("from User where addresses.size > 1").list();
        if ((users != null) && (0 < users.size())) {
            user = users.get(0);
        }
        return user;
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = getUserWithOneAddress();
        assertNotNull(user);
        if (user != null) {
            user = deleteUser(user);
        }
    }
    
    @Test
    public void testAllDeleted() {
        List<User> users = session.createQuery("from User ").list();
        assertTrue(users.size()==0);
    }

    @Test
    public void testShutdown() throws Exception {
        HibernateUtil.shutdown();
        assertNotNull(session);
    }
    
    private User deleteUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        return user;
    }
}
