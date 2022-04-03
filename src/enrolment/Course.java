package enrolment;

public class Course {
    private String id;
    private String name;
    private int numOfCredit;
    //because the variable are private so we need getter to access them
    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public int getNumOfCredit() {
        return numOfCredit;
    }



}
