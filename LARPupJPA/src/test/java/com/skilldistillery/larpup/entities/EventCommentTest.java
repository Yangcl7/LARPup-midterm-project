package com.skilldistillery.larpup.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

@DisplayName("event Comment tests")
class EventCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private EventComment eventComment;

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
		eventComment = em.find(EventComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		eventComment = null;
	}

	@Test
	void test_eventcomment_mappings() {
		assertEquals("Dude that guy was such a tool.", eventComment.getComment());
		assertEquals("2019-03-06T23:57:41", eventComment.getPostTime().toString());
	}
	@Test
	void test_eventcomment_manyToOne_event_userid_mapping() {
		assertEquals("NerdAvenger",eventComment.getUser().getNickname());
		assertEquals("Greg", eventComment.getUser().getFirstName());
		assertEquals("Kraehenbuehl", eventComment.getUser().getLastName());
		assertEquals("greg", eventComment.getUser().getPassword());
		assertEquals("KingNerd@gmail.com", eventComment.getUser().getEmail());
		assertEquals("admin", eventComment.getUser().getRole());
		assertEquals("1981-03-06", eventComment.getUser().getBirthDate().toString());
		
	}
	@Test
	void test_eventcomment_ManyToOne_event_eventid_mapping() {
		assertEquals("Train Heist", eventComment.getEvent().getName());
		assertEquals("The team are on a mission to take some much needed medical suplies from a train.", eventComment.getEvent().getDescription());
		assertEquals("2018-06-15 23:57:41.0", eventComment.getEvent().getDate().toString());
		
	}
	
	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
