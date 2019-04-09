package com.skilldistillery.larpup.entities;

import static org.junit.Assert.assertEquals;
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

@DisplayName("picture tests")
class PictureTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Picture picture;

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
		picture = em.find(Picture.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		picture = null;
	}
	@Test
	void test_picture_url_link_mapping() {
		assertEquals("https://m.media-amazon.com/images/M/MV5BMjEzMjk1NTA1Ml5BMl5BanBnXkFtZTcwNjQyNjAwOQ@@._V1_UX214_CR0,0,214,317_AL_.jpg", picture.getUrl());
		assertEquals("profile image",picture.getAlt());
	}

	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
