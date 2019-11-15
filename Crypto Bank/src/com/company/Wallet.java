package com.company;

import java.io.*;


public class Wallet {
    private String owner;
    private int id;
    private double money;
    private double cryptoCoins;
    private static int instanceCount = 0;
    private static final int course = 375;

    public Wallet() {
        this.owner = "";
        this.id = 0;
        this.money = 0;
        this.cryptoCoins = 0;
    }

    //constructed used to create/add wallet
    public Wallet(String owner, double money) {
        this.owner = owner;
        this.id = ++instanceCount;
        this.money = money;
        this.cryptoCoins = money / course;
        Transaction transaction = new Transaction(Integer.MAX_VALUE, this.id, this.cryptoCoins);
        transaction.writeToFile();
    }

    //constructor to write object to file
    public Wallet(String owner, int id, double money, double cryptoCoins) {
        this.owner = owner;
        this.id = id;
        this.money = money;
        this.cryptoCoins = cryptoCoins;
    }

    public String getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public double getMoney() {
        return money;
    }

    public double getCryptoCoins() {
        return cryptoCoins;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setCryptoCoins(double cryptoCoins) {
        this.cryptoCoins = cryptoCoins;
    }

    public String toFile() {
        return String.format("%s %d %.2f %.5f%n",
                this.owner, this.id, this.money, this.cryptoCoins);
    }

    @Override
    public String toString() {
        return String.format("Owner: %s | Wallet ID: %d | Money: %.2f | Crypto Coins: %.5f%n",
                this.owner, this.id, this.money, this.cryptoCoins);
    }

    public void writeToFile() {
        String dir = System.getProperty("user.dir");
        try (FileWriter fw = new FileWriter(dir + "\\Files\\wallets.txt", true);
             BufferedWriter writer = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(writer)) {

            out.print(toFile());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        String dir = System.getProperty("user.dir");
        try (FileReader fr = new FileReader(dir + "\\Files\\wallets.txt");
        BufferedReader reader = new BufferedReader(fr)) {

            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }

    public void updateWallet(double money, double cryptoCoins) {
        this.money += money;
        this.cryptoCoins += cryptoCoins;
    }
}
