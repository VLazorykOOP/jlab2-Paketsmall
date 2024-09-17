import java.util.Scanner;

public class Vector3D {
    private double x;
    private double y;
    private double z;

    // Конструктор за замовчуванням (вектор (0, 0, 0))
    public Vector3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    // Конструктор з параметрами (задає вектор через координати)
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Метод для обчислення модуля (довжини) вектора
    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    // Метод для множення вектора на скаляр (число)
    public Vector3D multiplyByScalar(double scalar) {
        return new Vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    // Метод для додавання двох векторів
    public Vector3D add(Vector3D other) {
        return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    // Метод для скалярного добутку двох векторів
    public double dotProduct(Vector3D other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    // Метод для векторного добутку двох векторів
    public Vector3D crossProduct(Vector3D other) {
        double crossX = this.y * other.z - this.z * other.y;
        double crossY = this.z * other.x - this.x * other.z;
        double crossZ = this.x * other.y - this.y * other.x;
        return new Vector3D(crossX, crossY, crossZ);
    }

    // Метод для виведення координат вектора
    @Override
    public String toString() {
        return String.format("(%f, %f, %f)", x, y, z);
    }

    // Метод для введення вектора від користувача
    public static Vector3D inputVector() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть координату x: ");
        double x = scanner.nextDouble();

        System.out.print("Введіть координату y: ");
        double y = scanner.nextDouble();

        System.out.print("Введіть координату z: ");
        double z = scanner.nextDouble();

        return new Vector3D(x, y, z);
    }

    // Основна програма для тестування
    public static void main(String[] args) {
        // Введення вектора 1 від користувача
        System.out.println("Введіть координати для вектора 1:");
        Vector3D v1 = Vector3D.inputVector();

        // Введення вектора 2 від користувача
        System.out.println("Введіть координати для вектора 2:");
        Vector3D v2 = Vector3D.inputVector();

        // Друкуємо вектори
        System.out.println("Вектор 1: " + v1);
        System.out.println("Вектор 2: " + v2);

        // Обчислюємо модуль вектора 1
        System.out.println("Модуль вектора 1: " + v1.magnitude());

        // Множимо вектор 1 на скаляр
        Vector3D v1Multiplied = v1.multiplyByScalar(2);
        System.out.println("Вектор 1 після множення на 2: " + v1Multiplied);

        // Додаємо вектор 1 і вектор 2
        Vector3D sum = v1.add(v2);
        System.out.println("Сума вектора 1 і вектора 2: " + sum);

        // Обчислюємо скалярний добуток вектора 1 і вектора 2
        double dot = v1.dotProduct(v2);
        System.out.println("Скалярний добуток вектора 1 і вектора 2: " + dot);

        // Обчислюємо векторний добуток вектора 1 і вектора 2
        Vector3D cross = v1.crossProduct(v2);
        System.out.println("Векторний добуток вектора 1 і вектора 2: " + cross);
    }
}
