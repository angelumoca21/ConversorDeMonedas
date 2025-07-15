package com.alura.ConversorDeMonedas.principal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        Gson gson = new Gson();
        while (true){
            System.out.println("Conversor de moneda");
            System.out.println("1.MXN a USD");
            System.out.println("2.USD a MXN");
            System.out.println("3.MXN a BRL");
            System.out.println("4.BRL a MXN");
            System.out.println("5.MXN a COP");
            System.out.println("6.COP a MXN");
            System.out.println("Ingresa una opción:");
            var opcion = lectura.nextLine();
            String direccion = "https://v6.exchangerate-api.com/v6/0492fc9b62cdf9e03d4976fe/latest/";
            String base = "";
            String destino = "";
            switch (opcion){
                case "1":
                    base = "MXN";
                    destino = "USD";
                    direccion = direccion + "MXN";
                    break;
                case "2":
                    base = "USD";
                    destino = "MXN";
                    direccion = direccion + "USD";
                    break;
                case "3":
                    base = "MXN";
                    destino = "BRL";
                    direccion = direccion + "MXN";
                    break;
                case "4":
                    base = "BRL";
                    destino = "MXN";
                    direccion = direccion + "BRL";
                    break;
                case "5":
                    base = "MXN";
                    destino = "COP";
                    direccion = direccion + "MXN";
                    break;
                case "6":
                    base = "COP";
                    destino = "MXN";
                    direccion = direccion + "COP";
                    break;
                case "0":
                    System.exit(0);
                default:
                    continue;
            }
            System.out.print("Ingresa la cantidad a convertir: ");
            var cantidad = Double.parseDouble(lectura.nextLine());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            //String json = response.body();
            //System.out.println(json);
            JsonObject json = gson.fromJson(response.body(), JsonObject.class);
            double tasa = json.getAsJsonObject("conversion_rates").get(destino).getAsDouble();
            //System.out.println(tasa);
            double resultado = cantidad * tasa;
            System.out.println(cantidad + " " + base + " = " + resultado + " " + destino);
        }
    }
}