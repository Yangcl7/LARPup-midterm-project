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

@DisplayName("genre tests")
class GenreTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Genre genre;

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
		genre = em.find(Genre.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		genre = null;
	}

//	'1','Space Operah','4'
	@Test
	void test_genre_entity_mappings() {
		assertEquals("Space Operah", genre.getName());
	}
	
//	@OneToOne
//	@JoinColumn(name="picture_id")
//	private Picture picture;
	@Test
	void test_genre_picture_association() {
		assertEquals("https://starwarsblog.starwars.com/wp-content/uploads/2018/08/battlefront-2-clone-troopers-tall.jpg", genre.getPicture().getUrl());
		assertEquals("genre image", genre.getPicture().getAlt());
	}
	
	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
