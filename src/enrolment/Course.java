package enrolment;

public class Course {
    private String id;
    private String name;
    private String numOfCredit;

    public Course(String id, String name, String numOfCredit) {
        this.id = id;
        this.name = name;
        this.numOfCredit = numOfCredit;
    }

    //because the variable are private so we need getter to access them
    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getNumOfCredit() {
        return numOfCredit;
    }



}
