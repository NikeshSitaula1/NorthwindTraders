package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Console {
    // Static scanner for input
    static Scanner scanner = new Scanner(System.in);

    // Method to prompt a String input
    public static String PromptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Method to prompt a String input without asking prompt
    public static String PromptForString(){
        return scanner.nextLine().trim();
    }

    //Method to prompt a Boolean input
    public static boolean PromptForYesNo(String prompt){
        System.out.print(prompt + "( Y for Yes, N for No ): ");
        String boolInput = scanner.nextLine().trim();

        return
                (
                        boolInput.equalsIgnoreCase("Y")
                                ||
                                boolInput.equalsIgnoreCase("YES")
                );

    }

    //Method to prompt a Short input
    public static short PromptForShort(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        short shortInput = Short.parseShort(value);
        return shortInput;
    }

    //Method to prompt an Integer input
    public static int PromptForInt(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        int intInput = Integer.parseInt(value);
        return intInput;
    }

    //Method to prompt an Integer input without asking prompt
    public static int PromptForInt(){
        String value = scanner.nextLine();
        int intInput = Integer.parseInt(value);
        return intInput;
    }

    //Method to prompt a Double input
    public static double PromptForDouble(String prompt){
        System.out.print(prompt);
        String userInputs = scanner.nextLine();
        double doubleInput = Double.parseDouble(userInputs);
        return doubleInput;
    }

    //Method to prompt a Byte input
    public static byte PromptForByte(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        byte byteInput = Byte.parseByte(value);
        return byteInput;
    }

    //Method to prompt a Byte input without asking prompt
    public static byte PromptForByte(){
        String value = scanner.nextLine();
        byte byteInput = Byte.parseByte(value);
        return byteInput;
    }

    //Method to prompt a Float input
    public static float PromptForFloat(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        float floatInput =Float.parseFloat(value);
        return floatInput;
    }

    //Method to prompt to input LocalDate
    public static LocalDate PromptForDate(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine();
        LocalDate dateInput = LocalDate.parse(value);
        return dateInput;
    }

    //Method to prompt to input LocalTime
    public static LocalTime PromptForTime(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine();
        LocalTime timeInput = LocalTime.parse(value);
        return timeInput;
    }

}
