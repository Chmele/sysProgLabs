package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter file path:");
        Scanner input = new Scanner(System.in);
        String filePath = input.nextLine();
        try {
            FileReader fr = new FileReader(filePath);
            int i;
            StringBuilder sb = new StringBuilder();
            while ((i=fr.read()) != -1)
                sb.append((char) i);
            String text = sb.toString();
            String[] words = text.split(" |\\r?\\n|\\t");

            slice_words(words);

            filter_words(words);
        }
        catch (FileNotFoundException e){
            System.out.println("No such file");
        }
    }

    public static  void slice_words(String[] words){
        for (int i = 0; i < words.length; i++){
            words[i] = words[i].substring(0, Math.min(words[i].length(), 30));
        }
    }

    public static void filter_words(String[] words){
        int max = 0;
        Set<String> ret_words = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            if (countSubConsonant(words[i]) == max){
                ret_words.add(words[i]);
            }
            if (countSubConsonant(words[i]) > max){
                max = countSubConsonant(words[i]);
                ret_words = new HashSet<>();
                ret_words.add(words[i]);
            }
        }
        System.out.println(ret_words.toString());
    }

    public static int countSubConsonant(String word){
        int count = 0;
        int max = 0;
        String consonantLetters = "BCDFGHJKLMNPQRSTVXZWY";
        for (int i = 0; i < word.length(); i++){
            if (consonantLetters.indexOf(Character.toUpperCase(word.charAt(i))) == -1){
                count = 0;
            }
            else{
                count++;
                max = Math.max(max, count);
            }
        }
        return max;
    }
}
