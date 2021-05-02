package com.api.rc_paradise_api.service;

import com.api.rc_paradise_api.model.Product;
import com.api.rc_paradise_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // saving new product
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    // get all products
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // search product by name or category
    public List<Product> searchProducts(String query) {
        return repository.searchQuery(query);
    }

    // search product by seller and query
    public List<Product> searchProductsBySeller(String sellerId, String query) {
        return repository.searchProductsBySeller(sellerId, query);
    }

    // get product by id
    public Product getProduct(Long id) {
        return repository.findById(id).get();
    }

    // delete product by id
    public String deleteProduct(Long id) {
        repository.deleteById(id);
        return "product with id: " + id + " deleted";
    }

    // compress uploading image
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }

        try {
            outputStream.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }


    // decompress downloading image
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (DataFormatException e) {
            System.out.println(e.getMessage());
        }

        return outputStream.toByteArray();
    }


}
