package singleCopy;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import javax.servlet.http.Part;

public class ImageUpload {
    private static ImageUpload IU=new ImageUpload();
    private ImageUpload(){
    }
    
    public String uploadImage(Part part,String path){
        try {
            Part p=part;
            byte b[]= new byte[p.getInputStream().available()];
            p.getInputStream().read(b);
            File f=new File(path+"/image.jpg") ;

            FileOutputStream fout=new FileOutputStream(f);
            fout.write(b);
            fout.close();

            Cloudinary cld=new Cloudinary(ObjectUtils.asMap("cloud_name","bhooooot","api_key","444557635596948","api_secret","kUBMvq3oH2ZaZKcWMJrieiV0N2E"));
            Map uploadResult=cld.uploader().upload(f, ObjectUtils.emptyMap());

            System.out.println(uploadResult.get("secure_url"));
            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static ImageUpload getInstance(){
        return IU;
    }
    
}
