package com.pizzaiolo.authorization;

import static org.assertj.core.api.Assertions.assertThat;

import com.pizzaiolo.authorization.models.entities.Role;
import com.pizzaiolo.authorization.repositories.RoleRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthorizationApplicationTests {
	static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer(DockerImageName.parse("mongo:4.2"));

	@BeforeAll
	static void beforeAll() {
		MONGO_DB_CONTAINER.start();
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("mongodb://0.0.0.0:27017/admin?directConnection=true&ssl=false", MONGO_DB_CONTAINER::getReplicaSetUrl);
	}

	@Autowired
	RoleRepository roleRepository;

	@Test
	void contextLoads() {
		List<Role> roleList = roleRepository.findAll();

		assertThat(roleList.size()).isEqualTo(5);
	}
}
