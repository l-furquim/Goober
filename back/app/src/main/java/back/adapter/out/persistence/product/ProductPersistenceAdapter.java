package back.adapter.out.persistence.product;

import back.adapter.in.web.controller.product.dto.ChangeProductNameRequestDto;
import back.adapter.in.web.controller.product.dto.ChangeProductPriceRequestDto;
import back.adapter.out.persistence.mapper.product.ProductMapper;
import back.adapter.out.persistence.repository.product.ProductJpaRepository;
import back.application.service.product.ProductServiceImpl;
import back.domain.enums.ProductCategories;
import back.domain.model.product.Product;
import back.domain.port.out.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductPersistenceAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductPersistenceAdapter(ProductJpaRepository productJpaRepository, ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper = productMapper;
    }


    @Override
    public void save(Product product) {

        log.info(product.getProductName(), product.getProductPrice().toString());

        var productEntity = productMapper.toEntity(product);

        productJpaRepository.save(productEntity.get());
    }

    @Override
    public void delete(Product product) {
        var productEntity = productJpaRepository.findById(product.getProductId());

        productJpaRepository.delete(productEntity.get());
    }

    @Override
    public Optional<Product> findProductById(String id) {
        var prod = productJpaRepository.findById(UUID.fromString(id));

        if(prod.isEmpty()){
            return Optional.empty();
        }

        return productMapper.toDomain(prod.get());
    }

    @Override
    public Optional<List<Product>> findProductsByName(String name) {
        var prodl = productJpaRepository.findProductsByProductName(name).get();

        if(prodl.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(prodl.stream().map(prod -> productMapper.toDomain(prod).get()).toList());
    }

    @Override
    public Optional<List<Product>> findProductsByNameAndPriceFilter(String name, BigDecimal price) {
        var prod = productJpaRepository.findProductByNameAndPriceFilter(name,price);

       if(prod.isEmpty()){
           return Optional.empty();
       }
        return Optional.of(prod.get().stream().map(prods -> productMapper.toDomain(prods).get()).toList());
    }

    @Override
    public Optional<List<Product>> findProductsByCategories(String categories) {

        var cate = ProductCategories.fromNome(categories);

        var products = productJpaRepository.findProductsByCategorie(cate);

        if(products.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(products.get().stream().map(prods -> productMapper.toDomain(prods).get()).toList());
    }

    @Transactional
    @Override
    public void changeProductPrice(ChangeProductPriceRequestDto productPriceRequestDto) {
        var product = productJpaRepository.findById(UUID.fromString(productPriceRequestDto.id()));


       if(product.isPresent()){
           var doublePrice = Double.valueOf(productPriceRequestDto.newPrice().toString());

           product.get().setProductEntityPrice(BigDecimal.valueOf(doublePrice));
       }
    }

    @Transactional
    @Override
    public void changeProductName(ChangeProductNameRequestDto productNameRequestDto) {
        var product = productJpaRepository.findById(UUID.fromString(productNameRequestDto.id()));

        if(product.isPresent()){
            product.get().setProductEntityName(productNameRequestDto.newName());
        }

    }
}
