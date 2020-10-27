package com.hardwarevaluewareapi.image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import org.springframework.web.multipart.MultipartFile;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.StorageClient;

public class SendImage {
	 
	 public String sendImageCode(MultipartFile file) throws IOException {
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
	      return imageUrl;
	}
}
