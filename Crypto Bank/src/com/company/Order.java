package com.company;

import java.io.*;

public class Order {
    private String type;
    private int walletId;
    private double cryptoCoins;

    public Order() {
        this.type = "";
        this.walletId = 0;
        this.cryptoCoins = 0;
    }

    public Order(String  type, int walletId, double cryptoCoins) {
        this.type = type;
        this.walletId = walletId;
        this.cryptoCoins = cryptoCoins;
    }

    public String getType() {
        return type;
    }

    public int getWalletId() {
        return walletId;
    }

    public double getCryptoCoins() {
        return cryptoCoins;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public void setCryptoCoins(double cryptoCoins) {
        this.cryptoCoins = cryptoCoins;
    }

    private String toFile() {
        return String.format("%s %d %.5f%n",
                this.type, this.walletId, this.cryptoCoins);
    }

    @Override
    public String toString() {
        return String.format("Type: %s | Wallet ID: %d | Crypto Coins Amount: %.5f%n",
                this.type, this.walletId, this.cryptoCoins);
    }

    public void writeToFile() {
        String dir = System.getProperty("user.dir");
        try (FileWriter fw = new FileWriter(dir + "\\Files\\orders.txt", true); //DOESN'T overwrite
             BufferedWriter writer = new BufferedWriter(fw);
             PrintWriter in = new PrintWriter(writer)) {

            in.write(toFile()); //adds next order to previous orders

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        String dir = System.getProperty("user.dir");
        try (FileReader fr = new FileReader(dir + "\\Files\\orders.txt");
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
