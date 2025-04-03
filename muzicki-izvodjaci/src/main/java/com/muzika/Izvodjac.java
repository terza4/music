package com.muzika;

import javax.persistence.*;

@Entity
@Table(name = "izvodjac")
public class Izvodjac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private String tip; // solo ili bend

    @Column(name = "godina_formacije", nullable = false)
    private int godinaFormacije;

    @Column(name = "godina_raspada")
    private Integer godinaRaspada;

    @Column(name = "zvanicni_sajt")
    private String zvanicniSajt;

    // Constructors
    public Izvodjac() {
    }

    public Izvodjac(String naziv, String tip, int godinaFormacije, Integer godinaRaspada, String zvanicniSajt) {
        this.naziv = naziv;
        this.tip = tip;
        this.godinaFormacije = godinaFormacije;
        this.godinaRaspada = godinaRaspada;
        this.zvanicniSajt = zvanicniSajt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getGodinaFormacije() {
        return godinaFormacije;
    }

    public void setGodinaFormacije(int godinaFormacije) {
        this.godinaFormacije = godinaFormacije;
    }

    public Integer getGodinaRaspada() {
        return godinaRaspada;
    }

    public void setGodinaRaspada(Integer godinaRaspada) {
        this.godinaRaspada = godinaRaspada;
    }

    public String getZvanicniSajt() {
        return zvanicniSajt;
    }

    public void setZvanicniSajt(String zvanicniSajt) {
        this.zvanicniSajt = zvanicniSajt;
    }

    @Override
    public String toString() {
        return "Izvodjac{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", tip='" + tip + '\'' +
                ", godinaFormacije=" + godinaFormacije +
                ", godinaRaspada=" + godinaRaspada +
                ", zvanicniSajt='" + zvanicniSajt + '\'' +
                '}';
    }
}