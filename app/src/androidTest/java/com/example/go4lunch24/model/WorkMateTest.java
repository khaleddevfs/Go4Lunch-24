package com.example.go4lunch24.model;

import androidx.annotation.Nullable;

import com.example.go4lunch24.models.WorkMate;

import junit.framework.TestCase;

import org.junit.Before;

import java.util.List;

public class WorkMateTest extends TestCase {


    private WorkMate workMate;
    private String uid = "uid";
    private String name = "name";
    @Nullable
    private String email = "email";
    @Nullable
    private String photoUrl = "photoUrl";
    @Nullable
    private List<String> likedRestaurants;

    @Before
    public void setup() {}

    public void testSetAndGetUid() {
        workMate = new WorkMate(uid,name,email,photoUrl);
        assertEquals(uid, workMate.getUid());

        String setData = "uid";
        workMate.setUid(setData);

        String getData = workMate.getUid();
        assertNotNull(getData);
        assertEquals(setData,getData);
    }

    public void testSetAndGetName() {
        workMate = new WorkMate(uid,name,email,photoUrl);
        assertEquals(name, workMate.getName());

        String setData = "name";
        workMate.setName(setData);

        String getData = workMate.getName();
        assertNotNull(getData);
        assertEquals(setData,getData);
    }

    public void testSetAndGetEmail() {
        workMate = new WorkMate(uid,name,email,photoUrl);
        assertEquals(email, workMate.getEmail());

        String setData = "email";
        workMate.setEmail(setData);

        String getData = workMate.getEmail();
        assertNotNull(getData);
        assertEquals(setData,getData);
    }

    public void testSetAndGetPhotoUrl() {
        workMate = new WorkMate(uid,name,email,photoUrl);
        assertEquals(photoUrl, workMate.getPhotoUrl());

        String setData = "photoUrl";
        workMate.setPhotoUrl(setData);

        String getData = workMate.getPhotoUrl();
        assertNotNull(getData);
        assertEquals(setData,getData);
    }

    public void testGetLikedRestaurants() {
        workMate = new WorkMate(uid,name,email,photoUrl);
        assertNull(workMate.getLikedRestaurants());

        String setData = "uid";
        workMate.setName(setData);

        String getData = workMate.getName();
        assertNotNull(getData);
        assertEquals(setData,getData);
    }
}
