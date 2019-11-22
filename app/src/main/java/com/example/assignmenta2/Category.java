package com.example.assignmenta2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Category implements Serializable {

    public int id;

    public String title;

    public int getId() {
        return id; }

    public void setId(int id) {
        this.id = id; }

    public String getTitle() {
        return title; }

    public void setTitle(String title) {
        this.title = title; }

    public int getClues_count() {
        return clues_count;
    }

    public void setClues_count(int clues_count) {
        this.clues_count = clues_count;
    }

    public int clues_count;
}