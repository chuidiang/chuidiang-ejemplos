package com.chuidiang;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejemplo Sencillo de acceso a Elasticsearch desde Java.
 * Conexión y operaciones CRUD típicas.
 */
public class ElasticsearchExample
{
    public static final String PRODUCTS = "products";
    private static ElasticsearchClient elasticsearchClient;
    private static ElasticsearchTransport transport;
    public static void main( String[] args )
    {
        connect();

        try {
            elasticsearchClient.indices().create(b -> b.index(PRODUCTS));
        } catch (IOException e) {
            e.printStackTrace();
        }
        flush();
        insertSingle();
        flush();
        insertMultiple();
        flush();
        selectAll();
        flush();
        update();
        flush();
        selectById();
        flush();
        selectByQuery();
        flush();
        deleteById();
        flush();
        selectAll();
        flush();

        try {
            elasticsearchClient.indices().delete(b -> b.index(PRODUCTS));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            transport.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteById() {
        System.out.println("Deleting");
        try {
            DeleteResponse delete = elasticsearchClient.delete(d ->
                    d.index(PRODUCTS)
                            .id("1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Borrado!!");
    }

    private static void insertMultiple() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("1","Uno", 1));
        productList.add(new Product("2","Dos", 2));
        productList.add(new Product("3","Tres", 3));
        productList.add(new Product("4","Cuatro", 1));  // El 1 está a posta para findByQuery().

        BulkRequest.Builder bulkRequest = new BulkRequest.Builder();

        productList.forEach(product ->
                bulkRequest.operations(op -> op.index(idx ->
                        idx.index(PRODUCTS)
                                .document(product)
                                .id(product.id()))));

        try {
            BulkResponse bulkResponse = elasticsearchClient.bulk(bulkRequest.build());
            if (bulkResponse.errors()){
                System.out.println("Algo va mal en bulk insert");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void connect() {
        System.out.println("Estableciendo conexión");
        String elasticsearchUrl = "https://localhost:9200";

        try {
            File certFile = new File("src/main/files/http_ca.crt");
            SSLContext sslContext = TransportUtils
                    .sslContextFromHttpCaCrt(certFile);

            BasicCredentialsProvider credentialsProvider =  new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials("elastic","*Kg1ejic8ZNrncsyKi_G"));

            RestClient restClient = RestClient.builder(HttpHost.create(elasticsearchUrl))
                    .setHttpClientConfigCallback(hc ->
                            hc.setDefaultCredentialsProvider(credentialsProvider)
                                    .setSSLContext(sslContext))
                    .build();

            transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
            elasticsearchClient = new ElasticsearchClient(transport);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Conexión establecida");

    }

    private static void insertSingle() {
        System.out.println("Insertando un elemento");
        Product product = new Product("bk-1", "City bike", 123.0);

        IndexResponse response = null;
        try {
            response = elasticsearchClient.index(i -> i
                    .index(PRODUCTS)
                    .id(product.id())
                    .document(product)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Indexed with version " + response.version());
    }

    private static void selectAll(){
        System.out.println("Select All");
        try {
            SearchResponse<Product> products = elasticsearchClient.search(s -> s.index(PRODUCTS), Product.class);
            products.hits().hits().forEach(product -> System.out.println(product));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void update(){
        Product updatedProduct = new Product("1", "One", 1);
        try {
            UpdateResponse<Product> update = elasticsearchClient.update(u -> u.index(PRODUCTS)
                            .id(updatedProduct.id())
                            .doc(updatedProduct),
                    Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void selectById (){
        System.out.println("Find by id");
        try {
            GetResponse<Product> productGetResponse = elasticsearchClient.get(r ->
                            r.index(PRODUCTS)
                                    .id("1"),
                    Product.class);
            System.out.println(productGetResponse.source());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void selectByQuery (){
        System.out.println("Find by Query");
        try {
            SearchResponse<Product> search = elasticsearchClient.search(r -> r.index(PRODUCTS)
                            .query(q -> q.match( m -> m.field("value").query(1))),
                    Product.class);
            search.hits().hits().forEach(product -> System.out.println(product.source()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void flush(){
        try {
            elasticsearchClient.indices().flush();
            Thread.sleep(1000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
