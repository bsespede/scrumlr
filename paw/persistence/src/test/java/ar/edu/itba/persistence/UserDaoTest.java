package ar.edu.itba.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.interfaces.dao.ProjectDao;
import ar.edu.itba.interfaces.dao.UserDao;
import ar.edu.itba.models.Project;
import ar.edu.itba.models.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserDaoTest {

		@Autowired
		private DataSource ds;

		@Autowired
		private UserDao ud;
		
		@Autowired
		private ProjectDao pd;
		
		private User user;
		private JdbcTemplate jdbcTemplate;
		private final String name = "Braulius";
		private final String password = "secret password";
		private final String mail = "braulius@braulicus.com";
		private final String fakeName = "fake name used for fails, do no instantiate";
		
		@Before
		public void setup(){
			jdbcTemplate = new JdbcTemplate(ds);
			if (ud.userNameExists(name)) {
				user = ud.getByUsername(name);
			} else {
				user = ud.createUser(name, password, mail);			
			}
		}

		@Test 
		@Transactional
		public void create(){
			user = ud.createUser("False User", password, mail + "additional");
			assertEquals(user.username(), "False User");
			assertEquals(user.password(), password);
			assertEquals(user.mail(), mail + "additional" );
		}
		
		@Test(expected = IllegalStateException.class) 
		public void createCreated(){
			user = ud.createUser(name, password, mail);	
		}
		
		@Test
		public void userNameExists(){
			assertTrue(ud.userNameExists(name));
		}
		
		@Test
		public void userNameExistsInexistent(){
			assertFalse(ud.userNameExists(fakeName));
		}
		
		@Test
		public void userMailExists(){
			assertTrue(ud.userMailExists(mail));
		}
		
		@Test
		public void inexistingUserMail(){
			assertFalse(ud.userMailExists(mail + "a little something"));
		}		
		
		@Test
		public void setPasswordTest(){
			ud.setPassword(user, "newPassword");
			user = ud.getByUsername(name);
			assertEquals(user.password(), "newPassword");
			ud.setPassword(user, password);
		}
		
		@Test
		public void getAllUsernamesOfProject(){
			final Project project = pd.createProject(user, "name","description", "code");
			pd.addProjectMember(project, ud.createUser("project user", password, "project@mail.com"));
			final List<String> list = ud.getAllUsernamesOfProject(project);
			assertEquals(list.size(), 1);
		}
		
}
