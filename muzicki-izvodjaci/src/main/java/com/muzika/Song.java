package com.muzika;

import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "izvodjac_id", nullable = false)
    private Izvodjac izvodjac;
    @Column(name = "songName", nullable = false)
    private String songName;

    @Column(name = "songs", nullable = false)
    private String songs;

    public Song(){

    }

    public Song(String songName, String songs, Izvodjac izvodjac){
        this.songName = songName;
        this.songs = songs;
        this.izvodjac = izvodjac;
    }

    //setter and getter
    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }

    public String getSongs() {
        return songs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Izvodjac getIzvodjac() {
        return izvodjac;
    }

    public void setIzvodjac(Izvodjac izvodjac) {
        this.izvodjac = izvodjac;
    }
}
