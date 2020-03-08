package com.curtisnewbie.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import com.curtisnewbie.model.HomeNew;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class HomeNewRepositoryTest {

    @Inject
    HomeNewRepository repo;

    @Test
    @Order(1)
    public void shouldHaveNoHomeNew() {
        assertEquals(0, repo.getHomeNews().size());
    }

    @Test
    @Order(2)
    public void shouldThrowExceptions() {
        var id = 123456654l;
        assertThrows(WebApplicationException.class, () -> {
            repo.getHomeNewById(id);
        });
        assertThrows(WebApplicationException.class, () -> {
            repo.removeHomeNewById(id);
        });
        assertThrows(WebApplicationException.class, () -> {
            HomeNew homeNew = new HomeNew();
            homeNew.setId(id);
            homeNew.setTitle("title");
            homeNew.setContent("content");
            homeNew.setDate(LocalDate.now());
            repo.updateHomeNew(homeNew);
        });
    }

    @Test
    @Order(3)
    public void shouldCreateDeliveryOpt() {
        var title = "title";
        var content = "content";
        var date = LocalDate.now();
        HomeNew homeNew = new HomeNew();
        homeNew.setTitle(title);
        homeNew.setContent(content);
        homeNew.setDate(date);
        var createdHomeNew = repo.createHomeNew(homeNew);
        assertNotNull(createdHomeNew);
        assertNotNull(createdHomeNew.getId());
        assertEquals(title, createdHomeNew.getTitle());
        assertEquals(content, createdHomeNew.getContent());
        assertEquals(date, createdHomeNew.getDate());
    }

    @Test
    @Order(4)
    public void shouldUpdateDeliveryOpt() {
        var allHomeNews = repo.getHomeNews();
        assertTrue(allHomeNews.size() > 0);
        var homeNew = allHomeNews.get(0);
        assertNotNull(homeNew);

        // change state
        var diffTitle = homeNew.getTitle();
        var diffContent = homeNew.getContent();
        var diffDate = homeNew.getDate().minusDays(1);
        homeNew.setTitle(diffTitle);
        homeNew.setContent(diffContent);
        homeNew.setDate(diffDate);

        var updatedHomeNew = repo.updateHomeNew(homeNew);
        assertNotNull(updatedHomeNew);
        assertEquals(homeNew.getId(), updatedHomeNew.getId());
        assertEquals(diffTitle, updatedHomeNew.getTitle());
        assertEquals(diffContent, updatedHomeNew.getContent());
        assertEquals(diffDate, updatedHomeNew.getDate());
    }

    @Test
    @Order(5)
    public void shouldDeleteDeliveryOpt() {
        var allHomeNews = repo.getHomeNews();
        assertTrue(allHomeNews.size() > 0);
        var homeNew = allHomeNews.get(0);
        assertNotNull(homeNew);

        var id = homeNew.getId();
        assertNotNull(id);
        repo.removeHomeNewById(id);
    }
}