package com.hardwarevaluewareapi.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.hardwarevaluewareapi.SaveImage;
import com.hardwarevaluewareapi.bean.Cart;
import com.hardwarevaluewareapi.bean.Product;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;

@Service
public class ProductService {
	
	Firestore fireStore = FirestoreClient.getFirestore(); 
	public Product saveProduct(MultipartFile file, Product product) throws IOException {
	    String imageUrl = new SaveImage().sendImage(file);
        String productId = fireStore.collection("Product").document().getId().toString();
        product.setImageUrl(imageUrl);
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        product.setTimestamp(timestamp.getTime());
        product.setProductId(productId);
        
        fireStore.collection("Product").document(productId).set(product);
        return product;
	}
    public Product getProduct(String productId) throws InterruptedException, ExecutionException {
    	Product product =  fireStore.collection("Product").document(productId).get().get().toObject(Product.class);
	   	return product;
	}
	public Product updateProduct(MultipartFile file, Product product) throws IOException, InterruptedException, Exception {
		  String imageUrl  = new SaveImage().sendImage(file);
	      java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
	      product.setTimestamp(timestamp.getTime());
	      product.setProductId(product.getProductId());
	      product.setImageUrl(imageUrl);
	      fireStore.collection("Product").document(product.getProductId()).set(product);
	      return product;
	}
    public Product deleteProduct(String productId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		DocumentReference documentReference = fireStore.collection("Product").document(productId);
		Product product = documentReference.get().get().toObject(Product.class);
		documentReference.delete();
    	return product;
    }
    public ArrayList<Product> getDiscountedProduct() throws InterruptedException, ExecutionException {
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
    	List<Product> list;
        CollectionReference collectionReference =  fireStore.collection("Product");
	    Query queryi = collectionReference.orderBy("timestamp", Direction.DESCENDING).limit(10);
        list = queryi.get().get().toObjects(Product.class);
    	return list;
    }
    public List<Product> getProductByCategory(String categoryId) throws InterruptedException, ExecutionException {
    	List<Product> list;
        CollectionReference collectionReference =  fireStore.collection("Product");
	    Query queryi = collectionReference.whereEqualTo("categoryId", categoryId);
        list = queryi.get().get().toObjects(Product.class);
    	return list;
    }
    public Product sendImage(MultipartFile file, Product product) throws IOException {
		  
		  InputStream serviceAccount=this.getClass().getClassLoader().getResourceAsStream("./serviceAccountKey.json");
		  Storage storage=StorageOptions.newBuilder().setProjectId("hardwarevalueapi")
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build().getService();
		  
		  HashMap<String,String> hm=new HashMap<>();
		  hm.put("firebaseStorageDownloadTokens", "3434434343434dfdf");
		  BlobId blobid=BlobId.of("hardwarevalueapi.appspot.com", file.getOriginalFilename());
		  BlobInfo blobInfo=BlobInfo.newBuilder(blobid).setContentType("image/jpeg").setMetadata(hm).build();
		  
		  File convertedFile=new File(file.getOriginalFilename());
		  FileOutputStream fos=new FileOutputStream(convertedFile);
		  fos.write(file.getBytes());
		  fos.close();
		  
		  Blob blob =storage.create(blobInfo,Files.readAllBytes(convertedFile.toPath()));
		  Bucket bucket = StorageClient.getInstance().bucket("hardwarevalueapi.appspot.com");
		 
		  String imageUrl = "https://firebasestorage.googleapis.com/v0/b/hardwarevalueapi.appspot.com/o/"+convertedFile+"?alt=media&token=3434434343434dfdf";
	      System.out.println("Image Url : "+imageUrl);
	      product.setImageUrl(imageUrl);
		  return product;
	}
}
