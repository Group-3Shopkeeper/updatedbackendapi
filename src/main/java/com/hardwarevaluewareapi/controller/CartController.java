package com.hardwarevaluewareapi.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hardwarevaluewareapi.bean.Cart;
import com.hardwarevaluewareapi.bean.Favorite;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;
import com.hardwarevaluewareapi.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartservice;
	
	@PostMapping("/")
    public Cart saveCart(@RequestBody Cart cart) throws Exception{
		   return cartservice.saveCart(cart);
	}
	@GetMapping("/{userId}")
	public ResponseEntity<List<Cart>> getFavourite(@PathVariable String userId) throws ResourceNotFoundException, InterruptedException, ExecutionException {
		List<Cart> cartList = cartservice.getCart(userId);
		if(cartList!=null)
		{
			return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);	
		}
		else
		{
			throw new ResourceNotFoundException("Product Not Found");
		}	
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCart(@PathVariable String id) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Cart cart =  cartservice.deleteCart(id);
		if(cart == null) {
			throw new ResourceNotFoundException("Not found "+id);
		}
		else
		  return new ResponseEntity<Cart>(cart,HttpStatus.OK);
		  
	}
}
