package com.company;

import java.io.*;

public class Transaction {
    private long time;
    private int senderId;
    private int receiverId;
    private double cryptoCoins;

    public Transaction() {
        this.time = 0;
        this.senderId = 0;
        this.receiverId = 0;
        this.cryptoCoins = 0;
    }

    public Transaction(int senderId, int receiverId, double cryptoCoins) {
        this.time = System.currentTimeMillis();
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.cryptoCoins = cryptoCoins;
    }

    //constructor to write to file
    public Transaction(long time, int senderId, int receiverId, double cryptoCoins) {
        this.time = time;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.cryptoCoins = cryptoCoins;
    }

    public long getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public double getCryptoCoins() {
        return cryptoCoins;
    }

    public void setCryptoCoins(double cryptoCoins) {
        this.cryptoCoins = cryptoCoins;
    }

    private String toFile() {
        return String.format("%s %s %.5f %d%n",
                this.senderId, this.receiverId, this.cryptoCoins, this.time);
    }

    @Override
    public String toString() {
        return String.format("Sender ID: %s | Receiver ID: %s | Crypto Coins: %.5f | Time: %d",
                this.senderId, this.receiverId, this.cryptoCoins, this.time);
    }

    public void writeToFile() {
        String dir = System.getProperty("user.dir");
        try (FileWriter fw = new FileWriter(dir + "\\Files\\transactions.txt", true);
             BufferedWriter writer = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(writer)) {

            out.print(toFile());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        String dir = System.getProperty("user.dir");
        try(FileReader fr = new FileReader(dir + "\\Files\\transactions.txt");
            BufferedReader reader = new BufferedReader(fr)) {

            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
