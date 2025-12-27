package ma.formation.rest.service;
import java.util.List;
import ma.formation.rest.service.model.product;

public interface IProductService {
    product getById(Long id);
    List<product> getAll();
    void create(product product);
    void update(Long id, product product);
    void delete(Long id);
}
