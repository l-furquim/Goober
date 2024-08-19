package back.application.service.product;

import back.adapter.in.web.controller.product.dto.*;
import back.adapter.out.persistence.mapper.product.ProductMapper;
import back.domain.exception.ProductException;
import back.domain.exception.UserException;
import back.domain.model.product.Product;
import back.domain.port.in.ProductService;
import back.domain.port.out.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(CreateProductRequestDto product) {
        if(product.name().isEmpty()){
            throw new ProductException("Voce nao pode criar um produto sem nome !");
        }



        var newProduct = new Product(
                UUID.randomUUID(),
                product.name(),
                product.price(),
                product.productCategories(),
                product.description(),
                product.productImagePath()
        );

        try{
            productRepository.save(newProduct);
        }catch (IllegalArgumentException e){
            throw new ProductException((e.getMessage()));
        }catch(OptimisticLockingFailureException e) {
            throw new UserException((e.getMessage()));
        }
        return newProduct;
    }

    @Override
    public void deleteProduct(DeleteProductRequestDto product) {

        var aProduct = productRepository.findProductById(product.id());

        if(aProduct.isEmpty()){
            throw new ProductException("Nao foi possivel excluir o produto, ele nao existe.");
        }
        try{
            productRepository.delete(aProduct.get());
        }catch (IllegalArgumentException e){
            throw new ProductException(e.getMessage());
        }catch(OptimisticLockingFailureException e) {
            throw new UserException((e.getMessage()));
        }

    }

    @Override
    public void changeProductPrice(ChangeProductPriceRequestDto product) {

        productRepository.changeProductPrice(product);

    }
    @Override
    public void changeProductName(ChangeProductNameRequestDto product) {
        productRepository.changeProductName(product);
    }

    @Override
    public Optional<List<Product>> findProductsByCategories(String categories) {

        var products = productRepository.findProductsByCategories(categories);

        if(products.isEmpty()){
            return Optional.empty();
        }


        return products;
    }
    @Override
    public Optional<List<Product>> findProductsByNameAndPriceFilter(FindProductsByNameAndPriceFilterRequesDto productss){

        var products = productRepository.findProductsByNameAndPriceFilter(productss.name(),BigDecimal.valueOf(productss.price()));

        if(products.isPresent()){
            return products;
        }
        return Optional.empty();
    }
}
