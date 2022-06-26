package app.Operations;

import app.Models.Category;
import app.Models.Entity.CategoryEntity;
import app.Models.Entity.ProductEntity;
import app.Models.Product;
import app.Models.REST.CategoryList;
import app.Models.REST.ProductList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebRequest {
    public static final String baseAdress="http://localhost:8080/";
    public static final String SaveProduct = baseAdress+"product/save";
    public static final String GetProductByID = baseAdress+"product/getById/";
    public static final String GetCategoryByName = baseAdress+"cat/getByName/";
    public static final String SaveCategory = baseAdress+"cat/update";
    public static final String GetViewByID = baseAdress+"myView/getById/";
    public static final String GetCategoryById = baseAdress+"cat/getById/";
    public static final String GetProductsByCategoryId = baseAdress+"product/getByCatId/";
    public static final String GetCategoryList = baseAdress+"cat/list";
    public static final String GetProductsAll=baseAdress+"product/all";

    public static boolean setPOST(Product p, String url) {
        URL obj = null;
        try {
            obj = new URL(url);

            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");


            postConnection.setDoOutput(true);
            OutputStream os = postConnection.getOutputStream();

            os.write(JSON.ObjectToJson(p).getBytes());
            os.flush();
            os.close();


            int responseCode = postConnection.getResponseCode();
            if (responseCode == 200) return true;
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setPOST(Category c, String url) {
        URL obj = null;
        try {
            obj = new URL(url);

            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");


            postConnection.setDoOutput(true);
            OutputStream os = postConnection.getOutputStream();
            os.write(JSON.ObjectToJson(c).getBytes());
            os.flush();
            os.close();


            int responseCode = postConnection.getResponseCode();
            if (responseCode == 200) return true;
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Object setGetOne(Class clas, String url) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            if (clas == null) {
                c.disconnect();
                return null;
            }
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    InputStream inputStream = c.getInputStream();
                    ObjectMapper mapper = new ObjectMapper();
                    return mapper.readValue(inputStream, clas);
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (MismatchedInputException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public static List<Product> setGetProductList(String url){
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();

            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    InputStream inputStream = c.getInputStream();
                    ObjectMapper mapper = new ObjectMapper();

                    return  mapper.readValue(inputStream, ProductList.class).productList;
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (MismatchedInputException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public static List<Category> setGetCategoryList(String url){
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();

            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    InputStream inputStream = c.getInputStream();
                    ObjectMapper mapper = new ObjectMapper();

                    return  (mapper.readValue(inputStream, CategoryList.class)).categoryList;
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (MismatchedInputException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void update() {
        try {
            setGetOne(null, "http://localhost:8080/cat/init");
        } catch (IllegalArgumentException ignore) {
        }
    }


}

