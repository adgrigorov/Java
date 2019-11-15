package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    private static void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Add Wallet");
        System.out.println("2. Make Order");
        System.out.println("3. Transfer Crypto Coins");
        System.out.println("4. Display Wallet Info");
        System.out.println("5. Attract Investors - Display Top 10 Richest Wallets");
        System.out.println("6. Quit");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /*
            LOADS INFORMATION FROM FILE SYSTEM ON EVERY START
        */
        Bank bank = new Bank();
        bank.writeOrders();
        bank.writeWallets();
        bank.writeTransactions();
        bank.readOrders();
        bank.readWallets();
        bank.readTransactions();
        bank.getWallets().forEach(wallet -> System.out.println(wallet.toFile()));
        
        int option;
        while (true) {
            printMenu();
            option = Integer.parseInt(reader.readLine());
            switch (option) {
                case 1:
                    System.out.print("Enter owner: ");
                    String owner = reader.readLine();
                    System.out.print("Enter money amount: ");
                    double money = Double.parseDouble(reader.readLine());
                    System.out.println();
                    Wallet wallet = new Wallet(owner, money);
                    bank.addWallet(wallet);
                    break;
                case 2:
                    System.out.print("Specify type (buy/sell): ");
                    String orderType = reader.readLine();
                    System.out.print("Enter amount of Crypto Coins: ");
                    double cryptoCoinsOrder = Double.parseDouble(reader.readLine());
                    System.out.print("Enter wallet ID: ");
                    int walletId = Integer.parseInt(reader.readLine());
                    bank.makeOrder(orderType, cryptoCoinsOrder, walletId);
                    bank.updateWalletsFile();
                    break;
                case 3:
                    System.out.print("Enter sender ID: ");
                    int senderId = Integer.parseInt(reader.readLine());
                    System.out.print("Enter receiver ID: ");
                    int receiverId = Integer.parseInt(reader.readLine());
                    System.out.print("Enter amount of Crypto Coins: ");
                    double cryptoCoinsTransfer = Double.parseDouble(reader.readLine());
                    bank.makeTransfer(senderId, receiverId, cryptoCoinsTransfer);
                    bank.updateWalletsFile();
                    break;
                case 4:
                    System.out.print("Enter wallet ID: ");
                    int walletIdInfo = Integer.parseInt(reader.readLine());
                    bank.walletInfo(walletIdInfo);
                    break;
                case 5:
                    bank.topTenRichestWallets();
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }
}
