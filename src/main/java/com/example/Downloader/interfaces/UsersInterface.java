package com.example.Downloader.interfaces;

import com.example.Downloader.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersInterface extends CrudRepository<Users, Long> {}