package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinningTicket {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tickets = sc.nextLine().split("(\\s*,\\s*)");

        for (String ticket : tickets) {
            boolean isValidTicket = ticket.length() == 20;
            if (isValidTicket) {

                String leftHalf = ticket.substring(0, 10);
                String rightHalf = ticket.substring(10, 20);

                String leftMatch = "";
                String rightMatch = "";

                //compiles the sequences: @@@@@@ (at least 6), $$$$$$ (at least 6) and so on
                Pattern pattern = Pattern.compile("[\\\\@]{6,}|[\\\\$]{6,}|[\\\\#]{6,}|[\\\\^]{6,}");

                //matches the compiled sequences if they are present in each substring of the ticket
                Matcher left = pattern.matcher(leftHalf);
                Matcher right = pattern.matcher(rightHalf);

                //if found, groups them into strings according to their respective sides
                if (left.find()) {
                    leftMatch = left.group();
                }

                if (right.find()) {
                    rightMatch = right.group();
                }

                //winning tickets
                //checking the start of each sequence
                //in case left half sequence is different than right half sequence
                if (leftMatch.substring(0, 1).equals(rightMatch.substring(0, 1))) {
                    //length of the sequence
                    int matchLength = Math.min(leftMatch.length(), rightMatch.length());

                    //sequence is 20 symbols long in this case
                    //prints in the format: ticket "{ticket}" - {matchLength}{matchSymbol} Jackpot!
                    if (matchLength == 10) {
                        System.out.printf("ticket \"%s\" - %d%s Jackpot!%n",
                                ticket, matchLength, leftMatch.substring(0, 1));
                    }
                    //prints in the format: ticket "{ticket}" - {matchLength}{matchSymbol}
                    else {
                        System.out.printf("ticket \"%s\" - %d%s%n",
                                ticket, matchLength, leftMatch.substring(0, 1));
                    }
                }
                //non-winning tickets
                else {
                    System.out.printf("ticket \"%s\" - no match%n", ticket);
                }
            }
            //invalid tickets
            else {
                System.out.println("invalid ticket");
            }
        }
    }
}
