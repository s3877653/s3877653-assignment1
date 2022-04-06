package enrolment;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class EnrolmentSystemTest {
    public static EnrolmentSystem obj;
    InputStream sysInBackup = System.in;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
        obj = new EnrolmentSystem();
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);
        obj.filePopulator();


    }

    @org.junit.jupiter.api.Test
    void checkStudentID() {
        assertNotNull(obj.checkStudentID("S101312"));
    }

    @org.junit.jupiter.api.Test
    void checkCourseID() {
        assertNotNull(obj.checkCourseID("BUS2232"));
    }

    @org.junit.jupiter.api.Test
    void add() {
        ByteArrayInputStream in = new ByteArrayInputStream("2021A\nS101153\nBUS2232".getBytes());
        System.setIn(in);
        obj.add();
        assertEquals(16, obj.enrolList.size());
        assertEquals("S101153",obj.enrolList.get(15).student.getId() );
        assertEquals("BUS2232",obj.enrolList.get(15).course.getId() );
        assertEquals("2021A",obj.enrolList.get(15).semester );
        System.setIn(sysInBackup);

    }


    @org.junit.jupiter.api.Test
    void delete() {
        ByteArrayInputStream in = new ByteArrayInputStream("2020C\nS101312\nBUS2232".getBytes());
        System.setIn(in);
        obj.delete();
        assertEquals(14, obj.enrolList.size());
    }
}