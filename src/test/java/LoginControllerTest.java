import com.classnotice.services.UserService;
import com.classnotice.LoginController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mainservlet-servlet.xml")
public class LoginControllerTest {
	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks
	private LoginController loginController;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup(loginController).build();
	}

	@Test
	public void shouldLoginSuccessfully() throws Exception{
		mockMvc.perform(post("/login")
			.param("id", "2014220402028")
			.param("password", "123456"))
			.andDo(print())
			.andExpect(redirectedUrl("/"));
			//.andExpect(status().isOk());
	}
}
