package com.example.Downloader.interfaces;

import com.example.Downloader.models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsersInterface extends CrudRepository<Users, Long> {
    public static final String FIND_PICTURE_BY_UUID = "SELECT image FROM `images` WHERE uuid=:uuid ";

    @Query(value = FIND_PICTURE_BY_UUID, nativeQuery = true)
    byte[] downloadPicture(@Param("uuid") String uuid);
}