package com.stkiller.hibexample.dal.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by IntelliJ IDEA.
 * User: Andrei Podoprigora
 * Date: 09/12/11
 * Time: 07:54
 */
@Embeddable
public class Address {
    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "block_number", nullable = false)
    private String blockNumber;

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
}
