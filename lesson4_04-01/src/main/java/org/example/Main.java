package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    private static final String MENU = "Выберите действие:\n1. Перевести текст с русского на английский\n2. Перевести текст с английского на русский\n3. Записать текст в файл\n4. Перевести содержимое файла\n5. Выход";
    public static int Choose;
    public static boolean exit = true;

    public static void main(String[] args) throws Exception {
        boolean isNumeric = true;
        Scanner in = new Scanner(System.in);

        while (exit) {
            System.out.println(MENU);
            try {
                Choose = parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено некорректное значение\nПожалуйста повторите еще раз!\n");
                isNumeric = false;
            }

            switch (Choose) {
                case 1:
                    System.out.println("Введите текст для перевода с русского на английский:");
                    String Rutext = in.nextLine();
                    System.out.println("\nПеревод вашего текста:");
                    System.out.println(Translator.TranslatorToEng(Rutext) + "\n");
                    break;
                case 2:
                    System.out.println("Введите текст для перевода с английского на русский:");
                    String Entext = in.nextLine();
                    System.out.println("\nПеревод вашего текста:");
                    System.out.println(Translator.TranslateToRU(Entext) + "\n");
                    break;
                case 3:
                    System.out.println("Введите текст для записи в файл:");
                    String textToWrite = in.nextLine();
                    writeToFile("text.txt", textToWrite);
                    System.out.println("Текст записан в файл text.txt\n");
                    break;
                case 4:
                    System.out.println("Введите имя файла для чтения (например, text.txt):");
                    String fileName = in.nextLine();
                    String fileContent = Translator.readFromFile(fileName);
                    if (!fileContent.isEmpty()) {
                        System.out.println("\nСодержимое файла:\n" + fileContent);
                        System.out.println("\nПеревод содержимого файла на русский:");
                        System.out.println(Translator.TranslateToRU(fileContent) + "\n");
                        System.out.println("\nПеревод содержимого файла на английский:");
                        System.out.println(Translator.TranslatorToEng(fileContent) + "\n");
                    } else {
                        System.out.println("Файл пуст или не найден.");
                    }
                    break;
                case 5:
                    exit = false;
                    System.out.println("Программа завершена.");
                    break;
                default:
                    if ((Choose > 5 || Choose < 1) && isNumeric)
                        System.out.println("Введен текст\nПожалуйста введите порядковый номер!");
                    break;
            }
            isNumeric = true;
            Choose = 0;
        }
    }

    private static void writeToFile(String fileName, String text) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(text);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
