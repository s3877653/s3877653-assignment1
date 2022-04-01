package enrolment;
interface StudentEnrolmentManager{
    public void add();
    public void update();
    public void delete();
    public void getOne();
    public void getAll();

}
public class StudentEnrolment {
    Student student;
    Course course;
    String semester;
}
