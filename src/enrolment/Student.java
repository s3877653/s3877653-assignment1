package enrolment;

public class Student {
    private String id;
    private String name;
    private String birthDate;

    public Student(String id, String name, String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

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

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
