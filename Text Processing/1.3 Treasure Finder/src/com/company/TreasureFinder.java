package com.company;

import java.util.*;

public class TreasureFinder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String key = sc.nextLine().replace(" ", "");

        String hiddenMessage = sc.nextLine();
        StringBuilder decypheredMessageBuilder = new StringBuilder();
        Map<String, String> treasures = new LinkedHashMap<>();

        while (!hiddenMessage.equals("find")) {
            //always holds one key sequence more than msg length in order not to run out of length
            key = key.repeat(hiddenMessage.length() / key.length() + 1);

            for (int i = 0; i < hiddenMessage.length(); i++) {
                char currentSymbol = hiddenMessage.charAt(i);
                int correspondingKeyValue = Character.getNumericValue(key.charAt(i));
                char modifiedSymbol = (char)(currentSymbol - correspondingKeyValue);
                decypheredMessageBuilder.append(modifiedSymbol);
            }

            String decypheredMessage = decypheredMessageBuilder.toString();

            int firstIndexOfType = decypheredMessage.indexOf('&') + 1;
            int lastIndexOfType = decypheredMessage.lastIndexOf('&');

            int firstIndexOfCoordinates = decypheredMessage.indexOf('<') + 1;
            int lastIndexOfCoordinates = decypheredMessage.indexOf('>');

            String treasureType = decypheredMessage.substring(firstIndexOfType, lastIndexOfType);
            String treasureCoordinates = decypheredMessage.substring(firstIndexOfCoordinates, lastIndexOfCoordinates);

            treasures.put(treasureType, treasureCoordinates);

            decypheredMessageBuilder = new StringBuilder();

            hiddenMessage = sc.nextLine();
        }

        for (Map.Entry<String, String> treasure : treasures.entrySet()) {
            System.out.printf("Found %s at %s%n", treasure.getKey(), treasure.getValue());
        }
    }
}
