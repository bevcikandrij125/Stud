import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Student extends Person implements Comparable<Student> {
    private HashMap<Subject, Integer> Marks = new HashMap();

    public Student(String firstname, String lastname, String address, Date birthday) {
        super(firstname, lastname, address, birthday);
    }

    public Student(String firstname, String lastname, String address, String birthday) {
        super(firstname, lastname, address, birthday);
    }

    public HashMap<String, Integer> getMarks() {
        HashMap<String, Integer> map = new HashMap();
        Iterator var2 = this.Marks.keySet().iterator();

        while(var2.hasNext()) {
            Subject key = (Subject)var2.next();
            map.put(key.getDiscipline_name(), (Integer)this.Marks.get(key));
        }

        return map;
    }

    public Integer getMark(String subject) {
        Iterator var2 = this.Marks.keySet().iterator();

        Subject key;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            key = (Subject)var2.next();
        } while(key.getDiscipline_name() != subject);

        return (Integer)this.Marks.get(key);
    }

    public Integer getMark(Subject subject) {
        return (Integer)this.Marks.get(subject);
    }

    public void addMark(Subject s, int mark) {
        this.Marks.put(s, mark);
    }

    public float getAverageScore() {
        int sum = 0;

        int value;
        for(Iterator<Map.Entry<Subject, Integer>> iterator = this.Marks.entrySet().iterator(); iterator.hasNext(); sum += value) {
            Map.Entry<Subject, Integer> entry = (Map.Entry)iterator.next();
            value = (Integer)entry.getValue();
        }

        return (float)(sum / this.Marks.size());
    }

    public int compareTo(Student s) {
        return (int)(this.getAverageScore() - s.getAverageScore()) * 100;
    }

    public String toCSV() {
        return firstname + "," + lastname + "," + address + "," + birthday;
    }
}