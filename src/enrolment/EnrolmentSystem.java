package enrolment;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EnrolmentSystem implements StudentEnrolmentManager {
    //create scanner and enrolmentList
     ArrayList<StudentEnrolment> enrolList;
     Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        //create scanner
        Scanner sc = new Scanner(System.in);
        EnrolmentSystem es = new EnrolmentSystem();


        String filePath = null;
        do{
            System.out.println("please choose the following file option to create the enrolment list: ");
            System.out.println("1.Your file: ");
            System.out.println("2.Default file: ");
            String option = sc.nextLine();
            //process the user input
            if(option.equals("1")){
                System.out.println("Enter your file location: ");
                filePath = sc.nextLine();
            }else if(option.equals("2")){
                filePath = "default.csv";
            }else{
                System.out.println("invalid input!");
                continue;
            }
            try {
                //read vcs file with bufferedReader
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line;
                while ((line= br.readLine()) != null) {
                    String[] str = line.split(",");
                    Student student = new Student(str[0],str[1],str[2]);
                    Course course = new Course(str[3],str[4],str[5]);
                    StudentEnrolment stuEnrolment = new StudentEnrolment(student,course,str[6]);
                    es.enrolList.add(stuEnrolment);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                filePath = null;
        }}while(filePath == null);
        while(true){
            System.out.println("Welcome to the enrolment system, what do you want to do? ");
            System.out.println("1.Add enrolment");
            System.out.println("2.Delete enrolment");
            System.out.println("3.Update enrolmentList");
            System.out.println("4.Get one enrolment");
            System.out.println("5.Get all enrolment");
            System.out.println("6.Print a report");
            System.out.println("7.exit program: ");
            String option;
            option = sc.nextLine();
            boolean optionCheck = false;
            do{
                if(option.equals("1")){
                    es.add();
                }else if(option.equals("2")){
                    es.delete();
                }else if(option.equals("3")){
                    es.update();
                }else if(option.equals("4")){
                    es.getOne();
                }else if(option.equals("5")){
                    es.getAll();
                }else if(option.equals("6")){
                    es.printReport();
                }else if(option.equals("7")){
                    System.exit(0);
                }
                else{
                    System.out.println("invalid input");
                    optionCheck = true;
                }
            }while(optionCheck);
            }

    }

    public EnrolmentSystem() {
        enrolList = new ArrayList<>();
    }

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
            }}
            //if there is no course ID in enrolList that match the input
                System.out.println("Course id not found");
                return null;


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
        //tell user to input semester
        System.out.println("Enter semester: ");
        String semester = sc.nextLine();
        //validate semester
        //These are the letter that should be included in the semester last character
        ArrayList<Character> checkList = new ArrayList<>(3);
        checkList.add('A');
        checkList.add('B');
        checkList.add('C');
        if(semester.length() !=5 || !(checkList.contains(semester.charAt(4)))){
            System.out.println("invalid semester");
        }
        //print all the course that the student are currently enrolled
        for(StudentEnrolment stuEnrol : enrolList ){
            if(StuID.equals(stuEnrol.student.getId()) && semester.equals(stuEnrol.semester)){
                System.out.println(stuEnrol.course.getId() + stuEnrol.course.getName() +  stuEnrol.course.getNumOfCredit());
            }
        }
        System.out.println("Do you want to add or delete the course?");
        System.out.println("1. Add");
        System.out.println("2. Delete");
        String userOption = sc.nextLine();
        if(userOption.equals("1")){
            add();
        }else if(userOption.equals("2")){
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
        //use to check if the enrolment is found in the list
        boolean enrolCheck = true;
        //check if the student, course and semester is exist or not and the semester is valid or not
        if (student == null || course == null || semester.length() !=5 || !(checkList.contains(semester.charAt(4)))) {
            System.out.println("in valid input"); }
        else{
            //if the student, course and semester information is valid then compare with the list data
            for(StudentEnrolment stuEnrol : enrolList){
                if(semester.equals(stuEnrol.semester) && studentID.equals(stuEnrol.student.getId()) && courseID.equals(stuEnrol.course.getId())){
                    enrolList.remove(stuEnrol);
                    System.out.println("delete enrolment successfully");
                    enrolCheck = false;
                 }
                }
            //if the enrolment has been found then the if wont be execute
            if (enrolCheck){
                System.out.println("enrolment don't exist");
            }
        }
    }

    @Override
    public void getOne() {
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
        //use to check if the enrolment is found in the list
        boolean enrolCheck = true;
        for(StudentEnrolment stuEnrol : enrolList){
            if(semester.equals(stuEnrol.semester) && studentID.equals(stuEnrol.student.getId()) && courseID.equals(stuEnrol.course.getId())){
                System.out.println(stuEnrol.toString());
                enrolCheck = false;
            }
        }
        //if the enrolment has been found then the if wont be execute
        if(enrolCheck){
            System.out.println("Enrolment not found");
        }
    }

    @Override
    public void getAll() {
        for(StudentEnrolment enrolSys : enrolList){
        System.out.println(enrolSys);}
    }

    public void printReport() {
        int optionCheck = 0;
        String option = null;
        List<String> reportList = new ArrayList<>();
        do {
            if (optionCheck != 0) System.out.println("Input 1,2 or 3!");
            System.out.println("Please choose the report option: ");
            System.out.println("1.Print all course for 1 student in one semester");
            System.out.println("2.Print all student of one course in one semester");
            System.out.println("3.Print all courses offered in 1 semester");
            option = sc.nextLine();
            optionCheck++;
        } while (!option.equals("1") && !option.equals("2") && !option.equals("3"));


        if (option.equals("1")) {

            do {
                System.out.println("Enter semester: ");
                String semester = sc.nextLine();
                System.out.println("Enter student ID: ");
                String studentID = sc.nextLine();
                for (StudentEnrolment studentEnrol : enrolList) {
                    if (semester.equals(studentEnrol.semester) && studentID.equals(studentEnrol.student.getId())) {
                        String course = studentEnrol.course.getId() + "," + studentEnrol.course.getName() + "," + studentEnrol.course.getNumOfCredit();
                        System.out.println(studentEnrol.course.toString());
                        reportList.add(course);
                    }
                }
                if (reportList.size() == 0)
                    System.out.println("invalid input or student don't have course in this semester");
            } while (reportList.size() == 0);


        } else if (option.equals("2")) {

            do {
                System.out.println("Enter course ID: ");
                String courseID = sc.nextLine();
                System.out.println("Enter semester: ");
                String semester = sc.nextLine();
                for (StudentEnrolment studentEnrol : enrolList) {
                    if (courseID.equals(studentEnrol.course.getId()) && semester.equals(studentEnrol.semester)) {
                        String student = studentEnrol.student.getId() + "," + studentEnrol.student.getName() + "," + studentEnrol.student.getBirthDate();
                        System.out.println(studentEnrol.student.toString());
                        reportList.add(student);
                    }
                }
                if (reportList.size() == 0) System.out.println("invalid input or course don't exist");

            } while (reportList.size() == 0);


        } else if (option.equals("3")) {

            do {
                System.out.println("Enter semester: ");
                String semester = sc.nextLine();
                for (StudentEnrolment studentEnrol : enrolList) {
                    if (semester.equals(studentEnrol.semester)) {
                        System.out.println(studentEnrol.course.toString());
                        String course = studentEnrol.course.getId() + "," + studentEnrol.course.getName() + "," + studentEnrol.course.getNumOfCredit();
                        reportList.add(course);
                    }
                }
                if (reportList.size() == 0) System.out.println("invalid input or semester don't exist");
            } while (reportList.size() == 0);
        }
        String fileOption;
        int fileOptionCheck = 0;
        do {
            if (fileOptionCheck != 0) System.out.println("invalid input");
            System.out.println("Do you want to save to csv file");
            System.out.println("1.Yes");
            System.out.println("2.No");
            fileOption = sc.nextLine();
            fileOptionCheck++;
        } while (!fileOption.equals("1") && !fileOption.equals("2"));
        if (fileOption.equals("1")) {
            System.out.println("enter your file name: ");
            String fileName = sc.nextLine();
            File reportFile = new File(fileName + ".csv");
            try {
                FileWriter reportFileWriter = new FileWriter(reportFile);
                for (String str : reportList) {
                    System.out.println(str);
                    reportFileWriter.write(str + "\n");
                }reportFileWriter.close();
            } catch (IOException e) {
                e.getMessage();
            }

        } else  {
            System.out.println("program finished");
        }
    }
}
