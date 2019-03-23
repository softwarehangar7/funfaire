package com.funfaire.domain.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(final String id) {
        final Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException(String.format("Product with id '%s' not found", id));
    }

    public Product save(final Product product) {
        final Product newProduct = productRepository.save(product);
        productOptionRepository.saveAll(product.getOptions());
        return newProduct;
    }

    public List<Product> saveAll(final Iterable<Product> products) {
    	final List<Product> newProducts = productRepository.saveAll(products);
    	for (final Product product : newProducts) {
			productOptionRepository.saveAll(product.getOptions());
		}
    	
    	return newProducts;
    }
    
    
    public void deleteProductById(final String id) {
        final Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(String.format("Cannot delete product id=%s. Product not found", id));
        }
    }
	
}
