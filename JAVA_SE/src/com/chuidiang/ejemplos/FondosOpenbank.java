package com.chuidiang.ejemplos;

import com.google.gson.Gson;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Chuidiang
 * date 18/05/2024
 */
public class FondosOpenbank {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
//        readFromOpenbankUrl();

        String fondos = "fondos-%d.json";
        Gson gson = new Gson();
        PrintStream ps = new PrintStream("fondos-todos.csv");
        ps.println("Nombre;isin;ter;currency;assertClass;subAssetClass;fiveYearChangePercentage");
        for (int i = 1; i < 241; i++) {
            String fund = String.format(fondos, i);
            FileInputStream fis = new FileInputStream(fund);
            final Map map = gson.fromJson(new BufferedReader(new InputStreamReader(fis)), Map.class);
            map.forEach((k, v) -> {
                if ("funds".equals(k)) {
                    Map m = (Map) v;
                    m.forEach((k2, v2) -> {
                        if ("instrumentsList".equals(k2)) {
                            ArrayList<Map> funds = (ArrayList<Map>) v2;
                            funds.forEach(f -> {
                                ps.println(String.format("%s;%s;%f;%s;%s;%s;%f",
                                        f.get("name"),
                                        f.get("isin"),
                                        Float.valueOf((String)f.get("ter")),
                                        f.get("currency"),
                                        f.get("assertClass"),
                                        f.get("subAssetClass"),
                                        f.get("fiveYearChangePercentage")));
                            });
                        }
                    });
                }
            });
            fis.close();
        }
        ps.close();
    }

    private static void readFromOpenbankUrl() throws IOException, URISyntaxException, InterruptedException {
        String url2 = "https://api.openbank.es/wealth-catalogue/public/search/advanced?text=&products=FUNDS&pageSize=10&pageNumber=%d&originCountry=ES";
        for (int i=1;i<241;i++) {

            URI uri = new URI(String.format(url2,i));
            final URLConnection urlConnection = uri.toURL().openConnection();
            urlConnection.connect();
            final InputStream inputStream = urlConnection.getInputStream();
            final Path fondos = Path.of("fondos-"+i+".json");
            Files.copy(inputStream, fondos);
            inputStream.close();
            Thread.sleep(10_000);
        }
    }
}
