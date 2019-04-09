package com.skilldistillery.larpup.entities;

import static org.junit.Assert.assertNull;
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

@DisplayName("story tests")
class StoryTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Story story;

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
		story = em.find(Story.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		story = null;
	}

//	'1','Game of Thrones','An epic story about battles, and possibly dragons.','6','3','2','2018-03-15 23:57:41'
	@Test
	void test_story_entity_mappings() {
		assertEquals("Game of Thrones", story.getName());
		assertEquals("An epic story about battles, and possibly dragons.", story.getDescription());
		assertEquals("2018-03-15 23:57:41.0", story.getCreateDate().toString());
	}
	
//	@ManyToOne
//	@JoinColumn(name="address_id")
//	private Address address;
//		'6',NULL,'Colorado Springs','Colorado','80903',NULL
	@Test
	void test_story_address_association() {
		assertNull(story.getAddress().getStreet());
		assertEquals("Colorado Springs", story.getAddress().getCity());
		assertEquals("Colorado", story.getAddress().getState());
		assertEquals("80903", story.getAddress().getZipcode());
		assertNull(story.getAddress().getPhone());
	}
	
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private User user;
//		'3','Pikachu','Young','Awesome','young','ClassicRed@hotmail.com','user','1999-04-01','3','3','2018-02-15 23:57:41'
	@Test
	void test_story_user_association() {
		assertEquals("Pikachu", story.getUser().getNickname());
		assertEquals("Young", story.getUser().getFirstName());
		assertEquals("Awesome", story.getUser().getLastName());
		assertEquals("young", story.getUser().getPassword());
		assertEquals("ClassicRed@hotmail.com", story.getUser().getEmail());
		assertEquals("user", story.getUser().getRole());
		assertEquals("1999-04-01", story.getUser().getBirthDate().toString());
		assertEquals("2018-02-15 23:57:41.0", story.getUser().getCreateDate().toString());
	}
	
//	@ManyToOne
//	@JoinColumn(name="genre_id")
//	private Genre genre;
//		'2','Medievil','5'
	@Test
	void test_story_genre_association() {
		assertEquals("Medievil", story.getGenre().getName());
	}
	
	@Test
	void test_story_event_list_association() {
		int size = story.getEvents().size();
		Event evt = new Event();
		evt.setId(5);
		story.addEvent(evt);
		assertEquals(size + 1, story.getEvents().size());
		story.removeEvent(evt);
		assertEquals(size, story.getEvents().size());
	}

	@Test
	void test_story_eventuserinfo_list_association() {
		int size = story.getEventRoles().size();
		EventUserInfo evtUsrNfo = new EventUserInfo();
		evtUsrNfo.setId(5);
		story.addEventUserInfo(evtUsrNfo);
		assertEquals(size + 1, story.getEventRoles().size());
		story.removeEventUserInfo(evtUsrNfo);
		assertEquals(size, story.getEventRoles().size());
	}
	
	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
