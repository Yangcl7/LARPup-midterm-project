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

@DisplayName("story comment tests")
class StoryCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private StoryComment storyComment;

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
		storyComment = em.find(StoryComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		storyComment = null;
	}
	
//	'1','This story is amazing.','2019-03-06 23:57:41','1','3'
	@Test
	void test_storycomment_mappings() {
		assertEquals("This story is amazing.", storyComment.getComment());
		assertEquals("2019-03-06 23:57:41.0", storyComment.getPostTime().toString());
	}
	
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private User user;
//		'1','NerdAvenger','Greg','Kraehenbuehl','greg','KingNerd@gmail.com','admin','1981-03-06','1','1','2018-02-15 23:57:41'
	@Test
	void test_storycomment_user_association() {
		User u = storyComment.getUser();
		assertEquals("NerdAvenger", u.getNickname());
		assertEquals("Greg", u.getFirstName());
		assertEquals("Kraehenbuehl", u.getLastName());
		assertEquals("KingNerd@gmail.com", u.getEmail());
		assertEquals("admin", u.getRole());
		assertEquals("1981-03-06", u.getBirthDate().toString());
		assertEquals("2018-02-15 23:57:41.0", u.getCreateDate().toString());
	}

//	@ManyToOne
//	@JoinColumn(name="story_id")
//	private Story story;
//		'3','Firefly','An space drama with a crew of unrelated people surviving on nothing.','6','3','1','2018-04-26 23:57:41'
	@Test
	void test_storycomment_story_association() {
		Story s = storyComment.getStory();
		assertEquals("Firefly", s.getName());
		assertEquals("An space drama with a crew of unrelated people surviving on nothing.", s.getDescription());
		assertEquals("2018-04-26 23:57:41.0", s.getCreateDate().toString());
	}
	
	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}
}
