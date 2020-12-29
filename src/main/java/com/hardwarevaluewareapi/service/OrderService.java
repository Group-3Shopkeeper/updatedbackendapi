package com.hardwarevaluewareapi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query.Direction;
import com.hardwarevaluewareapi.bean.BuyCartList;
import com.hardwarevaluewareapi.bean.Cart;
import com.hardwarevaluewareapi.bean.Order;
import com.hardwarevaluewareapi.bean.OrderItems;
import com.hardwarevaluewareapi.bean.Product;
import com.hardwarevaluewareapi.bean.PurchaseOrder;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;

@Service
public class OrderService {
	

	public Order saveOrders(Order order) throws IOException, Exception, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		String orderId = firestore.collection("Order").document().getId().toString();
		order.setOrderId(orderId);

		firestore.collection("Order").document(orderId).set(order);

		return order;
	}

	public Order getOrderById(String id) throws Exception, Exception {
		Firestore firestore = FirestoreClient.getFirestore();
		Order order = firestore.collection("Order").document(id).get().get().toObject(Order.class);

		return order;
	}

	public Order deleteOrder(String id) throws Exception, Exception {
		Firestore firestore = FirestoreClient.getFirestore();
		Order order = firestore.collection("Order").document(id).get().get().toObject(Order.class);
		if (order != null) {

			firestore.collection("Order").document(id).delete();
		}
		return order;
	}

	public java.util.List<Order> getOrders(String currentUserId)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Firestore firestore = FirestoreClient.getFirestore();
		java.util.List<Order> list;
		CollectionReference collectionReference = firestore.collection("Order");

		Query query = collectionReference.whereIn("shippingStatus", Arrays.asList("cancelled", "delivered"));

		Query query1 = query.whereEqualTo("userId", currentUserId);

		list = query1.get().get().toObjects(Order.class);

		if (list != null)
			return list;
		else
			throw new ResourceNotFoundException("order not found");
	}

	public List<Order> getPlacedOrder(String currentUserId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Firestore firestore = FirestoreClient.getFirestore();
		List<Order> list;
		CollectionReference collectionReference = firestore.collection("Order");
		
		Query query = collectionReference.whereEqualTo("userId", currentUserId);
		
		Query query2 = query.whereEqualTo("shippingStatus", "Placed");
		
		list = query2.get().get().toObjects(Order.class);
		
		if (list != null) 
			return list;
		else 
		throw new ResourceNotFoundException("Order not Found");	
		
	}

	public ArrayList<PurchaseOrder> getPurchaseOrders(String shopkeeperId)
			throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		ArrayList<PurchaseOrder> purchaseOrdersList = new ArrayList<PurchaseOrder>();
		ApiFuture<QuerySnapshot> apiFuture = firestore.collection("Order").whereEqualTo("shippingStatus", "placed")
				.get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();

		for (QueryDocumentSnapshot document : documentSnapshotList) {
			double totalAmount = 0;
			boolean status = false;
			Order order = document.toObject(Order.class);
			System.out.println(order.getOrderId());
			ArrayList<OrderItems> orderItemList = order.getOrderItem();
			ArrayList<OrderItems> itemList = new ArrayList<>(3);
			for (OrderItems orderItems : orderItemList) {
				if (orderItems.getShopKeeperId().equals(shopkeeperId)) {
					status = true;
					totalAmount = totalAmount + (orderItems.getPrice() * orderItems.getQty());
					itemList.add(orderItems);
				}
			}
			if (status) {
				PurchaseOrder pOrder = new PurchaseOrder();
				pOrder.setOrderDate(order.getDate());
				pOrder.setOrderId(order.getOrderId());
				pOrder.setTotalAmount(totalAmount);
				pOrder.setOrderItemList(itemList);
				purchaseOrdersList.add(pOrder);
				status = false;
			}

		}

		return purchaseOrdersList;

	}

	public ArrayList<PurchaseOrder> getNewPurchaseOrders(String shopKeeperId)
			throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		ArrayList<PurchaseOrder> newPurchaseOrdersList = new ArrayList<PurchaseOrder>();
		ApiFuture<QuerySnapshot> apiFuture = firestore.collection("Order").whereEqualTo("shippingStatus", "on the way")
				.get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();

		for (QueryDocumentSnapshot document : documentSnapshotList) {
			double totalAmount = 0;
			boolean status = false;
			Order order = document.toObject(Order.class);
			System.out.println(order.getOrderId());
			ArrayList<OrderItems> orderItemList = order.getOrderItem();
			ArrayList<OrderItems> itemList = new ArrayList<>(3);
			for (OrderItems orderItems : orderItemList) {
				if (orderItems.getShopKeeperId().equals(shopKeeperId)) {
					status = true;
					totalAmount = totalAmount + (orderItems.getPrice() * orderItems.getQty());
					itemList.add(orderItems);
				}
			}
			if (status) {
				PurchaseOrder pOrder = new PurchaseOrder();
				pOrder.setOrderDate(order.getDate());
				pOrder.setOrderId(order.getOrderId());
				pOrder.setTotalAmount(totalAmount);
				pOrder.setOrderItemList(itemList);
				newPurchaseOrdersList.add(pOrder);
				status = false;
			}

		}

		return newPurchaseOrdersList;

	}

	public BuyCartList setQtyOfEachProduct(BuyCartList list) throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		List<Cart> cartList = new ArrayList<>();
		List<Cart> list2 = list.getList();
		
		for (Cart cart : list2){
			String productId = cart.getProductId();
			Product product = firestore.collection("Product").document(productId).get().get().toObject(Product.class);
			
			cart.setQtyInStock(product.getQtyInStock());
			cartList.add(cart);
		}
		
		list.setList(cartList);
		
		return list;
	}

}
