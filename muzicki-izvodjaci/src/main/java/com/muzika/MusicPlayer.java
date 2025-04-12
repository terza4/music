package com.muzika;

import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.InputStream;

public class MusicPlayer {
    private static Player player;
    private static Thread playerThread;

    public static void play(String path) {
        stop(); // zaustavi ako već nešto svira
        try {
            InputStream is = new FileInputStream(path);
            player = new Player(is);

            playerThread = new Thread(() -> {
                try {
                    System.out.println("▶ Reprodukcija: " + path);
                    player.play();
                } catch (Exception e) {
                    System.out.println("⛔ Greška tokom puštanja: " + e.getMessage());
                }
            });
            playerThread.start();

        } catch (Exception e) {
            System.out.println("⛔ Greška: " + e.getMessage());
        }
    }

    public static void stop() {
        try {
            if (player != null) {
                player.close();
                System.out.println("⏹ Reprodukcija prekinuta.");
            }
            if (playerThread != null && playerThread.isAlive()) {
                playerThread.interrupt();
            }
        } catch (Exception e) {
            System.out.println("⚠ Greška pri zaustavljanju: " + e.getMessage());
        }
    }

    public static boolean isPlaying() {
        return playerThread != null && playerThread.isAlive();
    }
}
