package com.hardwarevaluewareapi.service;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.hardwarevaluewareapi.SaveImage;
import com.hardwarevaluewareapi.bean.Shopkeeper;
import com.hardwarevaluewareapi.image.SendImage;

@Service
public class ShopkeeperService {
	      SaveImage sendImage=new SaveImage();
		public Shopkeeper saveStore(MultipartFile file,Shopkeeper shopkeeper) throws IOException {
			  
			 String imageUrl=sendImage.sendImage(file);
			 shopkeeper.setImageUrl(imageUrl);
			 Firestore firestoredatabase=FirestoreClient.getFirestore();
			 firestoredatabase.collection("Store").document(shopkeeper.getShopKeeperId()).set(shopkeeper);
			 return shopkeeper;
	      }
		  
     	public Shopkeeper viewStore(String id) throws InterruptedException, ExecutionException {
		
			 Firestore firestoredatabase=FirestoreClient.getFirestore();
			 Shopkeeper shopkeeper=firestoredatabase.collection("Store").document(id).get().get().toObject(Shopkeeper.class);
			 return shopkeeper;	 
		 }
   	
     	public Shopkeeper updateStore(MultipartFile file, Shopkeeper shopkeeper) throws IOException, InterruptedException, Exception {
    		
			String imageUrl=sendImage.sendImage(file);
		    shopkeeper.setImageUrl(imageUrl);
	        Firestore firestoredatabase=FirestoreClient.getFirestore();
	        firestoredatabase.collection("Store").document(shopkeeper.getShopKeeperId()).set(shopkeeper);
		    return shopkeeper;
		}
		public Shopkeeper updateStoreWithoutImage(Shopkeeper shopkeeper) throws IOException, InterruptedException, Exception {
			Firestore firestoredatabase=FirestoreClient.getFirestore();
	        firestoredatabase.collection("Store").document(shopkeeper.getShopKeeperId()).set(shopkeeper);
		    return shopkeeper;
		}
	
		 public Shopkeeper deleteStore(String id) throws Exception, Exception {
			
			Firestore firestoredatabase=FirestoreClient.getFirestore();
			DocumentReference documentReference=firestoredatabase.collection("Store").document(id);
			Shopkeeper shopkeeper=documentReference.get().get().toObject(Shopkeeper.class);
			if(shopkeeper!=null)
				documentReference.delete();
			
			return shopkeeper;
		}
  }




