import com.odyssey.model.Availability;
import com.odyssey.model.Employee;
import com.odyssey.model.Topic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTests {
    private Employee employee = new Employee("Joe", "Bloggs", "joebloggs@email.com", "12345");
    private Availability availability = new Availability(true, true, true, true,
            true, true, true, true, true, true, true,
            true, true, true, true, true, true,
            true, true, true, true, true, true,
            true, true, true, true, true, true, true);
    private Topic topic = new Topic("Java");

    @Test
    void testGetDisplayName() {
        String displayName = employee.getFullName();
        assertEquals("Joe Bloggs", displayName);
    }

    @Test
    void testGetFirstName() {
        String displayName = employee.getFirstName();
        assertEquals("Joe", displayName);
    }

    @Test
    void testGetLastName() {
        String displayName = employee.getLastName();
        assertEquals("Bloggs", displayName);
    }

    @Test
    void testSetFirstName() {
        employee.setFirstName("Mary");
        assertEquals("Mary", employee.getFirstName());
    }

    @Test
    void testBecomeMentor() {
        employee.becomeMentor(topic, availability, 4);
        assertTrue(employee.isMentor());
    }

    @Test
    void testGetMentorDuration() {
        employee.becomeMentor(topic, availability, 4);
        assertEquals(4, employee.getMentorDuration());
    }

    @Test
    void testGetIsManager() {
        assertFalse(employee.isManager());
    }
}
