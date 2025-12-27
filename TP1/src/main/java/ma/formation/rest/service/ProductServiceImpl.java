package ma.formation.rest.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ma.formation.rest.service.model.product;

@Service
public class ProductServiceImpl implements IProductService {

    private static List<product> productRepo = new ArrayList<>();

    static {
        productRepo.add(new product(1L, "PC PORTABLE HP"));
        productRepo.add(new product(2L, "TV LG 32p"));
        productRepo.add(new product(3L, "TV Sony 49p"));
        productRepo.add(new product(4L, "Camera Sony"));
    }

    @Override
    public product getById(Long id) {
        if (productRepo == null || productRepo.isEmpty())
            return null;
        for (product product : productRepo) {
            if (id.equals(product.getId()))
                return product;
        }
        return null;
    }

    @Override
    public List<product> getAll() {
        return productRepo;
    }

    @Override
    public void update(Long id, product product) {
        product productFound = getById(id);
        if (productFound == null)
            return;
        productRepo.remove(productFound);
        product.setId(id);
        productRepo.add(product);
    }

    @Override
    public void delete(Long id) {
        product productFound = getById(id);
        if (productFound == null)
            return;
        productRepo.remove(productFound);
    }

    @Override
    public void create(product product) {
        productRepo.add(product);
    }
}