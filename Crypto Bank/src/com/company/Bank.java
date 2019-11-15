package com.company;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bank {

    /*
    --Container class that holds lists of every
    --wallet, every transaction made by wallet's owner
    --and every order made by a wallet's owner

    --SYSTEM WALLET HAS ID 2147483647 AND IS USED FOR
    --ORDER OPERATIONS

    --All operations are contained inside the Bank
        ---creation of wallets
        ---orders
        ---transactions
        ---transfers
        ---gathering wallet information
        ---displaying richest wallets for investment purposes

     --Information is stored in files
        ---orders.txt
        ---wallets.txt
        ---transactions.txt

     --Upon starting the application, information from files
     --is loaded into the system

     */
    private ArrayList<Wallet> wallets;
    private ArrayList<Transaction> transactions;
    private ArrayList<Order> orders;
    public final static int course = 375;
//-----------------------------------------------------------------------//
    /*
    --Initialize system
    */
    public Bank() {
        this.wallets = new ArrayList<>(0);
        this.transactions = new ArrayList<>(0);
        this.orders = new ArrayList<>(0);
    }

    public ArrayList<Wallet> getWallets() {
        return wallets;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    /*
    --Load information from wallets.txt file into
    --system's list of wallets
    */
    public void readWallets() {
        String dir = System.getProperty("user.dir");
        List<String> fileLines = new ArrayList<>();
        try(FileReader fr = new FileReader(dir + "\\Files\\wallets.txt");
        BufferedReader reader = new BufferedReader(fr)) {

            String line = reader.readLine();
            while (line != null) {
                fileLines.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (String line : fileLines) {
                line = line.replace(',', '.');
                String regex = "(?<owner>[A-Za-z]+) (?<walletID>\\d+) (?<money>\\d+\\.\\d+) (?<cryptoCoins>\\d+\\.\\d+)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String owner = matcher.group("owner");
                    int walletId = Integer.parseInt(matcher.group("walletID"));
                    double money = Double.parseDouble(matcher.group("money"));
                    double cryptoCoins = Double.parseDouble(matcher.group("cryptoCoins"));
                    Wallet wallet = new Wallet(owner, walletId, money, cryptoCoins);
                    this.wallets.add(wallet);
                }
            }
        } catch (Exception e) {
            System.err.println("Couldn't read from wallets file");
            e.printStackTrace();
        }
    }

    /*
    --Load information from transactions.txt file into
    --system's list of transactions
    */
    public void readTransactions() {
        String dir = System.getProperty("user.dir");
        List<String> fileLines = new ArrayList<>();
        try (FileReader fr = new FileReader(dir + "\\Files\\transactions.txt");
            BufferedReader reader = new BufferedReader(fr)) {

            String line = reader.readLine();
            while (line != null) {
                fileLines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (String line : fileLines) {
                line = line.replace(',', '.');
                String regex = "(?<senderID>\\d+) (?<receiverID>\\d+) (?<cryptoCoins>\\d+\\.\\d*) (?<time>\\d+)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    int senderId = Integer.parseInt(matcher.group("senderID"));
                    int receiverId = Integer.parseInt(matcher.group("receiverID"));
                    double cryptoCoins = Double.parseDouble(matcher.group("cryptoCoins"));
                    long time = Long.parseLong(matcher.group("time"));
                    Transaction transaction = new Transaction(time, senderId, receiverId, cryptoCoins);
                    this.transactions.add(transaction);
                }
            }
        } catch (Exception e) {
            System.err.println("Couldn't read from transactions file");
            e.printStackTrace();
        }
    }

    /*
    --Load information from orders.txt file into
    --system's list of orders
    */
    public void readOrders() {
        String dir = System.getProperty("user.dir");
        List<String> fileLines = new ArrayList<>();
        try (FileReader fr = new FileReader(dir + "\\Files\\orders.txt");
            BufferedReader reader = new BufferedReader(fr)) {

            String line = reader.readLine();
            while (line != null) {
                fileLines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (String line : fileLines) {
                line = line.replace(',', '.');
                String regex = "(?<type>buy|sell) (?<id>\\d+) (?<cryptoCoins>\\d+\\\\.\\d*)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String type = matcher.group("type");
                    int walletId = Integer.parseInt(matcher.group("id"));
                    double cryptoCoins = Double.parseDouble(matcher.group("cryptoCoins"));
                    Order order = new Order(type, walletId, cryptoCoins);
                    this.orders.add(order);
                }
            }
        } catch (Exception e) {
            System.err.println("Couldn't read from orders file");
            e.printStackTrace();
        }
    }

    /*
    --Store information from system's list of orders
    --into orders.txt file
    --Loads upon next system start-up
    */
    public void writeOrders() {
        String dir = System.getProperty("user.dir");
        try (FileWriter fw = new FileWriter(dir + "\\Files\\orders.txt", true); //DOESN'T file content
             BufferedWriter writer = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(writer)) {

            for (Order order : this.orders) {
                out.print(order.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    --Store information from system's list of wallets
    --into wallets.txt file
    --Loads upon next system start-up
    */
    public void writeWallets() {
        String dir = System.getProperty("user.dir");
        try (FileWriter fw = new FileWriter(dir + "\\Files\\wallets.txt", true);
            BufferedWriter writer = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(writer)) {

            for (Wallet wallet : this.wallets) {
                out.print(wallet.toFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateWalletsFile() {
        String dir = System.getProperty("user.dir");
        try (FileWriter fw = new FileWriter(dir + "\\Files\\wallets.txt");
             BufferedWriter writer = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(writer)) {

            for (Wallet wallet : this.wallets) {
                out.print(wallet.toFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    --Store information from system's list of transactions
    --into transactions.txt file
    --Loads upon next system start-up
    */
    public void writeTransactions() {
        String dir = System.getProperty("user.dir");
        try(FileWriter fw = new FileWriter(dir + "\\Files\\transactions.txt", true);
            BufferedWriter writer = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(writer)) {

            for (Transaction transaction : this.transactions) {
                out.print(transaction.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    --Creates wallet
    --Upon creation, Bank transfers Crypto Coins
    --equal to the money divided by the course

    --Upon creation, a transaction is made between
    --the Bank (System wallet) and the wallet owner
    */
    public void addWallet(Wallet wallet) {
        this.wallets.add(wallet);
        wallet.writeToFile();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.writeToFile();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        order.writeToFile();
    }

    /*
    --Check for existing wallet owner inside the Bank
    --in order to make operations
    */
    public boolean walletExists(int walletID) {
        for (Wallet wallet : this.wallets) {
            if (wallet.getId() == walletID) {
                return true;
            }
        }
        return false;
    }

    /*
    --Completes order between a wallet owner and the Bank
    --Requirements for a valid order:
        ---BUY:
            ----Wallet owner exists
            ----Wallet owner has enough money to buy the desired amount of Crypto Coins
        ---SELL:
            ----Wallet owner exists
            ----Wallet owner has enough amount of Crypto Coins to sell

    --Upon completion of an order, information is sent into orders.txt
    --file and is registered in the Bank's list of orders

    --A transaction is also generated between Bank and wallet owner
    --which is sent into transactions.txt file and registered
    --in the Bank's list of transactions
    */
    public void makeOrder(String type, double cryptoCoins, int walletId) {
        if (walletExists(walletId)) {
            for (Wallet wallet : this.wallets) {
                if (wallet.getId() == walletId) {
                    double moneyFromTransaction = cryptoCoins * 375; //according to the course
                    if (type.equalsIgnoreCase("buy")) {
                        if (wallet.getMoney() >= moneyFromTransaction) {
                            wallet.updateWallet(-moneyFromTransaction, cryptoCoins); //charges money to buy cryptoCoins
                            Transaction transaction = new Transaction(Integer.MAX_VALUE, walletId, cryptoCoins);
                            transaction.writeToFile();
                        }
                    } else if (type.equalsIgnoreCase("sell")) {
                        if (wallet.getCryptoCoins() >= cryptoCoins) {
                            wallet.updateWallet(moneyFromTransaction, -cryptoCoins); //charges cryptoCoins to receive money
                            Transaction transaction = new Transaction(walletId, Integer.MAX_VALUE, cryptoCoins);
                            transaction.writeToFile();
                        }
                    }
                }
            }
            Order order = new Order(type, walletId, cryptoCoins);
            order.writeToFile();
        }
    }

    /*
    --Completes a transfer between 2 wallet owners
    --Requirements for a transfer to be completed:
        ---Both wallet owners exist in the Bank
        ---Sender has enough money in their wallet

    --Upon a completed transfer, information is updated
    --in both wallet holders' accounts

    --Upon a completed transfer, a transaction is generated
    --and sent to transactions.txt file and registered
    --in the Bank's list of transactions
    */
    public void makeTransfer(int senderId, int receiverId, double cryptoCoins) throws IOException {
        String fileDir = System.getProperty("user.dir") + "\\Files\\wallets.txt";
        Path path = Paths.get(fileDir);
        if (walletExists(senderId) && walletExists(receiverId)) {
            for (Wallet sender : this.wallets) {
                for (Wallet receiver : this.wallets) {
                    if (sender.getId() == senderId && receiver.getId() == receiverId) {
                        System.out.println(sender.toFile());
                        System.out.println(receiver.toFile());
                        if (sender.getCryptoCoins() >= cryptoCoins) {
                            double senderCurrentCoins = sender.getCryptoCoins();
                            sender.setCryptoCoins(senderCurrentCoins - cryptoCoins);
                            double receiverCurrentCoins = receiver.getCryptoCoins();
                            receiver.setCryptoCoins(receiverCurrentCoins + cryptoCoins);
                            Transaction transaction = new Transaction(senderId, receiverId, cryptoCoins);
                            transaction.writeToFile();
                        }
                    }
                }
            }
        }
    }

    /*
    --If the desired wallet exists, gives information
    --about its owner, money amount and Crypto Coins amount
    */
    public void walletInfo(int walletId) {
        if (walletExists(walletId)) {
            for (Wallet wallet : this.wallets) {
                System.out.printf("Owner: %s%nMoney: %.2f%nCrypto Coins: %.5f%n",
                        wallet.getOwner(), wallet.getMoney(), wallet.getCryptoCoins());
            }
        }
    }

    /*
    --For investment purposes: gives information about
    --the Bank's top 10 richest wallets (according to Crypto Coins)
    --Displays each wallet's list of transactions and the timestamps
    --of first and last completed transaction
    */
    public void topTenRichestWallets() {
        ArrayList<Wallet> richestWallets = new ArrayList<>(this.wallets);
        /*richestWallets.sort((a, b) ->
                Double.compare(b.getCryptoCoins(), a.getCryptoCoins()));*/
        ArrayList<Transaction> walletTransactions = new ArrayList<>();
        richestWallets
                .stream()
                .sorted((a, b) -> Double.compare(b.getCryptoCoins(), a.getCryptoCoins()))
                .forEach(wallet -> {
                    System.out.printf("Crypto Coins: %.5f%nTransactions:%n", wallet.getCryptoCoins());
                    for (Transaction t : this.transactions) {
                    if (wallet.getId() == t.getSenderId()
                        || wallet.getId() == t.getReceiverId()) {
                        walletTransactions.add(t);
                        System.out.printf("## %s%n", t.toString());
                    }
                }
                System.out.printf("First transaction time: %d%n",
                    walletTransactions.get(0).getTime());
                System.out.printf("Last transaction time: %d%n",
                    walletTransactions.get(walletTransactions.size() - 1).getTime());
                    System.out.println();
        });
    }
}
