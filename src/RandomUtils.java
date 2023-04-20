import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class RandomUtils {
    private static final Random rand = new Random();
    private static final String[] FIRST_NAMES = new String[]{"Liam", "Emma", "Noah", "Olivia", "William", "Ava", "James", "Isabella", "Logan", "Sophia", "Benjamin", "Mia", "Mason", "Charlotte", "Ethan", "Amelia", "Oliver", "Harper", "Jacob", "Evelyn"};
    private static final String[] LAST_NAMES = new String[]{"Smith", "Johnson", "Williams", "Jones", "Brown", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Perez", "Taylor", "Anderson", "Wilson", "Jackson", "Wright", "Walker"};
    private static final String[] ADDRESSES = new String[]{"123 Main St", "456 Elm St", "789 Oak Ave", "321 Maple St", "654 Pine Ave", "987 Cedar Ln", "555 Birch Blvd", "777 Ash St", "888 Cedar St", "999 Oak St"};

    RandomUtils() {
    }

    public static String getRandomFirstName() {
        return FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
    }

    public static String getRandomLastName() {
        return LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
    }

    public static String getRandomAddress() {
        return ADDRESSES[rand.nextInt(ADDRESSES.length)];
    }

    public static Date getRandomDateOfBirth() {
        long minDay = LocalDate.of(1900, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().minusYears(18L).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return java.sql.Date.valueOf(randomDate);
    }
}