package com.hardwarevaluewareapi.service;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.firebase.cloud.FirestoreClient;
import com.hardwarevaluewareapi.bean.Cart;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;

@Service
public class CartService {
	
	
	public Cart saveCart(Cart cart) throws IOException {
		Firestore fireStore = FirestoreClient.getFirestore(); 
		String cartId = fireStore.collection("Cart").document().getId().toString();
		cart.setCartId(cartId);
        fireStore.collection("Cart").document(cartId).set(cart);
        return cart;
	}
	public List<Cart> getCart(String userId) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore(); 
    	List<Cart> list;
        CollectionReference collectionReference =  fireStore.collection("Cart");
	    Query queryi = collectionReference.whereEqualTo("userId", userId);
        list = queryi.get().get().toObjects(Cart.class);
    	return list;
    }
	public Cart deleteCart(String id) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Firestore fireStore = FirestoreClient.getFirestore(); 
	    DocumentReference documentReference = fireStore.collection("Cart").document(id);
		Cart cart = documentReference.get().get().toObject(Cart.class);
		if(cart != null)
		documentReference.delete();
    	return cart;
    }
}
