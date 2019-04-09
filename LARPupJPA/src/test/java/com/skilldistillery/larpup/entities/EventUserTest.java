package com.skilldistillery.larpup.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("event user tests")
class EventUserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private EventUser eventUser;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("LARPupJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		eventUser = em.find(EventUser.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		eventUser = null;
	}

//	'1','2018-11-28 23:57:41',NULL,'4','6','3'
	@Test
	void test_eventuser_mappings() {
		assertEquals("2018-11-28 23:57:41.0", eventUser.getSignupDate().toString());
		assertEquals(4, eventUser.getEvent().getId());
		assertEquals(6, eventUser.getPicture().getId());
		assertEquals(3, eventUser.getEventUserInfo().getId());
	}
	
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private User user;
	
	// This test works but will remain disabled since the eventUser may or may not be null depending on the DB setup
	@Disabled
	@Test
	void test_eventuser_user_association() {
		assertNull(eventUser.getUser());
	}
	
//	@ManyToOne
//	@JoinColumn(name="event_id")
//	private Event event;
//		'4','Hands of Blue','The team are captured and get to meet those with hands of blue.','2018-11-15 23:57:41','3','4','2018-11-23 23:57:41'
	@Test
	void test_eventuser_event_association() {
		assertEquals("Hands of Blue", eventUser.getEvent().getName());
		assertEquals("The team are captured and get to meet those with hands of blue.", eventUser.getEvent().getDescription());
		assertEquals("2018-11-15 23:57:41.0", eventUser.getEvent().getDate().toString());
		assertEquals("2018-11-23 23:57:41.0", eventUser.getEvent().getCreateDate().toString());
	}
	
//	@ManyToOne
//	@JoinColumn(name="picture_id")
//	private Picture picture;
//		'6','https://upload.wikimedia.org/wikipedia/en/1/13/MalReynoldsFirefly.JPG','Malcolm Reynolds'
	@Test
	void test_eventuser_picture_association() {
		assertEquals("https://upload.wikimedia.org/wikipedia/en/1/13/MalReynoldsFirefly.JPG", eventUser.getPicture().getUrl());
		assertEquals("Malcolm Reynolds", eventUser.getPicture().getAlt());
	}
	
	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}
}