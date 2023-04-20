import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

abstract class Person extends IntroduceMyself {
    protected String firstname;
    protected String lastname;
    protected String address;
    protected Date birthday;

    public Person(String firstname, String lastname, String address, Date birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.birthday = birthday;
    }

    public Person(String firstname, String lastname, String address, String birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            this.birthday = dateFormat.parse(birthday);
        } catch (ParseException var7) {
            throw new RuntimeException(var7);
        }
    }

    public String getFirstName() {
        return this.firstname;
    }

    public String getLastName() {
        return this.lastname;
    }

    public String getAddress() {
        return this.address;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public boolean IsItMe(String firstname, String lastname, String address, Date birthday) {
        return this.firstname.contentEquals(firstname) && this.lastname.contentEquals(lastname) && this.address.contentEquals(address) && this.birthday == birthday;
    }

    public boolean IsItMe(String firstname, String lastname, String address, String birthday) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(birthday);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return this.IsItMe(firstname, lastname, address, date);
        } catch (ParseException var8) {
            System.out.println("Невірний формат дати: " + var8.getMessage());
            return false;
        }
    }
}
