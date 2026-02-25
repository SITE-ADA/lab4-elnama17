package az.edu.ada.wm2.lab4.repository;

import az.edu.ada.wm2.lab4.model.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final Map<UUID, Product> products = new HashMap<>();

    static {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID id4 = UUID.randomUUID();

        products.put(id1, new Product(id1, "Banana", new BigDecimal("0.99"), LocalDate.of(2025, 6, 1)));
        products.put(id2, new Product(id2, "Milk", new BigDecimal("2.49"), LocalDate.of(2025, 3, 12)));
        products.put(id3, new Product(id3, "Bread", new BigDecimal("1.75"), LocalDate.of(2025, 3, 15)));
        products.put(id4, new Product(id4, "Orange", new BigDecimal("6.99"), LocalDate.of(2025, 5, 1)));
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }


    @Override
    public Product save(Product product) {
        if (Objects.isNull(product.getId())) {
            product.setId(UUID.randomUUID());
        }
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void deleteById(UUID id) {
        products.remove(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return products.containsKey(id);
    }

}