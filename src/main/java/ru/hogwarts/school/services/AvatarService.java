package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.models.Avatar;
import ru.hogwarts.school.models.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("${student.avatar.dir.path}")
    private String avatarPath;
    private Logger logger = LoggerFactory.getLogger(StudentService.class);
    private AvatarRepository avatarRepository;
    private StudentService studentService;
    private StudentRepository studentRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentService studentService, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }


    public void uploadAvatar(long id, MultipartFile fileAvatar) throws IOException {

        logger.debug("Was invoked method upload avatar");
        StudentDTO student = studentService.findStudent(id);
        Path filePath = Path.of(avatarPath, id + "." + getExtension(fileAvatar.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = fileAvatar.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            bis.transferTo(bos);
            bis.transferTo(baos);


            Avatar avatar = findAvatarByStudentId(id);
            avatar.setStudent(student.toStudent());
            avatar.setFilePath(filePath.toString());
            avatar.setFileSize(fileAvatar.getSize());
            avatar.setMediaType(fileAvatar.getContentType());
            avatar.setData(baos.toByteArray());
            avatarRepository.save(avatar);
            logger.debug("Create avatar {}", avatar);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public Avatar findAvatarByStudentId(Long studentId) {
        logger.debug("Was invoked method for find avatar by student id {}", studentId);
        return avatarRepository.findAvatarByStudent_Id(studentId).orElse(new Avatar());
    }

    private String getExtension(String originalFilename) {
       return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

    }

}
