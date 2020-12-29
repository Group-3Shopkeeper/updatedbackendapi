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
	
	
	public Favorite saveFavorite(Favorite favorite) throws IOException {
		Firestore fireStore = FirestoreClient.getFirestore();
		String favoriteId = fireStore.collection("Favourite").document().getId().toString();
		favorite.setFavoriteId(favoriteId);
        fireStore.collection("Favourite").document(favoriteId).set(favorite);
        return favorite;
	}
	public List<Favorite> getFavourite(String userId) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
    	List<Favorite> list;
    
        CollectionReference collectionReference =  fireStore.collection("Favourite");
	    Query queryi = collectionReference.whereEqualTo("userId", userId);
        list = queryi.get().get().toObjects(Favorite.class);
    	return list;
    }
	public Favorite deleteFavorite(String favouriteId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Firestore fireStore = FirestoreClient.getFirestore(); 
			DocumentReference documentReference = fireStore.collection("Favourite").document(favouriteId);
			Favorite favorite = documentReference.get().get().toObject(Favorite.class);
			if(favorite != null)
			documentReference.delete();
	    	return favorite;
	  }
	public List<Favorite> getFavoriteByCategory(String currentUserId, String categoryId) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		List<Favorite> list;
		CollectionReference collectionReference = fireStore.collection("Favourite");
		Query query = collectionReference.whereEqualTo("userId", currentUserId);
		Query query2 = query.whereEqualTo("categoryId", categoryId);

		list = query2.get().get().toObjects(Favorite.class);
		return list;
	}
}
