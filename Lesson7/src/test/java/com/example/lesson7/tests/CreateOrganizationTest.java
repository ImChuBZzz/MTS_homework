package com.example.lesson7.tests;

import com.example.lesson7.data.Organization;
import com.example.lesson7.data.User;
import com.example.lesson7.data.exceptions.RequiredFieldMissedException;
import com.example.lesson7.service.OrganizationService;
import com.example.lesson7.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class CreateOrganizationTest {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserService userService;

    private static final User TEST_USER = new User("Q", "R", "A", "12345");
    private static final Organization TEST_ORG = new Organization("n", "img", "1", "d", "i", "o",
                                                                "k", "ok", "okp", "b", "bik", "p",
            "e", 2, "a", Timestamp.valueOf("2000-01-01 01:02:03"), Timestamp.valueOf("2000-02-01 04:05:06"), "d");

    @Test
    public void CreateOrganizationTesting() {
        userService.createUser(TEST_USER);
        TEST_ORG.setContactPersonId(TEST_USER.getId());
        organizationService.createOrganization(TEST_ORG);
    }

    @Test
    public void TestEmptyField() {
        Organization org = TEST_ORG;
        org.setINN("");
        Assertions.assertThrows(RequiredFieldMissedException.class, ()->organizationService.createOrganization(org));
    }
}
