import java.util.Scanner;

public class BinarySearchTree {

    // Внутрішній клас, що описує вузол дерева
    class Node {
        int value;
        Node left, right;

        public Node(int item) {
            value = item;
            left = right = null;
        }
    }

    // Корінь дерева
    Node root;

    // Конструктор для ініціалізації порожнього дерева
    public BinarySearchTree() {
        root = null;
    }

    // Метод для додавання елемента у дерево
    public void insert(int value) {
        root = insertRec(root, value);
    }

    // Рекурсивний метод для вставки нового елемента
    private Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    // Метод для пошуку елемента
    public boolean search(int value) {
        return searchRec(root, value);
    }

    // Рекурсивний метод для пошуку елемента
    private boolean searchRec(Node root, int value) {
        if (root == null) {
            return false;
        }
        if (root.value == value) {
            return true;
        }
        return value < root.value ? searchRec(root.left, value) : searchRec(root.right, value);
    }

    // Метод для видалення елемента з дерева
    public void delete(int value) {
        root = deleteRec(root, value);
    }

    // Рекурсивний метод для видалення елемента
    private Node deleteRec(Node root, int value) {
        if (root == null) {
            return root;
        }

        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.value = minValue(root.right);
            root.right = deleteRec(root.right, root.value);
        }

        return root;
    }

    // Метод для знаходження мінімального значення у дереві
    private int minValue(Node root) {
        int minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }
        return minv;
    }

    // Метод для обхідного виведення дерева (in-order traversal)
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    // Рекурсивний метод для in-order traversal
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.value + " ");
            inorderRec(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВиберіть дію:");
            System.out.println("1. Додати елемент");
            System.out.println("2. Видалити елемент");
            System.out.println("3. Шукати елемент");
            System.out.println("4. Вивести дерево (in-order)");
            System.out.println("5. Вийти");
            System.out.print("Ваш вибір: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введіть значення для додавання: ");
                    int addValue = scanner.nextInt();
                    tree.insert(addValue);
                    System.out.println("Елемент " + addValue + " додано у дерево.");
                    break;
                case 2:
                    System.out.print("Введіть значення для видалення: ");
                    int deleteValue = scanner.nextInt();
                    tree.delete(deleteValue);
                    System.out.println("Елемент " + deleteValue + " видалено з дерева.");
                    break;
                case 3:
                    System.out.print("Введіть значення для пошуку: ");
                    int searchValue = scanner.nextInt();
                    boolean found = tree.search(searchValue);
                    if (found) {
                        System.out.println("Елемент " + searchValue + " знайдено у дереві.");
                    } else {
                        System.out.println("Елемент " + searchValue + " не знайдено.");
                    }
                    break;
                case 4:
                    System.out.println("In-order обхід дерева:");
                    tree.inorder();
                    break;
                case 5:
                    System.out.println("Вихід.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неправильний вибір, спробуйте ще раз.");
            }
        }
    }
}
