package com.hardwarevaluewareapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.hardwarevaluewareapi.bean.Product;
import com.hardwarevaluewareapi.bean.ProductName;

@Service
public class ProductNameService {
	
	public ProductName saveProductName(ProductName name) {
		Firestore fireStore = FirestoreClient.getFirestore(); 
		String productId = fireStore.collection("ProductName").document().getId().toString();
		fireStore.collection("ProductName").document(productId).set(name);
		return name;
	}
	public List<ProductName> getProductNameByCategory(String categoryId) throws InterruptedException, ExecutionException {
    	Firestore fireStore = FirestoreClient.getFirestore(); 
    	List<ProductName> list;
    
        CollectionReference collectionReference =  fireStore.collection("ProductName");
	    Query queryi = collectionReference.whereEqualTo("categoryId", categoryId);
        list = queryi.get().get().toObjects(ProductName.class);
    	return list;
    	
	}
    


}
