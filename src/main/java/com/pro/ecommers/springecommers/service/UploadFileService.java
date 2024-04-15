package com.pro.ecommers.springecommers.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileService {
    //servicio encargado de guardalr las imagenes y eliminarlas
    private String foler="images//";

    /**
     * se encarga de guardar una imagen que se envia como un archivo multipart
     * (MultipartFile) es un directorio llamado images, si el archivo no es vacio
     * procede a guardarlo
     * @param file imagen
     * @return
     * @throws IOException
     */
    public String saveImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            byte [] bytes = file.getBytes();
            Path path = Paths.get(foler+file.getOriginalFilename());
            Files.write(path,bytes);
            return file.getOriginalFilename();
        }
        return "default.jpg";
    }

    public void deleteImage(String nombre){
        String ruta ="images//";
        File file = new File(ruta+nombre);
        file.delete();
    }

}
