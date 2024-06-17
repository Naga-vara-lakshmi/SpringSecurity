package com.techwave.SpringSecurityDemo.FeignProxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient("ProductService")
public interface ProductProxy {
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) ;
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id);
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts();
}
