import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;


public class Main {
    public static void main(String[] args) throws Exception {
        // Створив список предметів
        List<Subject> subjects = new LinkedList<Subject>();
        subjects.add(new Subject("Math", 2, 2, 1, 1, new Teacher("John", "Doe", "123 Main St", new Date(95, 3, 12), "Mathematics", "PhD"), TypeSummativeAssessment.CREDIT));
        subjects.add(new Subject("Physics", 2, 2, 1, 1, new Teacher("Alice", "Johnson", "456 First St", new Date(92, 8, 23), "Physics", "PhD"), TypeSummativeAssessment.EXAM));
        subjects.add(new Subject("Computer Science", 2, 2, 1, 1, new Teacher("Bob", "Smith", "789 Third St", new Date(87, 6, 30), "Computer Science", "PhD"), TypeSummativeAssessment.CREDIT));
        subjects.add(new Subject("Chemistry", 2, 2, 1, 1, new Teacher("Emma", "Brown", "321 Fourth St", new Date(90, 11, 1), "Chemistry", "PhD"), TypeSummativeAssessment.EXAM));
        subjects.add(new Subject("Biology", 2, 2, 1, 1, new Teacher("David", "Lee", "654 Fifth St", new Date(93, 4, 18), "Biology", "PhD"), TypeSummativeAssessment.CREDIT));


        // Створив 3 групи
        Group[] groups = new Group[3];
        Random rand = new Random();
        for (int i = 0; i < groups.length; i++) {
            groups[i] = new Group("Group " + (i + 1), "Specialty " + (i + 1), new Teacher("John", "Doe", "123 Main St", new Date(95, 3, 12), "Mathematics", "PhD"));
            for (int j = 0; j < 10; j++) {
                Student student = new Student(RandomUtils.getRandomFirstName(), RandomUtils.getRandomLastName(), RandomUtils.getRandomAddress(), RandomUtils.getRandomDateOfBirth());
                int base = rand.nextInt(30,50);
                student.addMark(subjects.get(0), base + rand.nextInt(50));
                student.addMark(subjects.get(1), base + rand.nextInt(50));
                student.addMark(subjects.get(2), base + rand.nextInt(50));
                student.addMark(subjects.get(3), base + rand.nextInt(50));
                student.addMark(subjects.get(4), base + rand.nextInt(50));
                groups[i].addStudent(student);
            }
        }
        // Сортування предметів
        Comparator<Subject> Discipline_name = new Comparator<Subject>() {
            @Override
            public int compare(Subject o1, Subject o2) {
                char[] s1 = o1.getDiscipline_name().toCharArray(), s2 = o2.getDiscipline_name().toCharArray();
                for(int i = 0; i < s1.length;i++){
                    if(s1[i] != s2[i]){
                        return (byte)s1[i] - (byte)s2[i];
                    }
                }
                return 0;
            }
        };
        Comparator<Subject> techer_name = new Comparator<Subject>() {
            @Override
            public int compare(Subject o1, Subject o2) {
                Teacher t1 = o1.lecturer, t2 = o2.lecturer;
                char[] s1 = (t1.getLastName() + t1.getFirstName()).toCharArray(), s2 = (t2.getLastName() + t2.getFirstName()).toCharArray();
                for(int i = 0; i < s1.length;i++){
                    if(s1[i] != s2[i]){
                        return (byte)s1[i] - (byte)s2[i];
                    }
                }
                return 0;
            }
        };





        // демонстрація
        System.out.println("Список предметів:\n");
        for(Subject s:subjects){
            System.out.println(s.getDiscipline_name()+ "\n");
        }
        Collections.sort(subjects,Discipline_name);
        System.out.println("Список предметів відсортовано по назві:\n");
        for(Subject s:subjects){
            System.out.println(s.getDiscipline_name()+ "\n");
        }
        Collections.sort(subjects,Discipline_name);
        System.out.println("Список предметів відсортовано по ПІБ викладача:\n");
        for(Subject s:subjects){
            System.out.println(s.getDiscipline_name());
            System.out.println(s.getLecturer().getFirstName() + " " + s.getLecturer().getLastName() + "\n");
        }
        CRUD<Group> wr = new CRUD<>("students.txt");
        for(Group student: groups){
            wr.add(student);
        }
        wr.load();

        System.out.println("Рейтингові списки");
        for(int i = 0; i < groups.length; i++){
            System.out.println("Рейтин "+ groups[i].getName());
            System.out.println(groups[i].getRating());
            System.out.println("");
        }
    }
}


