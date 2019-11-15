package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheHeiganDance {
    //стаята на Heigan е матрица 15х15, като играчите започват от центъра на стаята
    //на всеки ход Heigan използва магия, която удря конкретна клетка и съседните ѝ редове/колони
    //пр: ако удари [1][1], ще удари и [0][0], [0][1], [0][2], .... [2][2]
    //ако текущата позиция на играча е в зоната на удара, играчът трябва да се премести
    //първо се опитва да се мести НАГОРЕ
    //ако има УДАР/СТЕНА, се мести НАДЯСНО
    //после НАДОЛУ, после НАЛЯВО
    //ако не може да се премести никъде, защото клетката е ударена или има стена, играчът остава и поема щетата

    //ОБЛАКЪТ(ефект от магията) прави 3500 щета на удар и 3500 на следващия ход, след което изчезва
    //ИЗРИГВАНЕТО(магията) прави 6000 щета при удар
    //ако магия удари играч, който има активен ОБЛАК от предишния ход, ПЪРВО се отчита щетата от ОБЛАКА
    //И двамата (играчът и Heigan) може да умрат в един и същ ход
    //ако Heigan умре, щетата не се прилата

    //играчът започва с 18 500 кръв, Heigan - с 3 000 000
    //на всеки ход играчът нанася щета
    //битката продължава докато един от двамата умре

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int playerHealth = 18500;
        double bossHealth = 3000000;

        double playerDamage = Double.parseDouble(reader.readLine());

        //началната позиция на играча в стаята
        int[] playerPosition = new int[2];
        playerPosition[0] = 7; //row
        playerPosition[1] = 7; //column

        boolean isInCloud = false; //Cloud удря 2 пъти (1 път на текущия ход и 1 път на следващия)

        String lastSpell = ""; //в случай, че на текущия ход има Eruption, а играчът е умрял от втория удар на Cloud

        while (playerHealth > 0) {
            bossHealth -= playerDamage; //играчът винаги удря пръв

            if (bossHealth <= 0) { //Heigan е мъртъв
                break;
            }

            if (isInCloud) { //на предишния ход е имало Cloud, който играчът не е успял да избегне => втори удар на Cloud
                playerHealth -= 3500;
                isInCloud = false;
            }

            //ако играчът умре от втория удар на Cloud, въпреки че на текущия ход магията е Eruption
            if (playerHealth <= 0) {
                lastSpell = "Cloud";
                break;
            }

            if (bossHealth <= 0) {
                break;
            }

            String[] currentTurnAttack = reader.readLine().split("\\s+");
            String spell = currentTurnAttack[0];
            int targetRow = Integer.parseInt(currentTurnAttack[1]);
            int targetCol = Integer.parseInt(currentTurnAttack[2]);
            lastSpell = spell;

            //преди да удари магията, проверяваме дали играчът въобще ще бъде засегнат
            boolean isPlayerDamaged = checkIfPlayerIsHit(playerPosition[0], playerPosition[1], targetRow, targetCol);
            //ако не е засегнат, няма смисъл да се прави проверката за вида магия

            //ако е засегнат, гледаме от каква магия е ударен
            int damageDone = 0;
            if(isPlayerDamaged) {
                if (spell.equals("Cloud")) {
                    damageDone = 3500;
                } else if (spell.equals("Eruption")) {
                    damageDone = 6000;
                }
                boolean canMove = checkPlayerMovement(playerPosition, targetRow, targetCol);

                //мести се, САМО АКО Е В ПОЛЕТО НА УДАРА и има къде да се премести
                if (!canMove) { //ако няма къде да отиде, поема щетата
                    playerHealth -= damageDone;
                    if (spell.equals("Cloud")) { //по този начин ще поеме щета и на следващия ход
                        isInCloud = true;
                    }
                }
            } //end if(isPlayerDamaged)
        } //end while

        if (bossHealth > 0) {
            System.out.printf("Heigan: %.2f%n", bossHealth); //оставаща кръв
        } else {
            System.out.println("Heigan: Defeated!");
        }

        if (playerHealth <= 0) {
            lastSpell = lastSpell.equals("Cloud") ? "Plague Cloud" : lastSpell;
            System.out.printf("Player: Killed by %s%n", lastSpell);
        } else {
            System.out.printf("Player: %d%n", playerHealth); //оставаща кръв
        }
        System.out.printf("Final position: %d, %d", playerPosition[0], playerPosition[1]);
    }

    private static boolean checkPlayerMovement(int[] position, int targetRow, int targetCol) {
        boolean canMove = false;

        int playerRow = position[0];
        int playerCol = position[1];
/*
        if (playerRow == targetRow && playerCol == targetCol) {
            canMove = false;
        }*/

        //мести се НАГОРЕ, ако може
        if (isInBounds(playerRow - 1, playerCol)
                && !checkIfPlayerIsHit(playerRow - 1, playerCol, targetRow, targetCol)) {
            playerRow--;
            canMove = true;
        }

        //мести се НАДЯСНО, ако може
        else if (isInBounds(playerRow, playerCol + 1)
                && !checkIfPlayerIsHit(playerRow, playerCol + 1, targetRow, targetCol)) {
            playerCol++;
            canMove = true;
        }

        //мести се НАДОЛУ, ако може
        else if (isInBounds(playerRow + 1, playerCol)
                && !checkIfPlayerIsHit(playerRow + 1, playerCol, targetRow, targetCol)) {
            playerRow++;
            canMove = true;
        }

        //мести се НАЛЯВО, ако може
        else if(isInBounds(playerRow, playerCol - 1)
                && !checkIfPlayerIsHit(playerRow, playerCol - 1, targetRow, targetCol)) {
            playerCol--;
            canMove = true;
        }

        position[0] = playerRow;
        position[1] = playerCol;

        return canMove;
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < 15
                && col >= 0 && col < 15;
    }

    private static boolean checkIfPlayerIsHit(int playerRow, int playerCol, int targetRow, int targetCol) {
        return playerRow >= targetRow - 1 && playerRow <= targetRow + 1
                && playerCol >= targetCol - 1 && playerCol <= targetCol + 1;
        //тъй като щетата е 3х3 подматрица, проверяваме дали позицията на играча е измежду клетките на тази подматрица
    }
}
