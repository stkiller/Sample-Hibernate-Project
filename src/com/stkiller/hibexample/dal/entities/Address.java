package com.stkiller.hibexample.dal.entities;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andrei Podoprigora
 * Date: 09/12/11
 * Time: 07:54
 */
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "block_number", nullable = false)
    private String blockNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
