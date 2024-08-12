package back.application.service.product;

import back.adapter.in.web.controller.product.dto.ChangeProductNameRequestDto;
import back.adapter.in.web.controller.product.dto.ChangeProductPriceRequestDto;
import back.adapter.in.web.controller.product.dto.CreateProductRequestDto;
import back.adapter.in.web.controller.product.dto.DeleteProductRequestDto;
import back.domain.exception.ProductException;
import back.domain.exception.UserException;
import back.domain.model.product.Product;
import back.domain.port.in.ProductService;
import back.domain.port.out.ProductRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductServiceImpl(){

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

    @Transactional
    @Override
    public void changeProductPrice(ChangeProductPriceRequestDto product) {
        var aProduct = productRepository.findProductById(product.id());

        if(aProduct.isEmpty()){
            throw new ProductException("Nao foi possivel alterar o preço, o produto nao existe.");
        }

        aProduct.get().setProductPrice(BigDecimal.valueOf(product.newPrice()));
    }
    @Transactional
    @Override
    public void changeProductName(ChangeProductNameRequestDto product) {

        var aProduct = productRepository.findProductById(product.id());

        if(aProduct.isEmpty()){
            throw new ProductException("Nao foi possivel alterar o nome, o produto nao existe.");
        }

        aProduct.get().setProductName(product.newName());
    }
}
