package com.ohgiraffers.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile,
                                   @RequestParam String singleFileDescription,
                                   Model model) throws IOException {

        System.out.println("singleFile = " + singleFile);
        System.out.println("singleFileDescription = " + singleFileDescription);

        /* 참고. 파일을 저장할 경로 설정 */
        Resource resource = resourceLoader.getResource("classpath:static/img/single");
        // 저장할 경로를 미리 만들어주었지만, 나중에 사용 안함..???

        // ↓ 똑같은 파일을 올리더라도 중복되지 않게끔 만들기
        String filePath = null;

        // 경로 존재하지 않은 경우
        if (!resource.exists()) {           // ◁ 참고. 만약 static 폴더에 파일이 없는 경우 만들어준다.
            String root = "src/main/resources/static/img/single";
            File file = new File(root);                 // 업로드할 파일을 root 에 만들어줄거임~~
            file.mkdirs();                              // s를 붙이는 건 하나/여러개의 폴더를 만드느냐에 차이~
            filePath = file.getAbsolutePath();          // 절대 경로에~~

            // 경로 존재할때...??????
        } else {
            filePath = resourceLoader.getResource("classpath:static/img/single").getFile().getAbsolutePath();
        }
        /* 참고. 파일명 변경 처리 */
        String originFileName = singleFile.getOriginalFilename();      // ~~오리지널 이름을 반환해주는 메소드
        System.out.println("originFileName = " + originFileName);

        /* 참고. 확장자 제거 */
        // .jpg 이런 확장자를 뗴는 방법~~(마지막에서부터 .이 나올떄까지 잘라준다.)
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext = " + ext);

        String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
        System.out.println("saveName = " + saveName);

        /* 참고. 파일을 저장 */
        singleFile.transferTo(new File(filePath + "/" + saveName));
        model.addAttribute("message", "파일 업로드 성공!!!");
        model.addAttribute("img", "static/img/single/" + saveName);

        return "result";

    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles
            , @RequestParam String multiFileDescription
            , Model model) throws IOException {

        System.out.println("multipartFiles = " + multiFiles);
        System.out.println("multiFileDescription = " + multiFileDescription);

        Resource resource = resourceLoader.getResource("classpath:static/img/multi");
        String filePath = null;

        if (!resource.exists()) {

            String root = "src/main/resources/static/img/multi";
            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            filePath = resourceLoader.getResource("classpath:static/img/multi").getFile().getAbsolutePath();
        }

        System.out.println("filePath = " + filePath);    // 여러개를 저장할땐 이쪽에다가??(build에..??)

        List<FileDTO> files = new ArrayList<>();
        List<String> saveFiles = new ArrayList<>();

        for (MultipartFile file : multiFiles) {   // 여러개 놓을 반복문 만들기(file라는 변수에 계속 담아주기)

            /* 파일명 변경 처리 */
            String originFileName = file.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            files.add(new FileDTO(originFileName, savedName, filePath, multiFileDescription));

            file.transferTo(new File(filePath + "/" + savedName));
            saveFiles.add("static/img/multi/" + savedName);
        }

        model.addAttribute("message", "파일 업로드 성공!!!!!!!!");
        model.addAttribute("imgs", saveFiles);

        return "result";
    }
}