package com.skilldistillery.larpup.entities;

import static org.junit.Assert.assertTrue;
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

@DisplayName("user tests")
class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 3);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}
	@Test
	void test_user_mapping() {
		assertEquals(3, user.getId());
		assertEquals("Pikachu", user.getNickname());
		assertEquals("Young", user.getFirstName());
		assertEquals("Awesome", user.getLastName());
		assertEquals("young", user.getPassword());
		assertEquals("ClassicRed@hotmail.com", user.getEmail());
		assertEquals("1999-04-01", user.getBirthDate().toString());
		assertTrue(user.isActive());
		
	}
	@Test
	void test_onetoone_mapping_user_have_many_pictures() {
		assertEquals("https://m.media-amazon.com/images/M/MV5BMTQyNjUzMDk5N15BMl5BanBnXkFtZTcwNzk0Nzk5Mw@@._V1_UY317_CR10,0,214,317_AL_.jpg", user.getPicture().getUrl());
		assertEquals("profile image", user.getPicture().getAlt());
		
	}
	@Test
	void test_manytoOne_mapping_where_many_user_have_one_address() {
		assertEquals("3753 Firehose Circle", user.getAddress().getStreet());
		assertEquals("Raleigh", user.getAddress().getCity());
		assertEquals("North Carolina", user.getAddress().getState());
		assertEquals("27616", user.getAddress().getZipcode());
		assertEquals("9197686467", user.getAddress().getPhone());
	}
	
	@Test
	void test_user_eventuser_onetomany_list_association() {
		int size = user.getEventRoles().size();
		EventUser evtUsr = new EventUser();
		evtUsr.setId(5);
		user.addEventUser(evtUsr);
		assertEquals(size + 1, user.getEventRoles().size());
		user.removeEventUser(evtUsr);
		assertEquals(size, user.getEventRoles().size());
	}

	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
