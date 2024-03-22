package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.model.Screen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ScreenRepositoryTest {
    @Autowired
    ScreenRepositoryI screenRepositoryI;
    @Test
    public void findByThaeterId(){
        List<Screen> screens = screenRepositoryI.findByThaeterId(1l);
        assertNotNull(screens);
        assertNotEquals(screens.size(), 0);
    }
}
