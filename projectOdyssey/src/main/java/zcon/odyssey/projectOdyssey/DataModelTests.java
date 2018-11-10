package zcon.odyssey.projectOdyssey;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectOdysseyApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DataModelTests {

    @Autowired
    private TestRestTemplate template;

    private static String EMPLOYEE_ENDPOINT = "http://localhost:8080/employeess/";
    private static String ODYSSEY_ENDPOINT = "http://localhost:8080/odyesseys/";
    private static String ODYSSEY_MEETING_ENDPOINT = "http://localhost:8080/odyessey-meetings/";
    private static String TOPIC_ENDPOINT = "http://localhost:8080/topic/";

    private static String EMPLOYEE_FIRST_NAME = "Donal";
    private static String EMPLOYEE_LAST_NAME = "McManus";
    private static String EMPLOYEE_EMAIL = "dmcmanus@gmail.com";
    private static String EMPLOYEE_ONE_FIRST_NAME = "Joe";
    private static String EMPLOYEE_ONE_LAST_NAME = "Bloggs";
    private static String EMPLOYEE_ONE_EMAIL = "jbloggs@gmail.com";
    private static String TOPIC_NAME_ONE = "Java";
    private static String TOPIC_NAME_TWO = "Git";
    private static String LOCATION_ONE = "Hill of Tara";
    private static String LOCATION_TWO = "Lambay";

    // SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
    // private static Date ODYSSEY_MEETING_DATE = fmt.parse("20-11-2018");

    // TEST FOR ONE TO ONE RELATIONSHIP BETWEEN AN ODYSSEY AND A TOPIC
    @Test
    public void OdysseyToTopicRelationship_thenCorrect() {
        Employee employeeDonal = new Employee(EMPLOYEE_FIRST_NAME,EMPLOYEE_LAST_NAME,EMPLOYEE_EMAIL);
        Employee employeeJoe = new Employee(EMPLOYEE_ONE_FIRST_NAME,EMPLOYEE_ONE_LAST_NAME,EMPLOYEE_ONE_EMAIL);
        Topic topicJava = new Topic(TOPIC_NAME_ONE);

        Odyssey odyssey = new Odyssey(employeeDonal,employeeJoe,topicJava);
        template.postForEntity(ODYSSEY_ENDPOINT, odyssey, Odyssey.class);

        Topic topic = new Topic(TOPIC_NAME_ONE);
        template.postForEntity(TOPIC_ENDPOINT, topic, Topic.class);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity
                = new HttpEntity<>(TOPIC_ENDPOINT + "/1", requestHeaders);
        template.exchange(ODYSSEY_ENDPOINT + "/1/odysseyTopic",
                HttpMethod.PUT, httpEntity, String.class);

        ResponseEntity<Odyssey> odysseyGetResponse
                = template.getForEntity(TOPIC_ENDPOINT + "/1/odyssey", Odyssey.class);
        assertEquals("odyssey is incorrect",
                odysseyGetResponse.getBody().toString(), EMPLOYEE_FIRST_NAME);
    }

    // ONE TO MANY RELATIONSHIP BETWEEN ONE ODYSSEY AND MANY ODYSSEY MEETINGS
    @Test
    public void odysseyToOdysseyMeetingsRelationship_thenCorrect() {
        Employee employeeDonal = new Employee(EMPLOYEE_FIRST_NAME,EMPLOYEE_LAST_NAME,EMPLOYEE_EMAIL);
        Employee employeeJoe = new Employee(EMPLOYEE_ONE_FIRST_NAME,EMPLOYEE_ONE_LAST_NAME,EMPLOYEE_ONE_EMAIL);
        Topic topicJava = new Topic(TOPIC_NAME_ONE);

        Odyssey odyssey = new Odyssey(employeeDonal,employeeJoe,topicJava);
        template.postForEntity(ODYSSEY_ENDPOINT, odyssey, Odyssey.class);

        OdysseyMeeting meeting1 = new OdysseyMeeting(LOCATION_ONE);
        template.postForEntity(ODYSSEY_MEETING_ENDPOINT, meeting1, OdysseyMeeting.class);

        OdysseyMeeting meeting2 = new OdysseyMeeting(LOCATION_TWO);
        template.postForEntity(ODYSSEY_MEETING_ENDPOINT, meeting2, OdysseyMeeting.class);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "text/uri-list");
        HttpEntity<String> odysseyMeetingHttpEntity
                = new HttpEntity<>(ODYSSEY_ENDPOINT + "/1", requestHeaders);
        template.exchange(ODYSSEY_MEETING_ENDPOINT + "/1/odyssey",
                HttpMethod.PUT, odysseyMeetingHttpEntity, String.class);
        template.exchange(ODYSSEY_MEETING_ENDPOINT + "/2/odyssey",
                HttpMethod.PUT, odysseyMeetingHttpEntity, String.class);

        ResponseEntity<Odyssey> odysseyGetResponse =
                template.getForEntity(ODYSSEY_MEETING_ENDPOINT + "/1/odyssey", Odyssey.class);
        assertEquals("odyssey is incorrect",
                odysseyGetResponse.getBody().toString(), EMPLOYEE_FIRST_NAME);
    }

    // ONE TO MANY BETWEEN ONE EMPLOYEE AND MANY ODYSSEYS
    @Test
    public void employeeToOdysseysRelationship_thenCorrect() {

        Employee employeeJoe = new Employee(EMPLOYEE_ONE_FIRST_NAME,EMPLOYEE_ONE_LAST_NAME,EMPLOYEE_ONE_EMAIL);
        Topic topicJava = new Topic(TOPIC_NAME_ONE);
        Topic topicGit = new Topic(TOPIC_NAME_TWO);

        Employee employeeDonal = new Employee(EMPLOYEE_FIRST_NAME,EMPLOYEE_LAST_NAME,EMPLOYEE_EMAIL);
        template.postForEntity(EMPLOYEE_ENDPOINT, employeeDonal, Employee.class);

        Odyssey odyssey1 = new Odyssey(employeeDonal, employeeJoe, topicJava);
        template.postForEntity(ODYSSEY_ENDPOINT, odyssey1, Odyssey.class);

        Odyssey odyssey2 = new Odyssey(employeeJoe, employeeDonal, topicGit);
        template.postForEntity(ODYSSEY_ENDPOINT, odyssey2, Odyssey.class);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "text/uri-list");
        HttpEntity<String> odysseyMeetingHttpEntity
                = new HttpEntity<>(EMPLOYEE_ENDPOINT + "/1", requestHeaders);
        template.exchange(ODYSSEY_ENDPOINT + "/1/employee",
                HttpMethod.PUT, odysseyMeetingHttpEntity, String.class);
        template.exchange(ODYSSEY_ENDPOINT + "/2/employee",
                HttpMethod.PUT, odysseyMeetingHttpEntity, String.class);

        ResponseEntity<Employee> employeeGetResponse =
                template.getForEntity(ODYSSEY_ENDPOINT + "/1/employee", Employee.class);
        assertEquals("odyssey is incorrect",
                employeeGetResponse.getBody().toString(), EMPLOYEE_FIRST_NAME);
    }

    @Test
    public void persistantTest(){
        Employee employeeJoe = new Employee(EMPLOYEE_ONE_FIRST_NAME,EMPLOYEE_ONE_LAST_NAME,EMPLOYEE_ONE_EMAIL);

    }

}
