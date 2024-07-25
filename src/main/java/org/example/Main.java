package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
    String filePath = "src/main/resources/skaiciai.csv";
    List<Integer> skaiciuSarasas = fileRead(filePath);
        System.out.println("Dazniausiai pasikartoja "+kurisSkaiciusDaugiausiaiPasikartojoSarase(skaiciuSarasas));
    }

    static int kurisSkaiciusDaugiausiaiPasikartojoSarase(List<Integer> skaiciuSarasas){

        Map<Integer, Integer> pasikartojaMap = new HashMap<>();

        for (int number : skaiciuSarasas) {
            pasikartojaMap.put(number, pasikartojaMap.getOrDefault(number, 0) + 1);
        }

        int dazniausiaiKartojasi = -1;
        int pasikartoja = 0;

        for (Map.Entry<Integer, Integer> entry : pasikartojaMap.entrySet()) {
            if (entry.getValue() > pasikartoja) {
                dazniausiaiKartojasi = entry.getKey();
                pasikartoja = entry.getValue();
            }
        }

        return dazniausiaiKartojasi;
    }

    static List<Integer> fileRead(String path){
        List<Integer> naujasSkaiciuSarasas = new ArrayList<>();
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] skaiciuSarasas = line.split(",");
                for (String skaicius : skaiciuSarasas) {
                    try {
                        int number = Integer.parseInt(skaicius.trim());
                        naujasSkaiciuSarasas.add(number);
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException(e + "");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return naujasSkaiciuSarasas;
    }
}