package com.curtisnewbie.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import com.curtisnewbie.model.DeliveryOption;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class DeliveryOptionRepositoryTest {

    @Inject
    DeliveryOptionRepository repo;

    @Test
    @Order(1)
    public void shouldHaveNoDeliveryOpt() {
        assertEquals(0, repo.getAllDelivOpt().size());
    }

    @Test
    @Order(2)
    public void shouldThrowExceptions() {
        var id = 123456654;
        assertThrows(WebApplicationException.class, () -> {
            repo.getDelivOptById(id);
        });
        assertThrows(WebApplicationException.class, () -> {
            repo.removeDelivOptById(id);
        });
        assertThrows(WebApplicationException.class, () -> {
            DeliveryOption opt = new DeliveryOption();
            opt.setId(id);
            opt.setName("name");
            opt.setPrice(1.2);
            repo.updateDelivOpt(opt);
        });
    }

    @Test
    @Order(3)
    public void shouldCreateDeliveryOpt() {
        var name = "name";
        var price = 1.2;
        DeliveryOption opt = new DeliveryOption();
        opt.setName(name);
        opt.setPrice(price);
        var createdOpt = repo.createDelivOpt(opt);
        assertNotNull(createdOpt);
        assertNotNull(createdOpt.getId());
        assertEquals(name, createdOpt.getName());
        assertEquals(price, createdOpt.getPrice());
    }

    @Test
    @Order(4)
    public void shouldUpdateDeliveryOpt() {
        var allOpts = repo.getAllDelivOpt();
        assertTrue(allOpts.size() > 0);
        var opt = allOpts.get(0);
        assertNotNull(opt);

        // change state
        var diffName = opt.getName() + "Diff";
        var diffPrice = opt.getPrice() + 1.1;
        opt.setName(diffName);
        opt.setPrice(diffPrice);

        var updatedOpt = repo.updateDelivOpt(opt);
        assertNotNull(updatedOpt);
        assertEquals(opt.getId(), updatedOpt.getId());
        assertEquals(diffName, updatedOpt.getName());
        assertEquals(diffPrice, updatedOpt.getPrice());
    }

    @Test
    @Order(5)
    public void shouldDeleteDeliveryOpt() {
        var allOpts = repo.getAllDelivOpt();
        assertTrue(allOpts.size() > 0);
        var opt = allOpts.get(0);
        assertNotNull(opt);

        var id = opt.getId();
        assertNotNull(id);
        repo.removeDelivOptById(id);
    }
}