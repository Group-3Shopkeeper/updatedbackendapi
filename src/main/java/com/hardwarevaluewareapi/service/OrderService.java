package com.hardwarevaluewareapi.service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;
import com.google.api.core.ApiFuture;
import com.google.api.services.storage.Storage.BucketAccessControls.List;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Query;
import com.hardwarevaluewareapi.bean.Order;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;

@Service
public class OrderService {
	Firestore firestore = FirestoreClient.getFirestore();

	public Order saveOrders(Order order) throws IOException, Exception, ExecutionException {

		String orderId = firestore.collection("Order").document().getId().toString();
		order.setOrderId(orderId);

		firestore.collection("Order").document(orderId).set(order);

		return order;
	}

	public Order getOrderById(String id) throws Exception, Exception {
		Order order = firestore.collection("Order").document(id).get().get().toObject(Order.class);

		return order;
	}

	public Order deleteOrder(String id) throws Exception, Exception {
		Order order = firestore.collection("Order").document(id).get().get().toObject(Order.class);
		if (order != null) {

			firestore.collection("Order").document(id).delete();
		}
		return order;
	}

	public java.util.List<Order> getOrders(String currentUserId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		java.util.List<Order> list;
		CollectionReference collectionReference = firestore.collection("Order");
		
		Query query = collectionReference.whereIn("shippingStatus",Arrays.asList("cancelled","delivered"));
		
		Query query1 = query.whereEqualTo("userId", currentUserId);
		
		list = query1.get().get().toObjects(Order.class);
		
		if(list != null)
			return list;
		else
			throw new ResourceNotFoundException("order not found"); 
	}

	public java.util.List<Order> getAllOrders(String currentUserId, String status) throws ResourceNotFoundException, InterruptedException, ExecutionException {
		
		java.util.List<Order> list;
		
		CollectionReference collectionReference = firestore.collection("Order");
		
		Query query = collectionReference.whereEqualTo("userId", currentUserId);
		
		Query query1 = query.whereEqualTo("shippingStatus", status);
		
		list = query1.get().get().toObjects(Order.class);
		if(list != null)
			return list;
		else
			throw new ResourceNotFoundException("order not found");
	}



}
