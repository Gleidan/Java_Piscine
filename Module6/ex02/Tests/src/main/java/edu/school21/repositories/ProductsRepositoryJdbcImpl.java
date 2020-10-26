package edu.school21.repositories;

import edu.school21.models.Product;
import javax.sql.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private DataSource ds;

    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void update(Product product) {
        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE product "
                                                                        + "SET name = (?), price = (?) "
                                                                        + "WHERE id = (?);");

            if (product.getName() == null) {
                statement.setNull(1, Types.VARCHAR);
            }
            else {
                statement.setString(1, product.getName());
            }
            if (product.getPrice() == null) {
                statement.setNull(2, Types.BIGINT);
            }
            else {
                statement.setLong(2, product.getPrice());
            }

            statement.setLong(3, product.getId());

            statement.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void save(Product product) {
        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO"
                                                                        + " product (id, name, price)"
                                                                        + "  VALUES (DEFAULT, (?), (?));");

            statement.setString(1, product.getName());
            statement.setLong(2, product.getPrice());

            statement.executeUpdate();

            statement = connection.prepareStatement("SELECT id FROM product WHERE name = (?) AND price = (?)");

            statement.setString(1, product.getName());
            statement.setLong(2, product.getPrice());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                product.setId(resultSet.getLong("id"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = new Product();

        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE id = (?);");

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                product.setId(id);
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getLong("price"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Optional<Product> optionalProduct = Optional.of(product);

        return Optional.of(product);
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE id = (?);");

            statement.setLong(1, id);

            statement.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> allProduct = new LinkedList<>();

        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product;");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product tempProduct = new Product();
                tempProduct.setId(resultSet.getLong("id"));
                tempProduct.setName(resultSet.getString("name"));
                tempProduct.setPrice(resultSet.getLong("price"));
                allProduct.add(tempProduct);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allProduct;
    }
}
