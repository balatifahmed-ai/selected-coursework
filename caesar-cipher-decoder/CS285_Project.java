package cs285_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/*
    CS285 Project | Semester: 242 | Class: 932
    Ahmed Balatif | Ziyad Al Ali
    This program decrypts any file encrypted with Caesar Cipher using a basic English word frequency check.
*/

public class CS285_Project {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789,./?!";

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the full file path (no quotation marks): ");
            String path = in.nextLine();

            File inputFile = new File(path);
            Scanner input = new Scanner(inputFile);

            System.out.println("\n1- Decrypt a file\n2- Display a decrypted message");
            int menu = in.nextInt();

            if (menu == 2) {
                while (input.hasNextLine()) 
                    System.out.println(input.nextLine());
                }
            

            else if (menu == 1) {
                int fileNumber = 1;
                File outputDirectory = inputFile.getAbsoluteFile().getParentFile();
                File outputFile = new File(outputDirectory, "decrypted-file-" + fileNumber + ".txt");

                while (outputFile.exists()) {
                    fileNumber++;
                    outputFile = new File(outputDirectory, "decrypted-file-" + fileNumber + ".txt");
                }

                PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    if (line.toLowerCase().contains("alphabet")) continue;

                    String decryptedOutput = decryptCaesar(line);
                    if (!decryptedOutput.isEmpty()) {
                        writer.println(decryptedOutput);
                        writer.println();
                        System.out.println(decryptedOutput + "\n");
                    }
                }

                writer.close();
                System.out.println("Decryption complete. File saved alongside input as: " + outputFile.getName());
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static String decryptCaesar(String text) {
        int key = findCorrectKey(text);
        if (key == -1) {
            return "";
        }

        String decrypted = shiftText(text, key);
        return decrypted + "\nKey: " + key;
    }

    public static String shiftText(String text, int key) {

        StringBuilder result = new StringBuilder();

        for (char c : text.toLowerCase().toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                int newIndex = (index - key + ALPHABET.length()) % ALPHABET.length();
                result.append(ALPHABET.charAt(newIndex));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static int findCorrectKey(String text) {
        for (int i = 0; i < ALPHABET.length(); i++) {
            String decrypted = shiftText(text, i);
            if (isEnglish(decrypted)) {
                return i;
            }
        }
        // No valid key found
        return -1;
    }

    public static boolean isEnglish(String text) {
        String[] commonWords = {
            "the", "be", "to", "of", "and", "in", "that", "have", "am", "is", "you", "it",
            "we", "he", "she", "they", "not", "do", "say", "this", "but", "his", "my",
            "your", "our", "their", "me", "us", "who"
        };

        int count = 0;
        String[] words = text.toLowerCase().split("\\W+");

        for (String word : words) {
            for (String common : commonWords) {
                if (word.equals(common)) {
                    count++;
                }
            }
        }

        return count >= 5;
    }
}
