package com.company;

import java.util.Random;
import java.util.Scanner;

public class AdvertisementMessage {

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);

	    int numberOfMessages = sc.nextInt();

	    String[] phrases = {"Excellent product.", "Such a great product.", "I always use that product.",
                        "Best product of its category.", "Exceptional product", "I can't live without this product."};
	    String[] events = {"Now I feel good.", "I have succeeded with this product.",
                        "Makes miracles. I am happy of the results!", "I cannot believe but now I feel awesome.", "I feel great!",
                           "Try it yourself, I am very satisfied."};
	    String[] authors = {"Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};
	    String[] cities = {"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};

        Random rnd = new Random();

	    for (int i = 0; i < numberOfMessages; i++) {
	        int randomPhrase = rnd.nextInt(phrases.length);
	        int randomEvent = rnd.nextInt(events.length);
	        int randomAuthor = rnd.nextInt(authors.length);
	        int randomCity = rnd.nextInt(cities.length);

            System.out.printf("%s %s %s - %s%n", phrases[randomPhrase], events[randomEvent],
                    authors[randomAuthor], cities[randomCity]);
        }
    }
}