package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.jdbc.datasource.embedded.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryJdbcImplTest {

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(4L, "Iphone 12", 109800L);

    final Product EXPECTED_SAVED_PRODUCT = new Product(5L, "Xiaomi Mi 10", 500L);

    final Product EXPECTED_UPDATED_PRODUCT = new Product(2L, "Xiaomi Mi 10", 500L);

    final Product EXPECTED_UPDATED_NULL_PRODUCT = new Product(2L, null, 0L);

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new LinkedList() {{
        add(new Product(0L, "Iphone SE", 34300L));
        add(new Product(1L, "Iphone X", 67800L));
        add(new Product(2L, "Iphone XR", 112300L));
        add(new Product(3L, "Iphone 11", 87000L));
        add(new Product(4L, "Iphone 12", 109800L)); }};

    EmbeddedDatabase db;
    ProductsRepositoryJdbcImpl productRepo;

    @BeforeEach
    void init() {
        db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        productRepo = new ProductsRepositoryJdbcImpl(db);
    }

    @Test
    void testConnection() {
        try {
            Connection connection = db.getConnection();
            Assertions.assertNotNull(connection);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findByIdTest() {
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productRepo.findById(4L).get());
    }

    @Test
    void testSave() {
        Product product = new Product(null, "Xiaomi Mi 10", 500L);

        productRepo.save(product);

        assertEquals(EXPECTED_SAVED_PRODUCT, productRepo.findById(5L).get());
    }

    @Test
    void testUpdate() {
        Product product = new Product(2L, "Xiaomi Mi 10", 500L);

        productRepo.update(product);

        assertEquals(EXPECTED_UPDATED_PRODUCT, productRepo.findById(2L).get());
    }

    @Test
    void testNullUpdate() {
        Product product = new Product(2L, null, null);

        productRepo.update(product);

        assertEquals(EXPECTED_UPDATED_NULL_PRODUCT, productRepo.findById(2L).get());
    }

    @Test
    void testDelete() {
        productRepo.delete(4L);

        assertNotEquals(EXPECTED_FIND_BY_ID_PRODUCT, productRepo.findById(4L).get());
    }

    @Test
    void testFindAll() {
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productRepo.findAll());
    }

    @AfterEach
    void shutdown() {
        db.shutdown();
    }
}
