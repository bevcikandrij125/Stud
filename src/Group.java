import java.util.Iterator;
import java.util.TreeSet;

class Group extends IntroduceMyself {
    private String name;
    private String specialty;
    private Teacher curator;
    private TreeSet<Student> students = new TreeSet();

    public Group(String name, String specialty, Teacher curator, TreeSet<Student> students) {
        this.name = name;
        this.specialty = specialty;
        this.curator = curator;
        this.students = students;
    }

    public Group(String name, String specialty, Teacher curator) {
        this.name = name;
        this.specialty = specialty;
        this.curator = curator;
    }

    public void addStudent(Student somebody) {
        this.students.add(somebody);
    }

    public TreeSet<Student> getStudents() {
        return new TreeSet(this.students);
    }

    public String getRating() {
        String str = "";

        Student s;
        for(Iterator<Student> iterator = this.students.iterator(); iterator.hasNext(); str = str + s.getLastName() + " " + s.getFirstName() + " - " + s.getAverageScore() + ".\n") {
            s = (Student)iterator.next();
        }

        return str;
    }

    public void refreshRating() {
        TreeSet<Student> Buf = new TreeSet(this.students);
        Iterator var2 = this.students.iterator();

        Student s;
        while(var2.hasNext()) {
            s = (Student)var2.next();
            this.removeStudent(s);
        }

        var2 = Buf.iterator();

        while(var2.hasNext()) {
            s = (Student)var2.next();
            this.addStudent(s);
        }

    }

    public void removeStudent(Student somebody) {
        this.students.remove(somebody);
    }

    public void setStudents(TreeSet<Student> students) {
        this.students = new TreeSet(students);
    }

    public String getName() {
        return this.name;
    }

    public String getSpecialty() {
        return this.specialty;
    }

    public Teacher getCurator() {
        return this.curator;
    }

   /* public String toCSV() {
        String std = "";
        for(Student s: students){
            std += s.hashCode() + "-";
        }
        return name + "," + specialty + "," + curator.hashCode() + "," + std;
    }*/
   public String toCSV() {
       return name + "," + specialty + "," + curator + "," + students;
   }
}