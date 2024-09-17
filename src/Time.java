import java.util.Objects;
import java.util.Scanner;

public class Time {
    private short hour;
    private short minute;
    private short second;

    // Конструктор за замовчуванням (00:00:00)
    public Time() {
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    // Конструктор з числами (година, хвилина, секунда)
    public Time(short hour, short minute, short second) {
        this.hour = (short) (hour % 24);
        this.minute = (short) (minute % 60);
        this.second = (short) (second % 60);
    }

    // Конструктор з рядком "hh:mm:ss"
    public Time(String timeStr) {
        String[] parts = timeStr.split(":");
        this.hour = (short) (Integer.parseInt(parts[0]) % 24);
        this.minute = (short) (Integer.parseInt(parts[1]) % 60);
        this.second = (short) (Integer.parseInt(parts[2]) % 60);
    }

    // Конструктор за кількістю секунд від 00:00:00
    public Time(int totalSeconds) {
        this.hour = (short) ((totalSeconds / 3600) % 24);
        this.minute = (short) ((totalSeconds % 3600) / 60);
        this.second = (short) (totalSeconds % 60);
    }

    // Метод для введення часу з консолі
    public static Time inputTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть час у форматі hh:mm:ss: ");
        String input = scanner.nextLine();
        return new Time(input);
    }

    // Метод для переведення часу в кількість секунд
    public int toSeconds() {
        return hour * 3600 + minute * 60 + second;
    }

    // Метод для переведення часу в кількість хвилин (з округленням)
    public int toMinutes() {
        return (int) Math.round((double) toSeconds() / 60.0);
    }

    // Метод для обчислення різниці між двома моментами часу (в секундах)
    public int differenceInSeconds(Time other) {
        return Math.abs(this.toSeconds() - other.toSeconds());
    }

    // Додавання до часу заданої кількості секунд
    public void addSeconds(int seconds) {
        int totalSeconds = this.toSeconds() + seconds;
        Time newTime = new Time(totalSeconds);
        this.hour = newTime.hour;
        this.minute = newTime.minute;
        this.second = newTime.second;
    }

    // Віднімання від часу заданої кількості секунд
    public void subtractSeconds(int seconds) {
        int totalSeconds = this.toSeconds() - seconds;
        if (totalSeconds < 0) totalSeconds += 24 * 3600;  // Запобігання негативному часу
        Time newTime = new Time(totalSeconds);
        this.hour = newTime.hour;
        this.minute = newTime.minute;
        this.second = newTime.second;
    }

    // Оператори порівняння
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return toSeconds() == time.toSeconds();
    }

    @Override
    public int hashCode() {
        return Objects.hash(toSeconds());
    }

    public boolean isGreaterThan(Time other) {
        return this.toSeconds() > other.toSeconds();
    }

    public boolean isLessThan(Time other) {
        return this.toSeconds() < other.toSeconds();
    }

    // Перевантаження toString для виведення часу у форматі "hh:mm:ss"
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public static void main(String[] args) {
        // Приклад введення часу користувачем
        Time t1 = Time.inputTime(); // Користувач вводить час
        Time t2 = Time.inputTime(); // Користувач вводить другий час

        System.out.println("Час 1: " + t1);
        System.out.println("Час 2: " + t2);

        System.out.println("Різниця між t1 і t2 в секундах: " + t1.differenceInSeconds(t2) + " секунд");

        t1.addSeconds(3600); // Додати годину
        System.out.println("Час 1 після додавання 3600 секунд: " + t1);

        t2.subtractSeconds(4500); // Відняти 4500 секунд
        System.out.println("Час 2 після віднімання 4500 секунд: " + t2);

        System.out.println("Час 1 в хвилинах (округлено): " + t1.toMinutes());
        System.out.println("Час 2 в секундах: " + t2.toSeconds());

        if (t1.isGreaterThan(t2)) {
            System.out.println("Час 1 більший за час 2");
        } else {
            System.out.println("Час 1 не більший за час 2");
        }
    }
}
