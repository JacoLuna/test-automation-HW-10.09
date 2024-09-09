package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Calculate the number of unique words in the text (case-insensitive)
 * Calculate the number of letters in the text (without spaces)
 * Find the number of matches of words in the text (Searching for a word in the text, the program should not search for 1 letter, we should receive an error, the program should ask to enter the word)
 * Writing and reading a file using FileUtils
 * Use as many methods as possible from StringUtils to implement the requirements
 */
public class Main {
    public static void main(String[] args) {
        String path = "src\\text.txt";
        String[] content;
        Scanner keyboard = new Scanner(System.in);
        int ans;
        content = fileToArray(path);
        Set<String> list = collectionToSetList(List.of(content));

        do {
            System.out.println("-0 exit\n-1 file data\n-2 find word\n-3 number of unique words\n-4 print file\n-5 add word");
            try {
                ans = keyboard.nextInt();
                switch (ans){
                    case 1:
                        fileData(content);
                        break;
                    case 2:
                        findWord(content);
                        break;
                    case 3:
                        System.out.println("amount of words " + list.size());
                        break;
                    case 4:
                        printFile(path);
                        break;
                    case 5:
                        writeFile(path);
                        content = fileToArray(path);
                        list = collectionToSetList(List.of(content));
                        break;
                }
            }catch (InputMismatchException e){
                ans = -1;
                keyboard.nextLine();
                System.out.println("invalid input");
            }catch (Exception e){
                ans = -1;
                keyboard.nextLine();
                System.out.println(e.getMessage());
            }
        }while (ans != 0);
        System.out.println("Goodbye");
    }

    //items
    public static void findWord(String[] array){
        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> elements = new ArrayList<>();
        String ans;
        boolean valid = false;

        System.out.println("Type the word you are looking for");
        do {
            ans = keyboard.next();
            if (ans.length() > 1){
                valid = true;
                for (String value: array){
                    if (StringUtils.contains(value,ans)){
                        elements.add(value);
                    }
                }
                System.out.println("\"" + ans + "\" founded " + elements.size() + " times");
                for (String value: elements){
                    System.out.println(value);
                }
                System.out.println();
            }else {
                System.out.println("The word must be longer than 1 character");
                keyboard.nextLine();
            }
        }while (!valid);
    }
    public static void fileData(String[] list){
        int letters = 0;
        for (String value: list){
            letters += value.length();
        }
        System.out.println("amount of words " + list.length);
        System.out.println("amount of letters " + letters);
    }
    //Array conversion
    public static String[] fileToArray(String path){
        String data;
        String[] content;
        try {
            File file = FileUtils.getFile(path);
            data = FileUtils.readFileToString(file, Charset.defaultCharset());
        } catch(IOException e) {
            data = "";
            System.out.println(e.getMessage());
        }
        content = StringUtils.split(data, " ");
        return content;
    }
    public static Set<String> collectionToSetList(Collection<String> content){
        Set<String> list = new HashSet<>();
        for (String value: content){;
            list.add(StringUtils.toRootLowerCase(value));
        }
        return list;
    }
    //file functions
    public static void printFile(String path){
        String data;
        try {
            File file = FileUtils.getFile(path);
            data = FileUtils.readFileToString(file, Charset.defaultCharset());
        } catch(IOException e) {
            data = "";
            System.out.println(e.getMessage());
        }
        System.out.println(data);
    }
    public static void writeFile(String path){
        Scanner keyboard = new Scanner(System.in);
        String ans, data;
        boolean valid = false;
        File file = null;
        try {
            file = FileUtils.getFile(path);
            data = FileUtils.readFileToString(file, Charset.defaultCharset());
        } catch(IOException e) {
            data = "";
            System.out.println(e.getMessage());
        }
        System.out.println("write a word to add");
        do {
            ans = keyboard.next();
            if (ans.length() > 1){
                valid = true;
                data += " " + ans;
                try {
                    FileUtils.write(file,data,Charset.defaultCharset());
                    System.out.println("\"" + ans + "\" added");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }else {
                System.out.println("The word must be longer than 1 character");
                keyboard.nextLine();
            }
        }while (!valid);
    }
}