 package com.hardwarevaluewareapi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.hardwarevaluewareapi.SaveImage;
import com.hardwarevaluewareapi.bean.Product;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;

@Service
public class ProductService {
	
	
	public Product storeProduct(List<MultipartFile> files,Product p) throws IOException {
		Firestore fireStore = FirestoreClient.getFirestore(); 
	    String productId = fireStore.collection("Product").document().getId().toString();
	    p.setProductId(productId);
	    System.out.println("Size of list : "+files.size());
		if(files.size() == 1) {
			String firstUrlString = new SaveImage().sendImage(files.get(0));
			p.setImageUrl(firstUrlString);
		}
		if(files.size()==2) {
			String firstUrlString = new SaveImage().sendImage(files.get(0));
			p.setImageUrl(firstUrlString);
			String secondImageUrl=new SaveImage().sendImage(files.get(1));
			p.setSecondImageUrl(secondImageUrl);
		}
		if (files.size()==3) {
			String firstUrlString = new SaveImage().sendImage(files.get(0));
			p.setImageUrl(firstUrlString);
			String secondImageUrl=new SaveImage().sendImage(files.get(1));
			p.setSecondImageUrl(secondImageUrl);
			String thirdImageUrl=new SaveImage().sendImage(files.get(2));
			p.setThirdImageurl(thirdImageUrl);
		}
			
			
		
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        p.setTimestamp(timestamp.getTime());
              
        fireStore.collection("Product").document(productId).set(p);
        return p;
	}
	
	
    public Product getProduct(String productId) throws InterruptedException, ExecutionException {
    	Firestore fireStore = FirestoreClient.getFirestore(); 
    	Product product =  fireStore.collection("Product").document(productId).get().get().toObject(Product.class);
	   	return product;
	}
    public Product updateProduct(MultipartFile file, Product product)
			throws IOException, InterruptedException, Exception {
    	Firestore fireStore = FirestoreClient.getFirestore(); 
		String imageUrl = new SaveImage().sendImage(file);
		java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		product.setTimestamp(timestamp.getTime());
		product.setProductId(product.getProductId());
		ArrayList<String> imageUrlList = new ArrayList<>();
		imageUrlList.add(imageUrl);
		fireStore.collection("Product").document(product.getProductId()).set(product);
		return product;
	}
	public Product updateProductWithoutImage(Product product) throws IOException, InterruptedException, Exception {
		Firestore fireStore = FirestoreClient.getFirestore(); 
		java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		product.setTimestamp(timestamp.getTime());
		fireStore.collection("Product").document(product.getProductId()).set(product);
		return product;
	}

    public Product deleteProduct(String productId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
    	Firestore fireStore = FirestoreClient.getFirestore(); 
		DocumentReference documentReference = fireStore.collection("Product").document(productId);
		Product product = documentReference.get().get().toObject(Product.class);
		documentReference.delete();
    	return product;
    }
    public List<Product> getDiscountedProduct() throws InterruptedException, ExecutionException {
    	Firestore fireStore = FirestoreClient.getFirestore(); 
		ArrayList<Product> pl = new ArrayList<Product>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Product").orderBy("timestamp", Direction.DESCENDING).limit(10).get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshotList) {
			Product product = document.toObject(Product.class);
			double discount = product.getDiscount();
			if (discount > 0) {
				pl.add(product);
			}
		}
		return pl;
	}
    public List<Product> getRecentProduct() throws InterruptedException, ExecutionException {
    	Firestore fireStore = FirestoreClient.getFirestore(); 
    	List<Product> list;
        CollectionReference collectionReference =  fireStore.collection("Product");
	    Query queryi = collectionReference.orderBy("timestamp", Direction.DESCENDING).limit(10);
        list = queryi.get().get().toObjects(Product.class);
    	return list;
    }
    public List<Product> getProductByCategory(String categoryId) throws InterruptedException, ExecutionException {
    	Firestore fireStore = FirestoreClient.getFirestore(); 
    	List<Product> list;
        CollectionReference collectionReference =  fireStore.collection("Product");
	    Query queryi = collectionReference.whereEqualTo("categoryId", categoryId);
        list = queryi.get().get().toObjects(Product.class);
    	return list;
    }
    public List<Product> getProductByName(String productName) throws InterruptedException, ExecutionException {
    	Firestore fireStore = FirestoreClient.getFirestore(); 
    	List<Product> list;
        CollectionReference collectionReference =  fireStore.collection("Product");
	    Query queryi = collectionReference.whereEqualTo("name",productName);
        list = queryi.get().get().toObjects(Product.class);
    	return list;
    }
    
public List<Product> searchProductByName(String shopkeeperId,String name) throws InterruptedException, ExecutionException {
	Firestore fireStore = FirestoreClient.getFirestore(); 
		
		ArrayList<Product> pl = new ArrayList<Product>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Product").whereEqualTo("shopKeeperId",shopkeeperId).get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshotList) {
			
			    Product product = document.toObject(Product.class);
				name = name.toLowerCase();
				String doc = product.getName().toLowerCase();
				if (doc.contains(name)) {
					pl.add(product);
				}
			
		}
		return pl;
	}
	public List<Product> getProductByCategoryAndShopkeeper(String categoryId, String shopKeeperId) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore(); 
		
		ArrayList<Product> list = new ArrayList<Product>() ;
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Product").whereEqualTo("shopKeeperId",shopKeeperId).get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshotList) {
			Product product = document.toObject(Product.class);
			if(categoryId.equals(product.getCategoryId())){
				list.add(product);
			}
		}
    	return list;
	}


	public Product updateProductImage( List<MultipartFile> files, String productId,String urlFirst,String urlSecond,String urlThird) throws IOException, InterruptedException, ExecutionException {
		Firestore firestore=FirestoreClient.getFirestore();
		Product p = firestore.collection("Product").document(productId).get().get().toObject(Product.class);
		if(files.size() == 1) {
			if(urlFirst.equals("Selected")) {
				String firstUrlString = new SaveImage().sendImage(files.get(0));
				p.setImageUrl(firstUrlString);	
			 }
			if(urlSecond.equals("Selected")) {
				String secondImageUrl= new SaveImage().sendImage(files.get(0));
				p.setSecondImageUrl(secondImageUrl);	
			 }
			if(urlThird.equals("Selected")) {
				String thirdImageUrl = new SaveImage().sendImage(files.get(0));
				p.setThirdImageurl(thirdImageUrl);	
			 }
		}
		if(files.size()==2) {
			if(urlFirst.equals("Selected") && urlSecond.equals("Selected")) {
				String firstUrlString = new SaveImage().sendImage(files.get(0));
				p.setImageUrl(firstUrlString);	

				String secondImageUrl= new SaveImage().sendImage(files.get(1));
				p.setSecondImageUrl(secondImageUrl);	
			 }
			else if(urlSecond.equals("Selected") && urlThird.equals("Selected")) {
				String thirdImageUrl = new SaveImage().sendImage(files.get(1));
				p.setThirdImageurl(thirdImageUrl);	
			
				
				String secondImageUrl= new SaveImage().sendImage(files.get(0));
				p.setSecondImageUrl(secondImageUrl);	
			 }
			else if(urlThird.equals("Selected") && urlFirst.equals("Selected")) {
				String firstUrlString = new SaveImage().sendImage(files.get(0));
				p.setImageUrl(firstUrlString);	

				String thirdImageUrl = new SaveImage().sendImage(files.get(1));
				p.setThirdImageurl(thirdImageUrl);	
			 }
			
		}
		if (files.size()==3) {
			if(urlFirst.equals("Selected")) {
				String firstUrlString = new SaveImage().sendImage(files.get(0));
				p.setImageUrl(firstUrlString);	
			 }
			if(urlSecond.equals("Selected")) {
				String secondImageUrl= new SaveImage().sendImage(files.get(1));
				p.setSecondImageUrl(secondImageUrl);	
			 }
			if(urlThird.equals("Selected")) {
				String thirdImageUrl = new SaveImage().sendImage(files.get(2));
				p.setThirdImageurl(thirdImageUrl);	
			 }
			
		}
		firestore.collection("Product").document(productId).set(p);
		return p;
	}
	
}
