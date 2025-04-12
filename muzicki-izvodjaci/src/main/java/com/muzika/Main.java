package com.muzika;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IzvodjacRepository repository = new IzvodjacRepository();
        SongRepository songRepository = new SongRepository();
        MusicPlayer musicPlayer = new MusicPlayer();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MUZICKI IZVODJACI APP ===");
            System.out.println("1. Dodaj novog izvođača");
            System.out.println("2. Prikaži sve izvođače");
            System.out.println("3. Pronađi izvođača po ID-u");
            System.out.println("4. Ažuriraj izvođača");
            System.out.println("5. Dodaj pjesmu");
            System.out.println("6. Pusti pjesmu");
            System.out.println("7  Prikazi pjesme od izvodjaca");
            System.out.println("8. Obriši izvođača");
            System.out.println("9 Obriši pjesmu");
            System.out.println("10. Zaustavi pjesmu");
            System.out.println("0. Izlaz");
            System.out.print("Izaberi opciju: ");

            int opcija = scanner.nextInt();
            scanner.nextLine(); // konzumira novi red

            switch (opcija) {
                case 1:
                    System.out.print("Naziv: ");
                    String naziv = scanner.nextLine();

                    System.out.print("Tip (solo/bend): ");
                    String tip = scanner.nextLine();

                    System.out.print("Godina formacije: ");
                    int formacija = scanner.nextInt();

                    System.out.print("Godina raspada (0 ako nije raspadnut): ");
                    int raspad = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Zvanični sajt (prazno ako nema): ");
                    String sajt = scanner.nextLine();

                    Izvodjac izvodjac = new Izvodjac(
                            naziv,
                            tip,
                            formacija,
                            (raspad == 0 ? null : raspad),
                            sajt.isEmpty() ? null : sajt
                    );
                    repository.save(izvodjac);
                    System.out.println("✔ Izvođač uspješno dodat!");
                    break;

                case 2:
                    List<Izvodjac> izvodjaci = repository.findAll();
                    izvodjaci.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Unesi ID: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    Izvodjac pronadjen = repository.findById(id);
                    if (pronadjen != null) {
                        System.out.println(pronadjen);
                    } else {
                        System.out.println("⛔ Nema izvođača sa tim ID-om.");
                    }
                    break;

                case 4:
                    System.out.print("Unesi ID izvođača za ažuriranje: ");
                    Long updateId = scanner.nextLong();
                    scanner.nextLine();

                    Izvodjac zaAzuriranje = repository.findById(updateId);
                    if (zaAzuriranje != null) {
                        System.out.print("Novi naziv: ");
                        String noviNaziv = scanner.nextLine();
                        zaAzuriranje.setNaziv(noviNaziv);
                        repository.update(zaAzuriranje);
                        System.out.println("✔ Izvođač ažuriran.");
                    } else {
                        System.out.println("⛔ Nema izvođača sa tim ID-om.");
                    }
                    break;
                case 5:
                    System.out.print("Unesi naziv pjesme: ");
                    String name = scanner.nextLine();
                    System.out.print("Unesi putanju do pjesme: ");
                    String putanja = scanner.nextLine();
                    System.out.print("ID izvođača: ");
                    Long idIzvodjaca = scanner.nextLong();
                    scanner.nextLine();

                    Izvodjac provjeraII = repository.findById(idIzvodjaca);
                    if(provjeraII != null){
                        Song song = new Song(name,putanja,provjeraII);
                        songRepository.save(song);
                        System.out.print("Pjesma je uspjesno dodana.");
                    }else{
                        System.out.print("Nema izvodjaca sa tim ID-jem");
                    }
                    break;
                case 6:
                    System.out.print("Unesi ID izvodjaca: ");
                    Long iddd = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Unesi naziv pjesme: ");
                    String nazivPjesme = scanner.nextLine();

                    Izvodjac provjeraI = repository.findById(iddd);
                    if (provjeraI != null) {
                        Song trazena = songRepository.findByNameAndArtist(nazivPjesme, iddd);
                        if (trazena != null) {
                            MusicPlayer.play(trazena.getSongs());
                        } else {
                            System.out.println("⛔ Pjesma nije pronađena.");
                        }
                    } else {
                        System.out.println("⛔ Nema izvođača sa tim ID-om.");
                    }
                    break;
                case 7:
                    System.out.print("Unesi ID od izvodjaca: ");
                    Long izID = scanner.nextLong();
                    scanner.nextLine();

                    Izvodjac i = repository.findById(izID);
                    if(i != null){
                        List<Song> pjesme = songRepository.findAllByIzvodjacId(izID);
                        if (!pjesme.isEmpty()) {
                            System.out.println("🎵 Pjesme izvođača " + i.getNaziv() + ":");
                            for (Song s : pjesme) {
                                System.out.println("- " + s.getSongName() + " (" + s.getSongs() + ")");
                            }
                        } else {
                            System.out.println("⛔ Ovaj izvođač nema dodanih pjesama.");
                        }
                    } else {
                        System.out.println("⛔ Izvođač nije pronađen.");
                    }
                    break;
                case 8:
                    System.out.print("Unesi ID izvođača za brisanje: ");
                    Long deleteId = scanner.nextLong();
                    scanner.nextLine();
                    repository.delete(deleteId);
                    System.out.println("✔ Izvođač obrisan.");
                    break;
                case 9:
                    System.out.print("Unesi ID izvodjaca: ");
                    Long iddiz = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Unesi naziv pjesme: ");
                    String pjesmaN = scanner.nextLine();

                    Izvodjac ii = repository.findById(iddiz);
                    if(ii != null){
                        songRepository.delete(iddiz,pjesmaN);
                        System.out.print("Pjesma obrisana.");
                    }else{
                        System.out.print("Pjesma sa tim imenom ne postoji.");
                    }
                    break;
                case 10:
                    MusicPlayer.stop();
                    break;
                case 0:
                    if (MusicPlayer.isPlaying()) {
                        MusicPlayer.stop();
                    }
                    running = false;
                    break;

                default:
                    System.out.println("⛔ Nevažeća opcija.");
            }
        }

        repository.close();
        System.out.println("👋 Zatvaranje aplikacije...");
    }
}