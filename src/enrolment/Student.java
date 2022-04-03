package enrolment;

public class Student {
    private String id;
    private String name;
    private String birthDate;
    //because the variable are private so we need getter to access them
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getBirthDate() {
        return birthDate;
    }

}
