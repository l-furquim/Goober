package back.domain.port.in;

import back.adapter.in.web.controller.product.dto.ChangeProductNameRequestDto;
import back.adapter.in.web.controller.product.dto.ChangeProductPriceRequestDto;
import back.adapter.in.web.controller.product.dto.CreateProductRequestDto;
import back.adapter.in.web.controller.product.dto.DeleteProductRequestDto;
import back.domain.model.product.Product;

public interface ProductService {

    Product createProduct(CreateProductRequestDto product);
    void deleteProduct(DeleteProductRequestDto product);
    void changeProductPrice(ChangeProductPriceRequestDto product);
    void changeProductName(ChangeProductNameRequestDto product);


}
