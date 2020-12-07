package com.example.vinam_music.entities;

public class Music {
    private String name;
    private int mp3;
    private int image;

    public Music(String name, int mp3, int image) {
        this.name = name;
        this.mp3 = mp3;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMp3() {
        return mp3;
    }

    public void setMp3(int mp3) {
        this.mp3 = mp3;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Music{" +
                "name='" + name + '\'' +
                ", mp3=" + mp3 +
                ", image=" + image +
                '}';
    }
}
