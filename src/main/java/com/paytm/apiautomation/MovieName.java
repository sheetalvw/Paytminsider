package com.paytm.apiautomation;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MovieName {
    String url = "https://apiproxy.paytm.com/v2/movies/upcoming";

    @Test
    public void getResponse() throws Exception {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);

        //1. Status code.
        Assert.assertEquals(closeableHttpResponse.getStatusLine().getStatusCode(), 200);

        String responseByApi = EntityUtils.toString(closeableHttpResponse.getEntity());
        JSONObject jsonObject = new JSONObject(responseByApi);


        JSONArray jsonArray = (JSONArray) jsonObject.get("upcomingMovieData");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("moviename");
        int rowCount = 0;
        Row row1 = sheet.createRow(rowCount);
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue((String) "MOVIE NAME");
        for (int i = 0; i < jsonArray.length(); i++) {
            System.out.println("------------------------------------------------------------------");
            int columnCount = 0;
            JSONObject moviejson = jsonArray.getJSONObject(i);

            String movieName = moviejson.getString("provider_moviename");
            String posterUrl = moviejson.getString("moviePosterUrl");
            String releasedate = moviejson.get("releaseDate").toString();
            String movieCode = moviejson.getString("paytmMovieCode");
            int contentAvailable = moviejson.getInt("isContentAvailable");
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            //2. Movie release date: should not be before today’s date
            if(!releasedate.equals(currentDate)){
                System.out.println(releasedate);
            }

            //3. Movie Poster URL: should only have .jpg format
              if(!posterUrl.contains(".jpg")){
                  System.out.println(posterUrl);
              }

            //4. Paytm movie code: is unique.
            System.out.println();



            //5. No movie code should have more than 1 language format.


            //content available is 0.
            if (contentAvailable == 0) {
                Row row = sheet.createRow(++rowCount);
                Cell cell = row.createCell(columnCount);
                cell.setCellValue((String) movieName);
                columnCount++;
            }

        }
        try (FileOutputStream outputStream = new FileOutputStream("E:\\PaytmFiles\\Zerocontentmoviename.xlsx")) {
            workbook.write(outputStream);
        }
    }


}
