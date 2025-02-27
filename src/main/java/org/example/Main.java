package org.example;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить пользователя");
            System.out.println("2. Показать пользователя");
            System.out.println("3. Показать всех пользователей");
            System.out.println("4. Изменить баланс пользователя");
            System.out.println("5. Удалить пользователя");
            System.out.println("6. Выйти");
            System.out.print("Введите номер действия: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Введите номер телефона: ");
                        String phone = scanner.nextLine();
                        System.out.print("Введите баланс: ");
                        BigDecimal balance = scanner.nextBigDecimal();
                        System.out.print("Введите код входа: ");
                        scanner.nextLine();
                        String enterCode = scanner.nextLine();
                        System.out.print("Введите время истечения кода: ");
                        int enterCodeExpiredAt = scanner.nextInt();
                        userService.addUser(phone, balance, enterCode, enterCodeExpiredAt);
                        break;
                    case 2:
                        System.out.print("Введите ID пользователя: ");
                        Long id = scanner.nextLong();
                        userService.getUser(id);
                        break;
                    case 3:
                        userService.listUsers();
                        break;
                    case 4:
                        System.out.print("Введите ID пользователя: ");
                        Long updateId = scanner.nextLong();
                        System.out.print("Введите новый баланс: ");
                        BigDecimal newBalance = scanner.nextBigDecimal();
                        userService.updateUserBalance(updateId, newBalance);
                        break;
                    case 5:
                        System.out.print("Введите ID пользователя: ");
                        Long deleteId = scanner.nextLong();
                        userService.deleteUser(deleteId);
                        break;
                    case 6:
                        System.out.println("Выход...");
                        return;
                    default:
                        System.out.println("Неверный выбор!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
