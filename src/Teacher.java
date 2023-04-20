import java.util.Date;
import java.util.HashSet;

class Teacher extends Person {
    HashSet<Discipline> Specialties = new HashSet();
    protected Integer experience;
    protected String education;
    protected String degree;

    public Teacher(String firstname, String lastname, String address, Date birthday, String education, String degree) {
        super(firstname, lastname, address, birthday);
        this.education = education;
        this.degree = degree;
    }

    public Teacher(String firstname, String lastname, String address, String birthday, String education, String degree) {
        super(firstname, lastname, address, birthday);
        this.education = education;
        this.degree = degree;
    }

    public boolean disciplineAvailable(Discipline d) {
        return this.Specialties.contains(d);
    }

    public void addDiscipline(Discipline d) {
        this.Specialties.add(d);
    }

    public String getEducation() {
        return this.education;
    }

    public String getDegree() {
        return this.degree;
    }

    public HashSet<Discipline> getDisciplines() {
        return new HashSet(this.Specialties);
    }

    public String toCSV() {
        return firstname + "," + lastname + "," + address + "," + birthday + "," + education + "," + degree;
    }
}