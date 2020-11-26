package com.hardwarevaluewareapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hardwarevaluewareapi.bean.Shopkeeper;
import com.hardwarevaluewareapi.exception.ResourceNotFoundException;
import com.hardwarevaluewareapi.service.ShopkeeperService;

@RestController
@RequestMapping("/store")
public class ShokeeperController {
	@Autowired
	private ShopkeeperService shopkeeperService;
	@PostMapping("/save")
     public ResponseEntity<?> saveStore(@RequestParam("file") MultipartFile file,
    		 @RequestParam("shopName") String shopName,
    		 @RequestParam("contactNumber") String contactNumber,
    		 @RequestParam("address") String address,
    		 @RequestParam("email") String email,
    		 @RequestParam("token") String token,
    		 @RequestParam("shopKeeperId")String shopKeeperId) throws Exception{
    	 if(file.isEmpty())
   		  throw new Exception();
    	 Shopkeeper shopkeeper = new Shopkeeper();
    	 shopkeeper.setContactNumber(contactNumber);
    	 shopkeeper.setShopName(shopName);
    	 shopkeeper.setAddress(address);
    	 shopkeeper.setEmail(email);
    	 shopkeeper.setToken(token);
    	 shopkeeper.setShopKeeperId(shopKeeperId);
    	 Shopkeeper s=shopkeeperService.saveStore(file,shopkeeper);
    	 return new ResponseEntity<Shopkeeper>(s,HttpStatus.OK);
     }
	@GetMapping("/{id}")
	public ResponseEntity<?> viewStore(@PathVariable String id) throws Exception {
		 Shopkeeper s=shopkeeperService.viewStore(id);
		 if(s!=null) 
		     return new ResponseEntity<Shopkeeper>(s,HttpStatus.OK);
		 else
			 throw new ResourceNotFoundException("store not found for this id : "+id);
	}
	@PostMapping("/update")
    public ResponseEntity<?> updateStore(@RequestParam("file") MultipartFile file,
    		@RequestParam("shopName") String shopName,
    		@RequestParam("shopKeeperId") String shopKeeperId,
    		@RequestParam("contactNumber") String contactNumber,
    		@RequestParam("address") String address,
    		@RequestParam("email") String email,
    		@RequestParam("token") String token) throws Exception{
	   	 if(file.isEmpty())
	  		  throw new Exception();
	   	 Shopkeeper shopkeeper = new Shopkeeper();
	   	 shopkeeper.setContactNumber(contactNumber);
	   	 shopkeeper.setShopName(shopName);
	   	 shopkeeper.setAddress(address);
	   	 shopkeeper.setEmail(email);
	   	 shopkeeper.setShopKeeperId(shopKeeperId);
	   	 shopkeeper.setToken(token);
	   	 Shopkeeper shop=shopkeeperService.updateStore(file, shopkeeper);
	   	 return new ResponseEntity<Shopkeeper>(shop,HttpStatus.OK);
    }
	@PostMapping("/update/withoutImage")
    public ResponseEntity<?> updateStoreWithoutImage(@RequestBody Shopkeeper shopkeeper ) throws Exception{
	   	 Shopkeeper shop=shopkeeperService.updateStoreWithoutImage(shopkeeper);
	   	 return new ResponseEntity<Shopkeeper>(shop,HttpStatus.OK);
    }
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStore(@PathVariable String id) throws Exception{
		 Shopkeeper shopkeeper=shopkeeperService.deleteStore(id);
		 if(shopkeeper!=null) 
		      return new ResponseEntity<Shopkeeper>(shopkeeper,HttpStatus.OK);
		 else
			  throw new ResourceNotFoundException("Not matching store for this id : "+id);
	}
		 
}
 