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
import com.hardwarevaluewareapi.bean.Comment;
import com.hardwarevaluewareapi.bean.Product;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;

@Service
public class CommentService {
	
	
	
	public Comment saveComment(Comment comment) {
		Firestore fireStore = FirestoreClient.getFirestore(); 
		java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		comment.setTimestamp(timestamp.getTime());
		String commentId = fireStore.collection("Product").document().getId().toString();
		comment.setCommentId(commentId);
		//fireStore.collection("Product").document(comment.getProductId()).collection("Comment").document(commentId).set(comment);
		DocumentReference dReference = fireStore.collection("Product").document(comment.getProductId());
		dReference.collection("Comment").document(commentId).set(comment);
		return comment;
	}
	
	public List<Comment> getProductByComment(String productId) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore(); 
    	List<Comment> list;
        CollectionReference collectionReference =  fireStore.collection("Product");
	    Query queryi = collectionReference.document(productId).collection("Comment");
        list = queryi.get().get().toObjects(Comment.class);
    	return list;
    }

	 public Comment deleteComment(Comment comment) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		 Firestore fireStore = FirestoreClient.getFirestore(); 
			DocumentReference documentReference = fireStore.collection("Product").document(comment.getProductId()).collection("Comment").document(comment.getCommentId());
			Comment c= documentReference.get().get().toObject(Comment.class);
			documentReference.delete();
	    	return c;
	    }
	 public Comment updateComment(Comment comment) throws IOException, InterruptedException, Exception {
		 Firestore fireStore = FirestoreClient.getFirestore(); 
			java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
			comment.setTimestamp(timestamp.getTime());
			fireStore.collection("Product").document(comment.getProductId()).collection("Comment").document(comment.getCommentId()).set(comment);
			return comment;
		}

}
