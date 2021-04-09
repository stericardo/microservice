package com.microservice.api.todo.list.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.util.Optional;

@ActiveProfiles("integration-test")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {TestConfiguration.class, ServiceConfig.class})
public abstract class BaseIntegrationTest {

    public abstract void initializeData();
    public abstract void loadSpecificData();

    @Autowired
    protected ObjectMapper objectMapper;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private boolean initOnce = true;

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup(TestInfo testInfo) throws Exception {
        if(initOnce) {
            initOnce();
        }
        String displayName = testInfo.getDisplayName();
        Optional<Method> methodOptional = testInfo.getTestMethod();
        if( methodOptional.isPresent() ){
            Method method = methodOptional.get();
            LoadCustomData loadCustomData = method.getAnnotation(LoadCustomData.class);
            if(loadCustomData != null){
                loadSpecificData();
            }
        }
    }

    private void initOnce(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        initOnce = false;
        cleanTables();
        initializeData();
    }

    private void cleanTables() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        tx = entityManager.getTransaction();
        tx.begin();

        Query query = entityManager.createNativeQuery("truncate table task;"
                + "truncate table building cascade;"
                + "truncate table person cascade;"
                + "truncate table project cascade;"
                + "ALTER SEQUENCE task_id_seq RESTART;"
                + "ALTER SEQUENCE project_id_seq RESTART;"
                + "ALTER SEQUENCE person_id_seq RESTART;"
                + "ALTER SEQUENCE building_id_seq RESTART;"
        );
        query.executeUpdate();
        tx.commit();
    }

}
