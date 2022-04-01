package enrolment;

public class StudentEnrolment {
    Student student;
    Course course;
    String semester;


    public StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }
}
