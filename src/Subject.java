//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class Subject extends Discipline {
    protected TypeSummativeAssessment t;
    protected byte semester;
    protected Teacher lecturer;

    public Subject(String Discipline_name, int lecture_hours, int practical_hours, int laboratory_hours, int semester, Teacher lecturer, TypeSummativeAssessment t) throws Exception {
        super(Discipline_name, lecture_hours, practical_hours, laboratory_hours);
        if (semester > 0) {
            this.semester = (byte)semester;
            this.lecturer = lecturer;
            this.t = t;
        } else {
            throw new Exception("The semester number can be only positive");
        }
    }

    public Subject(Discipline template, int semester, Teacher lecturer, TypeSummativeAssessment t) throws Exception {
        super(template.Discipline_name, template.lecture_hours, template.practical_hours, template.laboratory_hours);
        if (semester > 0) {
            this.semester = (byte)semester;
            this.lecturer = lecturer;
            this.t = t;
        } else {
            throw new Exception("The semester number can be only positive");
        }
    }

    public int getSemester() {
        return this.semester;
    }

    public Teacher getLecturer() {
        return this.lecturer;
    }

    public String getTypeSummativeAssessmentAsString() {
        return this.t == TypeSummativeAssessment.EXAM ? "Exam" : "Credit";
    }

    public TypeSummativeAssessment getTypeSummativeAssessment() {
        return this.t;
    }
    public String toCSV() {
        return Discipline_name + "," + lecture_hours + "," + practical_hours + "," + laboratory_hours + "," + semester + "," + lecturer.hashCode() + "," + t;
    }
}
