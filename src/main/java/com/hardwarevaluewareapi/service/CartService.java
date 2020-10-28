package com.hardwarevaluewareapi.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.hardwarevaluewareapi.bean.Cart;
import com.hardwarevaluewareapi.bean.Favorite;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;

@Service
public class CartService {
	Firestore fireStore = FirestoreClient.getFirestore(); 
	
	public Cart saveCart(Cart cart) throws IOException {
		String cartId = fireStore.collection("Cart").document().getId().toString();
		cart.setCartId(cartId);
        fireStore.collection("Cart").document(cartId).set(cart);
        return cart;
	}
	public List<Cart> getCart(String userId) throws InterruptedException, ExecutionException {
    	List<Cart> list;
        CollectionReference collectionReference =  fireStore.collection("Cart");
	    Query queryi = collectionReference.whereEqualTo("userId", userId);
        list = queryi.get().get().toObjects(Cart.class);
    	return list;
    }
	public Cart deleteCart(String id) throws InterruptedException, ExecutionException, ResourceNotFoundException {
	    DocumentReference documentReference = fireStore.collection("Cart").document(id);
		Cart cart = documentReference.get().get().toObject(Cart.class);
		if(cart != null)
		documentReference.delete();
    	return cart;
    }
}
