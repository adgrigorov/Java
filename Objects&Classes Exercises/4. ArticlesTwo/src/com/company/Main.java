package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfArticles = Integer.parseInt(sc.nextLine());
        List<Article> articles = new ArrayList<>();

        for (int i = 0; i < numberOfArticles; i++) {
            String[] input = sc.nextLine().split(", ");
            String title = input[0];
            String content = input[1];
            String author = input[2];

            Article article = new Article(title, content, author);
            articles.add(article);
        }

        String command = sc.nextLine();

        try {
            if (command.equals("title")) {
                articles.sort(Comparator.comparing(Article::getTitle));
            } else if (command.equals("content")) {
                articles.sort(Comparator.comparing(Article::getContent));
            } else if (command.equals("author")) {
                articles.sort(Comparator.comparing(Article::getAuthor));
            } else {
                throw new Exception("Invalid sort input.");
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
        }
        for (Article article : articles) {
            System.out.println(article.toString());
        }
    }
}