package ru.netology;
import java.io.*;
import java.util.ArrayList;

public class Basket implements Serializable{
    ClientLog clientLog = new ClientLog();
    protected String[] product;
    protected int[] prices;

    protected int[] quantity = new int[4];
    protected int[] userProducts = new int[4];
    protected String basket = "";

    public Basket(String[] product, int[] prices) {
        this.product = product;
        this.prices = prices;
    }

    public Basket(String basket) {
    }

    public void printCart() throws IOException {
        int[] userProducts = new int[4];
        Integer[] quantit = new Integer[userProducts.length];

        for (int m = 0; m < userProducts.length; m++) {
            userProducts[m] = quantity[m] * prices[m];
        }

        int sumProducts = 0;

        System.out.println("Ваша корзина:");

        for (int p = 0; p < userProducts.length; p++) {
            sumProducts = sumProducts + userProducts[p];
        }

        for (int t = 0; t < userProducts.length; t++) {
            quantit[t] = quantity[t];
            if (userProducts[t] > 0) {
                basket += (product[t] + " " + quantity[t] + " шт. "
                        + prices[t] + " руб. за товар " + userProducts[t] + " руб к сумме\n");
            }
        }

        clientLog.log(quantit);

        basket += ("Итого: " + sumProducts + " руб.");
        System.out.println(basket);
    }

    public void addToCart(int productNum, int amount) {
        if (productNum < 0 || productNum > 3) {
            System.out.println("Данного товара нет.");
        } else {
            if (amount < 1) {
                System.out.println("Введено не коректное число товаров.");
            }
        }
        quantity[productNum] += amount;
    }

    public void saveTxt(Basket textFile) throws IOException {
        Basket printWriter = new Basket(basket);

        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\WWW\\IdeaProjects\\ShopProducts");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(printWriter);

        objectOutputStream.close();
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\WWW\\IdeaProjects\\ShopProducts");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Basket basket = (Basket) objectInputStream.readObject();

        return basket;
    }
}
