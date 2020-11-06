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
import com.hardwarevaluewareapi.bean.Favorite;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;

@Service
public class FavoriteService {
	
	Firestore fireStore = FirestoreClient.getFirestore();
	public Favorite saveFavorite(Favorite favorite) throws IOException {
		String favoriteId = fireStore.collection("Favourite").document().getId().toString();
		favorite.setFavoriteId(favoriteId);
        fireStore.collection("Favourite").document(favoriteId).set(favorite);
        return favorite;
	}
	public List<Favorite> getFavourite(String userId) throws InterruptedException, ExecutionException {
    	List<Favorite> list;
    	Firestore fireStore = FirestoreClient.getFirestore(); 
        CollectionReference collectionReference =  fireStore.collection("Favourite");
	    Query queryi = collectionReference.whereEqualTo("userId", userId);
        list = queryi.get().get().toObjects(Favorite.class);
    	return list;
    }
	public Favorite deleteFavorite(String favouriteId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		     
			DocumentReference documentReference = fireStore.collection("Favourite").document(favouriteId);
			Favorite favorite = documentReference.get().get().toObject(Favorite.class);
			if(favorite != null)
			documentReference.delete();
	    	return favorite;
	  }
}
