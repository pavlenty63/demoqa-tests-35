package tests.lesson_10;

import static com.codeborne.selenide.DragAndDropOptions.to;

public class SimpleOperations {

  public static void main(String[] args) {

    int firstInt = 1, secondInt = 2;
    double firstDouble = 1.1, secondDouble = 2.2, infinityDouble = Double.MAX_VALUE;

    System.out.println("\nАрифметические операции с целыми числами: (1 * 2) / 2 - 1 = " + (firstInt * secondInt / (secondInt - firstInt)));
    System.out.println("\nАрифметические операции с вещественными числами: 1 + (2.2 / 1.1) * 2 = " + (firstInt + (secondDouble / firstDouble) * secondInt));

    System.out.println("\nЛогические операции:\n1 < 1.1 = " + (firstInt < firstDouble));
    System.out.println("2.2 > 1.1 = " + (secondDouble > firstDouble));
    System.out.println("2 >= 2 = " + (secondInt >= secondInt));
    System.out.println("2 <= 2.2 = " + (secondInt <= secondDouble));

    System.out.println("\nДиапазон float (32 бит): от " + -Float.MAX_VALUE + " до " + Float.MAX_VALUE);
    System.out.println("Минимальное положительное значение float: " + Float.MIN_VALUE);

    System.out.println("\nДиапазон double (64 бит): от " + -Double.MAX_VALUE + " до " + Double.MAX_VALUE);
    System.out.println("Минимальное положительное значение double: " + Double.MIN_VALUE);

    System.out.println("\nПереполнение double: " + infinityDouble * 2);
  }
}