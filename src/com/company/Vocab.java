package com.company;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Vocab {

    public static void main(String[] args) throws FileNotFoundException {
        int count = 'z' - 'a' + 1;
        //System.out.println(count);

        List<String> vocab = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/vocab"))) {
            String s;
            do {
                s = br.readLine();
                if (s != null)
                    vocab.add(s);

            }
            while (s != null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //  System.out.println(vocab.size());

        vocab.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // for(String s:vocab)
        //   System.out.println(s);
//ПЕрвые 10 слов
        ListIterator<String> it = vocab.listIterator(0);
        int i = 0;
        while (it.hasNext() && i < 10) {
            System.out.println(it.next());
            i++;
        }


        //последние 10
        it = vocab.listIterator(vocab.size());
        i = 0;
        while (it.hasPrevious() && i < 10) {
            System.out.println(it.previous());
            i++;
        }

        //Сколько слов  1-буквенных,2-х...
        int countLetter = 0;
        for (String s :

                vocab) {
            if (countLetter < s.length())
                countLetter = s.length();


        }
        // System.out.println(countLetter);
        int[] hist = new int[countLetter];
        for (i = 0; i < vocab.size(); i++)
            hist[vocab.get(i).length() - 1]++;

        for (i = 0; i < countLetter; i++)
            System.out.printf("Букв: %d, слов %d%n", i, hist[i]);

        //Слова-полиндромы

        for (String s : vocab) {
            StringBuilder st = new StringBuilder(s);

            if (s.equals(st.reverse().toString()))
                System.out.println(s);

        }
        //Слова -анаграммы
        Multimap<String, String> anagrams = ArrayListMultimap.create();
        for (String s : vocab) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String s2 = new String(arr);
            anagrams.put(s2, s);
        }

        String key1 = "", key2 = "";
        Map.Entry<String, String> entry2 = null;
        for (Map.Entry<String, String> entry1 : anagrams.entries()) {
            key1 = entry1.getKey();
            if (key1.equals(key2))
                System.out.println(entry2.getValue() + "-" + entry1.getValue());
            key2 = key1;
            entry2 = entry1;
        }
        //Найти слова с 3=мя один.буквами
        for (String s : vocab) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            char c0 = ' ';
            int countChar = 1;
            for (char c : arr) {
                if (c == c0) countChar++;
                else countChar = 1;
                if (countChar == 3) {
                    System.out.println(s);
                    break;
                }
                c0 = c;
            }


        }
        System.out.println("***************");
        //Найти слова в которых 3 буквы следуют по алфавиту
        for (String s : vocab) {
            char[] arr = s.toCharArray();
            char c0 = ' ';
            int countChar = 0;
            for (char c : arr) {
                if (c -c0==1) countChar++;
                else countChar = 1;
                if (countChar == 3) {
                    System.out.println(s);
                    break;
                }
                c0 = c;
            }


        }
    }
}
