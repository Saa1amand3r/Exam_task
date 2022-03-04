package com.example.Downloader.controllers;

import com.example.Downloader.interfaces.ImagesInterface;
import com.example.Downloader.interfaces.UsersInterface;
import com.example.Downloader.models.Images;
import com.example.Downloader.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@Controller
public class MappingController {

    @Autowired
    private ImagesInterface imginterface;
    @Autowired
    private ImagesController imgcontroller;
    @Autowired
    private UsersInterface usersInterface;
    @Autowired
    private UsersController usrController;

    @GetMapping("/")
    public String download(Model model) {
        model.addAttribute("text", "welcome to the downloader page. Here you can download your data to our database");
        model.addAttribute("title", "Download");
        return "download";
    }

    @PostMapping("/")
    public String download(@RequestParam MultipartFile image, Model model) throws IOException, SQLException, URISyntaxException {
        byte[] byteData = image.getBytes();
        Blob img = new SerialBlob(byteData);
        Images avatar = new Images(img);
        avatar.setImage_name(image.getOriginalFilename());
        avatar.setImage_size(image.getSize());
        avatar.setImage_type(image.getContentType());
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
        Images avatar = imgcontroller.convert(image);
        Users newUser = new Users(username, password, email, avatar.getUuid());
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
        // we need also give an image to html, but I will do this feature next time
        byte[] content = usrController.downloadPicture(user.map(Users::getImage_id).orElse(null));

        model.addAttribute("image", content);
        return "profile";
    }
}
