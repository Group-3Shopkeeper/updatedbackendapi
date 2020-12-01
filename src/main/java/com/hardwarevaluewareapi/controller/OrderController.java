package com.hardwarevaluewareapi.controller;

import java.util.ArrayList;

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

import com.google.api.services.storage.Storage.BucketAccessControls.List;
import com.hardwarevaluewareapi.bean.Order;
import com.hardwarevaluewareapi.bean.PurchaseOrder;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;
import com.hardwarevaluewareapi.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;

	@PostMapping("/")
	public ResponseEntity<?> createOrder(@RequestBody Order order) throws Exception {
		Order o = orderService.saveOrders(order);
		return new ResponseEntity<>(o, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable("id") String id) throws Exception {
		Order order = orderService.getOrderById(id);
		if (order != null)
			return new ResponseEntity<Order>(order, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("order not found for this id : " + id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable("id") String id) throws Exception {
		Order order = orderService.deleteOrder(id);
		if (order != null)
			return new ResponseEntity<Order>(order, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("order not found");
	}
	
	@GetMapping("/history/{currentUserId}")
	public ResponseEntity<java.util.List<Order>> getOrders (@PathVariable ("currentUserId") String currentUserId) throws Exception{
		//Order order = orderService.getOrders(currentUserId);
		java.util.List<Order> orderList = (java.util.List<Order>) orderService.getOrders(currentUserId);
		if(orderList != null)
			return new ResponseEntity<java.util.List<Order>>(orderList, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Order not found");
	}
	
	@GetMapping("/orderHistory/{shopKeeperId}")
	public ResponseEntity<ArrayList<PurchaseOrder>> getPurchaseOrder (@PathVariable ("shopKeeperId") String shopKeeperId) throws Exception{
		ArrayList<PurchaseOrder> orderList = orderService.getPurchaseOrders(shopKeeperId);
		if(orderList != null)
			return new ResponseEntity<ArrayList<PurchaseOrder>>(orderList, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Order not found");
	}
	@GetMapping("/newOrder/{shopKeeperId}")
	public ResponseEntity<ArrayList<PurchaseOrder>> getNewPurchaseOrder (@PathVariable ("shopKeeperId") String shopKeeperId) throws Exception{
		ArrayList<PurchaseOrder> orderList = orderService.getNewPurchaseOrders(shopKeeperId);
		if(orderList != null)
			return new ResponseEntity<ArrayList<PurchaseOrder>>(orderList, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Order not found");
	}
	
}
