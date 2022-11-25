package org.example;

import java.util.*;

public class Main {
    private static final int taskNumbers = 3; //quantity of tasks
    private static final int selectNextTask = 4; //option for change task
    private static int currentTaskNumber = 1; //will begin by 1st task
    private static int currentAction = currentTaskNumber;

    public static void main(String[] args) {

        final Scanner in = new Scanner(System.in, getActualConsoleEncoding());

        while (true) {
            System.out.println(outputDescription(currentAction));
            String input = in.nextLine();
            String result = doAction(input);
            if (result != "") {
                System.out.println(result);
                currentAction = selectNextTask;
            }
        }
    }
    public static String doAction(String inputString){
        String outputMessage = "";
        switch (currentAction) {
            case 1:
                try {
                    int inputNumber = Integer.parseInt(inputString);
                    outputMessage = taskOne(inputNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели не число");
                }
                break;
            case 2:
                outputMessage = taskTwo(inputString);
                break;
            case 3:
                try {
                    int[] inputNumbers = Arrays.stream(inputString
                                    .trim()
                                    .split(" "))
                                    .filter(x -> !x.equals(""))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
                    List<Integer> outputNumbers = taskThree(inputNumbers);
                    if (outputNumbers.size() == 0){
                        outputMessage = "Числа, кратные 3 не найдены.";
                    } else {
                        outputMessage = "Числа, кратные 3: ";
                        for (int num : outputNumbers) {
                            outputMessage += num + " ";
                        }
                    }
                } catch (NumberFormatException e){
                    System.out.println("Вы ввели не массив чисел");
                }
                break;
            case 4:
                if (inputString.equals("+")){
                    currentTaskNumber++;
                    if (currentTaskNumber > taskNumbers){
                        currentTaskNumber = 1;
                    }
                    currentAction = currentTaskNumber;
                } else {
                    currentAction = currentTaskNumber;
                }
                break;
        }
        return outputMessage;
    }
    public static String taskOne(int i) {
        if (i > 7) {
            return "Привет";
        }
        return "";
    }

    public static String taskTwo(String s) {
        if (s.equals("Вячеслав")) {
            return "Привет, Вячеслав";
        }
        return "";
    }

    public static List<Integer> taskThree(int[] inputNums) {
        List<Integer> outputNums = new ArrayList<>();
        for (int num : inputNums) {
            if ((num % 3) == 0) {
                outputNums.add(num);
            }
        }
        return outputNums;
    }
    public static String outputDescription(int taskNumber){
        switch (taskNumber){
            case 1:
                return "Задание 1: введите число больше 7";
            case 2:
                return "Задание 2: введите нужное имя:";
            case 3:
                return "Задание 3: Введите массив целых чисел через пробел, в формате х1 х2 ... хn";
            case 4:
                return "Желаете перейти к следующему заданию: Введите '+' - что-бы перейти, любой символ - что-бы продолжить.";
        }
        return "";
    }

    public static String getActualConsoleEncoding(){
        String encoding = System.getProperty("sun.stderr.encoding");
        if (encoding == null){
            encoding = "UTF-8";
        }
        return encoding;
    }
}

