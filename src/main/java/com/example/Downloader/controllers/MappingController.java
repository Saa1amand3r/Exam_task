package com.example.Downloader.controllers;

import com.example.Downloader.interfaces.ImagesInterface;
import com.example.Downloader.interfaces.UsersInterface;
import com.example.Downloader.models.Images;
import com.example.Downloader.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MappingController {

    @Autowired
    private ImagesInterface imginterface;
    @Autowired
    private UsersInterface usersInterface;
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String IMAGE_NAME_QUERY = "SELECT `image_name` FROM `images` WHERE uri=?";

    @GetMapping("/")
    public String download(Model model) {
        model.addAttribute("text", "welcome to the downloader page. Here you can download your data to our database");
        model.addAttribute("title", "Download");
        return "download";
    }

    @PostMapping("/")
    public String download(@RequestParam MultipartFile image, Model model) throws IOException, SQLException, URISyntaxException {
        Images avatar = new Images();
        if (!image.isEmpty()) {
            // avatar = imgcontroller.saveImage(image);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + "." + image.getOriginalFilename();
            avatar.setImage_name(fileName);
            avatar.setImage_size(image.getSize());
            avatar.setImage_type(image.getContentType());
            avatar.setURI(uploadDir + "\\" + fileName);
            image.transferTo(new File(avatar.getURI()));
        }
        imginterface.save(avatar);
        return "download";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("title", "New User");
        return "registration";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam MultipartFile image, @RequestParam String username, @RequestParam String password, @RequestParam String email, Model model) throws SQLException, IOException {
        Images avatar = new Images();
        if (!image.isEmpty()) {
            // avatar = imgcontroller.saveImage(image);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + "." + image.getOriginalFilename();
            avatar.setImage_name(fileName);
            avatar.setImage_size(image.getSize());
            avatar.setImage_type(image.getContentType());
            avatar.setURI(uploadDir + "\\" + fileName);
            image.transferTo(new File(avatar.getURI()));
        }
        Users newUser = new Users(username, password, email, avatar.getURI());
        
        imginterface.save(avatar);
        usersInterface.save(newUser);
        return "registration";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "takeUserFromDB";
    }

    @PostMapping("/login")
    public String login(@RequestParam Long id, Model model) {
        Optional<Users> user = usersInterface.findById(id);
        model.addAttribute("title", "profile");
        model.addAttribute("username", user.map(Users::getUsername).orElse(null));
        model.addAttribute("password", user.map(Users::getPassword).orElse(null));
        model.addAttribute("email", user.map(Users::getEmail).orElse(null));
        String imgName = jdbcTemplate.queryForObject(IMAGE_NAME_QUERY, String.class,user.map(Users::getURI).orElse(null));
        String imgAdress = "File://"+ uploadPath + "/"+ imgName;
        model.addAttribute("imageName", imgAdress);
        Users u = user.get();
        u.setStatus("Online");
        model.addAttribute("status", u.getStatus());
        return "profile";
    }

    @GetMapping("/status")
    public String checkStatus(Model model) {
        model.addAttribute("title", "Status");
        return "checkStatus";
    }

    @PostMapping("/status")
    public String status(@RequestParam Long id, Model model) {
        Optional<Users> user = usersInterface.findById(id);
        model.addAttribute("title", "Status");
        model.addAttribute("status", user.map(Users::getStatus).orElse(null));
        return "statuses";
    }
}
