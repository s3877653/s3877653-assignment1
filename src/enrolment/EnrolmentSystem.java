package enrolment;

import java.util.ArrayList;
import java.util.Scanner;

public class EnrolmentSystem implements StudentEnrolmentManager {
    public static void main(String[] args) {


    }


    ArrayList<StudentEnrolment> enrolList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    //take student information from the enrolList
    public Student checkStudentID(String id){
        Student student;
        for(StudentEnrolment stuEnrol: enrolList){
            if(stuEnrol.student.getId().equals(id)) {
                student = stuEnrol.student;
                return student;
            }
        }
        //if there is no student ID in enrolList that match the input
        System.out.println("Sid not found");
        return null;
    }

    //take course information from the enrolList
    public Course checkCourseID(String id){
        Course course;
        for(StudentEnrolment stuEnrol: enrolList){
            if(stuEnrol.course.getId().equals(id)){
                course = stuEnrol.course;
                return course;
            }
            //if there is no course ID in enrolList that match the input
                System.out.println("Course id not found");
                return null;
        }

    }
    public void add(String semester, String studentID, String courseID) {
        Student student = checkStudentID(studentID);
        Course course = checkCourseID(courseID);
        //These are the letter that should be included in the semester last character
        ArrayList<Character> checkList = new ArrayList<>(3);
        checkList.add('A');
        checkList.add('B');
        checkList.add('C');
        //check if the student, course is exist or not and the semester is valid or not
        if (student == null || course == null || semester.length() !=5 || !(checkList.contains(semester.charAt(4)))) {
            System.out.println("in valid input"); }
        else{
            StudentEnrolment stuEnrol = new StudentEnrolment(student,course,semester);
            enrolList.add(stuEnrol);
        }
    }

    @Override
    public void update(StudentEnrolment studentEnrol) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {

    }
}
