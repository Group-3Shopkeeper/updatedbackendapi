package com.hardwarevaluewareapi.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardwarevaluewareapi.bean.Comment;
import com.hardwarevaluewareapi.bean.Product;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;
import com.hardwarevaluewareapi.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/")
	public ResponseEntity<?> saveComment(@RequestBody Comment comment) {
		Comment c= commentService.saveComment(comment);
		return new ResponseEntity<Comment>(c,HttpStatus.OK);
	}
	@GetMapping("/{productId}")
	public ResponseEntity<List<Comment>> getProductByComment(@PathVariable String productId) throws ResourceNotFoundException, InterruptedException, ExecutionException {
		List<Comment> commentList = commentService.getProductByComment(productId);
		if(commentList!=null)
		{
			return new ResponseEntity<List<Comment>>(commentList, HttpStatus.OK);	
		}
		else
		{
			throw new ResourceNotFoundException("Comment Not Found");
		}	
	}
	@DeleteMapping("/")
	public ResponseEntity<?> deleteComment(@RequestBody Comment comment) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Comment c =  commentService.deleteComment(comment);
		if(c == null) {
			throw new ResourceNotFoundException("Not found "+comment.getCommentId());
		}
		else
		  return new ResponseEntity<Comment>(c,HttpStatus.OK);
		  
	}
	@PostMapping("/update/")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment) throws Exception{
	   
	   	 Comment c=commentService.updateComment(comment);
	   	 return new ResponseEntity<Comment>(c,HttpStatus.OK);
    }

}
