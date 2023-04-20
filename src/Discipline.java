import java.util.HashSet;

class Discipline extends IntroduceMyself {
    protected static HashSet<Discipline> allDisciplines = new HashSet();
    protected String Discipline_name = "";
    protected int lecture_hours = 0;
    protected int practical_hours = 0;
    protected int laboratory_hours = 0;

    public Discipline(String Discipline_name, int lecture_hours, int practical_hours, int laboratory_hours) {
        this.Discipline_name = Discipline_name;
        this.lecture_hours = lecture_hours;
        this.practical_hours = practical_hours;
        this.laboratory_hours = laboratory_hours;
        allDisciplines.add(this);
    }

    public static boolean disciplineExists(Discipline some) {
        return allDisciplines.contains(some);
    }

    public String toCSV() {
        return Discipline_name + "," + lecture_hours + "," + practical_hours + "," + laboratory_hours;
    }


    public String getDiscipline_name() {
        return this.Discipline_name;
    }

    public int getLecture_hours() {
        return this.lecture_hours;
    }

    public int getPractical_hours() {
        return this.practical_hours;
    }

    public int getLaboratory_hours() {
        return this.laboratory_hours;
    }
}