package com.muzika;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IzvodjacRepository repository = new IzvodjacRepository();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MUZICKI IZVODJACI APP ===");
            System.out.println("1. Dodaj novog izvođača");
            System.out.println("2. Prikaži sve izvođače");
            System.out.println("3. Pronađi izvođača po ID-u");
            System.out.println("4. Ažuriraj izvođača");
            System.out.println("5. Obriši izvođača");
            System.out.println("6. Izlaz");
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
                    System.out.println("✔ Izvođač uspešno dodat!");
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
                    System.out.print("Unesi ID izvođača za brisanje: ");
                    Long deleteId = scanner.nextLong();
                    scanner.nextLine();
                    repository.delete(deleteId);
                    System.out.println("✔ Izvođač obrisan.");
                    break;

                case 6:
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