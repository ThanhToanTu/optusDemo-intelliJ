package optus.assessment.demo;

import optus.assessment.demo.model.SearchText;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	private RestTemplate restTemplate;
	private HttpHeaders headers;

	@Before
	public void setup() {
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	@Test
	public void SearchTextTest() {

		final String uri = "http://localhost:8080/counter-api/search";

		SearchText input = new SearchText();

		List<String> texts = new ArrayList<String>();

		texts.add("Duis");
		texts.add("Sed");

		input.setSearchTexts(texts);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<SearchText> request = new HttpEntity<>(input, headers);

		String result = restTemplate.postForObject(uri, input, String.class);

		Assert.assertEquals("{\"counts\":[{\"Sed\":16,\"Duis\":11}]}", result);

	}

	@Test
	public void top5Test() {

		final String uri = "http://localhost:8080/counter-api/top/5";

		headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertTrue(result.toString().contains("VEL|17\n" +
				"EGET|17\n" +
				"SED|16\n" +
				"IN|15\n" +
				"ET|14"));

	}

}
