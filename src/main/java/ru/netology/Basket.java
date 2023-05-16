package ru.netology;
import com.google.gson.Gson;
import java.io.DataInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Basket implements Serializable{
    protected String[] product;
    protected int[] prices;

    protected int[] quantity = new int[4];
    protected int[] userProducts = new int[4];
    protected String basket = "";

    public Basket(String[] product, int[] prices) {
        this.product = product;
        this.prices = prices;
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

    public void saveJson(File file){
        try (PrintWriter writer = new PrintWriter(file)){
            Gson gson = new Gson();
            String json = gson.toJson(this);
            writer.print(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromJson(File file){
        Basket basket = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            Gson json = new Gson();
            basket = json.fromJson(builder.toString(), Basket.class);

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }
}
