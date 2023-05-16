package ru.netology;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static File saveFile = new File("basket.json");
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        XMLSettingsReader settings = new XMLSettingsReader(new File("shop.xml"));
        File loadFile = new File(settings.loadFile);
        File saveFile = new File(settings.saveFile);
        File logFile = new File(settings.logFile);

        Scanner scanner = new Scanner(System.in);

        String[] products = {"Молоко", "Хлеб", "Мясо", "Рис"};
        String[] product = new String[products.length];
        int[] prices = {64, 25, 170, 90};
        System.out.println("Список возможных товаров для покупки");



        Basket basket = new Basket(products,prices);
        ClientLog clientLog = new ClientLog();

        int n = 1;
        for (int i = 0; i < products.length; i++) {
            product[i] = products[i];
            System.out.println(n + " " + products[i] + " " + prices[i] + " руб.");
            n++;
        }

        while (true) {
            System.out.println("Выберите товар и количество или введите 'end'");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                basket.printCart();
                clientLog.exportAsCSV(new File("log.csv"));
                break;
            }

            try {
                String parts[] = input.split(" ");

                int productNumber = Integer.parseInt(parts[0]) - 1;

                if (parts.length != 2) {
                    System.out.println("Нужно выбрать товар и ввести его количество, вы ввели: " + input);
                    continue;
                }

                int productCount = Integer.parseInt(parts[1]);

                basket.addToCart(productNumber, productCount);
                clientLog.log(productNumber, productCount);
                basket.saveJson(saveFile);


            }catch (NumberFormatException e){
                System.out.println("Ошибка, некоректный ввод.");
                continue;
            }
        }
    }
}