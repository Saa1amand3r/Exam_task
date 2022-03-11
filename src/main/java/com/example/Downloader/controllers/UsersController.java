package com.example.Downloader.controllers;

import com.example.Downloader.interfaces.UsersInterface;
import com.example.Downloader.models.Images;
import com.example.Downloader.models.Users;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UsersController implements UsersInterface {
    // @Override
    // public byte[] downloadPicture(String uuid) {
    //     return null;
    // }

    @Override
    public <S extends Users> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Users> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Users> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Users> findAll() {
        return null;
    }

    @Override
    public Iterable<Users> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Users entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Users> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Images picture(String uri) {
        // TODO Auto-generated method stub
        return null;
    }
}
