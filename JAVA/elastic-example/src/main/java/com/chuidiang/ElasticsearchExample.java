package com.chuidiang;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.IndexOperation;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
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
        createIndex();
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
        removeIndex();
        closeConnection();
    }

    private static void closeConnection() {
        try {
            transport.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void removeIndex() {
        try {
            DeleteIndexRequest dir = new DeleteIndexRequest.Builder().index(PRODUCTS).build();

            elasticsearchClient.indices().delete(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createIndex() {
        try {
            CreateIndexRequest cir = new CreateIndexRequest.Builder()
                    .index(PRODUCTS)
                    .build();

            elasticsearchClient.indices().create(cir);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ElasticsearchException e){
            e.printStackTrace();
        }
    }

    private static void deleteById() {
        System.out.println("Deleting");
        try {
            DeleteRequest dr = new DeleteRequest.Builder()
                    .index(PRODUCTS)
                    .id("1")
                    .build();

            DeleteResponse delete = elasticsearchClient.delete(dr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Borrado!!");
    }

    private static void insertMultiple() {
        System.out.println("Insert multiple");
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("1","Uno", 1));
        productList.add(new Product("2","Dos", 2));
        productList.add(new Product("3","Tres", 3));
        productList.add(new Product("4","Cuatro", 1));  // El 1 está a posta para findByQuery().

        BulkRequest.Builder bulkRequest = new BulkRequest.Builder();

        productList.forEach(product -> {
            IndexOperation io = new IndexOperation.Builder<Product>().index(PRODUCTS).document(product).id(product.id()).build();
            BulkOperation bo = (BulkOperation) new BulkOperation.Builder().index(io).build();
            bulkRequest.operations(bo);
        });

        try {
            BulkResponse bulkResponse = elasticsearchClient.bulk(bulkRequest.build());
            if (bulkResponse.errors()){
                System.out.println("Algo va mal en bulk insert");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Inserted Multiple");
    }

    private static void connect() {
        System.out.println("Estableciendo conexión");

        // URL de Elasticsearch
        String elasticsearchUrl = "https://localhost:9200";

        try {
            // Copia del certificado que usa Elasticsearch para crear sus certificados. Está en
            // la instalación de Elasticsearch, carpeta /usr/share/elasticsearch/config/certs/http_ca.crt
            File certFile = new File("src/main/files/http_ca.crt");
            SSLContext sslContext = TransportUtils
                    .sslContextFromHttpCaCrt(certFile);

            // Credenciales para acceso a Elasticsearch. Usuario y Password.
            BasicCredentialsProvider credentialsProvider =  new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials("elastic","prWv9i_2iKn193lA0gS2"));

            // Creación del cliente REST necesario para uso de la API de Elasticsearch
            RestClient restClient = RestClient.builder(HttpHost.create(elasticsearchUrl))
                    .setHttpClientConfigCallback(hc ->
                            hc.setDefaultCredentialsProvider(credentialsProvider)
                                    .setSSLContext(sslContext))
                    .build();
            transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

            // Elasticsearch Cliente. Es la instancia que usaremos para hablar con Elasticsearch.
            elasticsearchClient = new ElasticsearchClient(transport);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Conexión establecida");

    }

    private static void insertSingle() {
        System.out.println("Insertando un elemento");
        Product product = new Product("bk-1", "City bike", 123);

        IndexResponse response = null;
        try {
            IndexRequest ir = new IndexRequest.Builder<Product>()
                    .index(PRODUCTS)
                    .id(product.id())
                    .document(product)
                    .build();
            response = elasticsearchClient.index(ir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Indexed with version " + response.version());
    }

    private static void selectAll(){
        System.out.println("Select All");
        try {
            SearchRequest sr = new SearchRequest.Builder()
                    .index(PRODUCTS)
                    .build();

            SearchResponse<Product> products = elasticsearchClient.search(sr, Product.class);

            products.hits().hits().forEach(product -> System.out.println(product));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void update(){
        System.out.println("Update element");
        Product updatedProduct = new Product(null, "One", null);
        try {
            UpdateRequest ur = new UpdateRequest.Builder<Product, Product>()
                    .index(PRODUCTS)
                    .id("1")
                    .doc(updatedProduct)
                    .build();

            UpdateResponse<Product> response = elasticsearchClient.update(ur, Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Updated!!");
    }

    private static void selectById (){
        System.out.println("Find by id");
        try {
            GetRequest gr = new GetRequest.Builder()
                    .index(PRODUCTS)
                    .id("1")
                    .build();

            GetResponse<Product> productGetResponse = elasticsearchClient.get(gr, Product.class);

            System.out.println(productGetResponse.source());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void selectByQuery (){
        System.out.println("Find by Query");
        try {
            Query q = QueryBuilders
                    .match()
                    .field("value")
                    .query(1)
                    .build()
                    ._toQuery();

            SearchRequest sr = new SearchRequest.Builder()
                    .index(PRODUCTS)
                    .query(q)
                    .build();

            SearchResponse<Product> search = elasticsearchClient.search(sr, Product.class);

            search.hits().hits().forEach(product -> System.out.println(product.source()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void flush(){
        try {
            elasticsearchClient.indices().flush();
            Thread.sleep(500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
