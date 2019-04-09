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

@DisplayName("event tests")
class EventTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Event event;

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
		event = em.find(Event.class, 5);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		event = null;
	}
	@Test
	void test_name_for_event_test_mapping() {
		assertEquals("Marriage of Two Races", event.getName());
		assertEquals("Thrall and Aggra finally get together to have a marriage to bring two kingdoms together.",event.getDescription());
		assertEquals("2018-07-15T23:57:41", event.getDate().toString());
		assertEquals(2,event.getStory().getId());
		assertEquals(5, event.getAddress().getId());
	}
	
//	'5', '1701 Bryant St', 'Denver', 'Colorado', '80204', '7202583000'

	@Test
	void test_manytoone_association_between_event_and_address() {
		assertEquals("1701 Bryant St", event.getAddress().getStreet());
		assertEquals("Denver", event.getAddress().getCity());
		assertEquals("Colorado", event.getAddress().getState());
		assertEquals("80204", event.getAddress().getZipcode());
		assertEquals("7202583000", event.getAddress().getPhone());
	}
	
	@Test
	void test_event_eventuser_onetomany_list_association() {
		int size = event.getEventUsers().size();
		EventUser evtUsr = new EventUser();
		evtUsr.setId(5);
		event.addEventUser(evtUsr);
		assertEquals(size + 1, event.getEventUsers().size());
		event.removeEventUser(evtUsr);
		assertEquals(size, event.getEventUsers().size());
	}

	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
