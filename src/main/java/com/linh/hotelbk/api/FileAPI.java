package com.linh.hotelbk.api;

import com.linh.hotelbk.dto.request.SaveFileRequest;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@RestController
@Slf4j
@Api(tags = "API")
public class FileAPI {

    @PostMapping(path = "api/save-file")
    public String savePdfFile(@RequestBody SaveFileRequest request){
        try {
            String rootPath =  new FileSystemResource("opt/wildfly").getFile().getAbsolutePath();
            log.info("root path : "+rootPath);
            File uploadPath = new File(rootPath);
            if (uploadPath.exists()){
                log.info("yes");
            }else{
                log.info("No");
                Files.createDirectories(uploadPath.toPath());
            }
            Path savePath = Paths.get(uploadPath.getAbsolutePath()).resolve(request.getFileName());
            log.info("save path file : "+savePath.toFile().getAbsolutePath());
            Files.write(savePath, Base64.getDecoder().decode(request.getFileContent()));

            return "Success";
        }catch (Exception e){
           e.printStackTrace();
           return " Failed";
        }

    }
}
