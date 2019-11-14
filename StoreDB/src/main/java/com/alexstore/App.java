package com.alexstore;

import javax.persistence.*;
import java.io.*;
import java.util.List;


public class App
{
    // Create an EntityManagerFactory when you start the application
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("store");

        public static List<Orders> getOrderList() {
            
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));
            String fName = null;
            String mName = null;
            String lName = null;
            try {
                fName = bufferedReader.readLine();
                mName = bufferedReader.readLine();
                lName = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            String query = "SELECT o FROM Clients c LEFT JOIN Orders o ON c.id = o.client.id WHERE c.firstName = :fName AND c.middleName = :mName AND c.familyName = :lName ORDER BY c.firstName, c.middleName, c.familyName";
            //TypedQuery<Clients> query1 = entityManager.createQuery(query, String.class);
            TypedQuery<Orders> createdQuery = entityManager.createQuery(query, Orders.class);
            createdQuery.setParameter("fName", fName);
            createdQuery.setParameter("mName", mName);
            createdQuery.setParameter("lName", lName);

            try {
                List<Orders> result = createdQuery.getResultList();
                if (result.isEmpty()) {
                    throw new Exception("Non-existent client.");
                }
                return result;
            /*for (Orders order : result) {
                System.out.print(order.getId() + ". ");
                for (Products product : order.getProducts()) {
                    System.out.println(product.getProductName());
                }
            }*/
            }
            catch(Exception e) {
                String msg = e.getMessage();
                e.printStackTrace();
                System.out.println(msg);
                //return new ArrayList<>();
                return null;
            }
    }

    public static void main( String[] args )
    {
        /*EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT o FROM Clients c LEFT JOIN Orders o ON c.id = o.client.id ORDER BY c.firstName, c.middleName, c.familyName";
        //TypedQuery<Clients> query1 = entityManager.createQuery(query, String.class);
        TypedQuery<Orders> query2 = entityManager.createQuery(query, Orders.class);


        try {
            List<Orders> result = query2.getResultList();
            for (Orders order : result) {
                System.out.print(order.getId() + ". ");
                for (Products product : order.getProducts()) {
                    System.out.println(product.getProductName());
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }*/

        for (Orders order : getOrderList()) {
            //System.out.print(order.getId() + ". ");
            if (!order.getProducts().isEmpty()) {
                System.out.print(order.getId() + ". ");
            }
            for (Products product : order.getProducts()) {
                System.out.println(product.getProductName());
            }
            System.out.println();
        }
    }
}
