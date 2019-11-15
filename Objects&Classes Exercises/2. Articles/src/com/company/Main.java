package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(", ");
        String articleTitle = input[0];
        String articleContent = input[1];
        String articleAuthor = input[2];

        Article article = new Article(articleTitle, articleContent, articleAuthor);

        int numberOfCommands = sc.nextInt();

        for (int i = 0; i <= numberOfCommands; i++) {
            String[] command = sc.nextLine().split(": ");
            String action = command[0];
            if (action.equals("Edit")) {
                String newContent = command[1];
                article.setContent(newContent);
            }

            else if (action.equals("ChangeAuthor")) {
                String newAuthor = command[1];
                article.setAuthor(newAuthor);
            }

            else if (action.equals("Rename")) {
                String newTitle = command[1];
                article.setTitle(newTitle);
            }
        }
        article.printArticle();
    }
}
