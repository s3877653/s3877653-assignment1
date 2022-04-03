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
    public void add() {
        System.out.println("Enter semester: ");
        String semester = sc.nextLine();
        System.out.println("Enter student ID: ");
        String studentID = sc.nextLine();
        System.out.println("Enter course ID: ");
        String courseID = sc.nextLine();
        Student student = checkStudentID(studentID);
        Course course = checkCourseID(courseID);
        //These are the letter that should be included in the semester last character
        ArrayList<Character> checkList = new ArrayList<>(3);
        checkList.add('A');
        checkList.add('B');
        checkList.add('C');
        //check if the student, course and semester is exist or not and the semester is valid or not
        if (student == null || course == null || semester.length() !=5 || !(checkList.contains(semester.charAt(4)))) {
            System.out.println("in valid input"); }
        else{
            //if the student, course and semester information is valid create a new StudentEnrolment object then add into the enrolList
            StudentEnrolment stuEnrol = new StudentEnrolment(student,course,semester);
            enrolList.add(stuEnrol);
            System.out.println("added enrolment successfully");
        }
    }

    @Override
    public void update() {
        //tell user to input student ID
        System.out.println("Enter student ID");
        String StuID = sc.nextLine();
        //check if the ID is valid or not
        Student studentCHeck = checkStudentID(StuID);
        if(studentCHeck == null){
            System.out.println("invalid student ID!");
        }
        //print all the course that the student are currently enrolled
        for(StudentEnrolment stuEnrol : enrolList ){
            if(StuID == stuEnrol.student.getId()){
                System.out.println(stuEnrol.course.getId() + stuEnrol.course.getName() +  stuEnrol.course.getNumOfCredit());
            }
        }
        System.out.println("Do you want to add or delete the course?");
        System.out.println("1. Add");
        System.out.println("2. Delete");
        int userOption = sc.nextInt();
        if(userOption == 1){
            add();
        }else if(userOption == 2){
            delete();
        }
    }

    @Override
    public void delete() {
        System.out.println("Enter semester: ");
        String semester = sc.nextLine();
        System.out.println("Enter student ID: ");
        String studentID = sc.nextLine();
        System.out.println("Enter course ID: ");
        String courseID = sc.nextLine();
        Student student = checkStudentID(studentID);
        Course course = checkCourseID(courseID);
        //These are the letter that should be included in the semester last character
        ArrayList<Character> checkList = new ArrayList<>(3);
        checkList.add('A');
        checkList.add('B');
        checkList.add('C');
        //check if the student, course and semester is exist or not and the semester is valid or not
        if (student == null || course == null || semester.length() !=5 || !(checkList.contains(semester.charAt(4)))) {
            System.out.println("in valid input"); }
        else{
            //if the student, course and semester information is valid then compare with the list data
            for(StudentEnrolment stuEnrol : enrolList){
                if(semester == stuEnrol.semester && studentID == stuEnrol.student.getId() && courseID == stuEnrol.course.getId()){
                    enrolList.remove(stuEnrol);
                    System.out.println("delete enrolment successfully");
                }else{
                    System.out.println("enrolment don't exist");
                }
            }

        }
    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {

    }
}
