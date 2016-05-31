import com.classnotice.services.NoticeService;
import com.classnotice.db.entities.NoticeStatus;
import com.classnotice.db.NoticeDAO;
import com.classnotice.db.NoticeStatusDAO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mainservlet-servlet.xml")
public class QueryUnreadNoticeTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Mock
	private NoticeDAO noticeDao;
	@Mock
	private NoticeStatusDAO noticeStatusDao;

	@InjectMocks
	private NoticeService noticeService;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void queryUnreadNoticeTest() throws Exception{
		List<NoticeStatus> noticeStatuses=new ArrayList<NoticeStatus>();
		noticeStatuses.add(new NoticeStatus());
		noticeStatuses.add(new NoticeStatus());

		when(noticeStatusDao.QueryBySidAndReadStatus(anyString(),eq(false))).thenReturn(noticeStatuses);

		assertThat(noticeService.CountUnreadNotice("2014220402028"),is(2));

		verify(noticeStatusDao).QueryBySidAndReadStatus(anyString(),eq(false));
	}
}
