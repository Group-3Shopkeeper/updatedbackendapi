package com.hardwarevaluewareapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardwarevaluewareapi.bean.Product;
import com.hardwarevaluewareapi.bean.ProductName;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;
import com.hardwarevaluewareapi.service.ProductNameService;

@RestController
@RequestMapping("/productName")
public class ProductNameController {
	
	@Autowired
	private ProductNameService productNameService;
	
	@PostMapping("/")
    public ResponseEntity<?> saveProductName(@RequestBody ProductName name) throws Exception{
	     ProductName p=productNameService.saveProductName(name);
	   	 return new ResponseEntity<ProductName>(p,HttpStatus.OK);
    }
	
	@GetMapping("/c/{categoryId}")
	public ResponseEntity<List<ProductName>> getProductNameByCategory(@PathVariable String categoryId) throws ResourceNotFoundException, InterruptedException, ExecutionException {
		List<ProductName> productList = productNameService.getProductNameByCategory(categoryId);
		if(productList!=null)
		{
			return new ResponseEntity<List<ProductName>>(productList, HttpStatus.OK);	
		}
		else
		{
			throw new ResourceNotFoundException("Product Not Found");
		}	
	}


}
