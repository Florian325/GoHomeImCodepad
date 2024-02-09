import java.util.Random;

public class Spiel {
    private String farbe1;
    private String farbe2;
    private String aktuelleFarbe;

    private Figur f1;
    private Figur f2;
    private Figur f3;
    private Figur f4;

    private Figur aktuelleFigur1;
    private Figur aktuelleFigur2;

    public Spiel() {
        farbe1 = "blau";
        f1 = new Figur(farbe1, 2, -2);
        f2 = new Figur(farbe1, -2, 2);

        farbe2 = "rot";
        f3 = new Figur(farbe2, -2, -2);
        f4 = new Figur(farbe2, 2, 2);

        aktuelleFarbe = "blau";
    }

    private void druckeGewinner() {
        int count = 0;
        System.out.println('\u000C');
        if (f1.gewonnen()) {
            count += 1;
            System.out.println("Blau (F1) hat gewonnen");
        }
        if (f2.gewonnen()) {
            count += 1;
            System.out.println("Blau (F2) hat gewonnen");
        }
        if (f3.gewonnen()) {
            count += 1;
            System.out.println("Rot (F3) hat gewonnen");
        }
        if (f4.gewonnen()) {
            count += 1;
            System.out.println("Rot (F4) hat gewonnen");
        }
        if (count == 0)
            System.out.println("Niemand hat gewonnen");
        druckeSpielfeld();
    }

    private void druckeAktuellenSpieler() {
        if (aktuelleFarbe != null) {
            System.out.println("An der Reihe ist der Spieler mit der Farbe " + aktuelleFarbe);
            System.out.println("-------------------------------");
        }
    }

    private void druckeAktuelleFiguren() {
        if (aktuelleFigur1 != null && aktuelleFigur2 != null) {
            System.out.println("Die aktuellen Figuren sind:");
            aktuelleFigur1.druckePosition();
            aktuelleFigur2.druckePosition();
            System.out.println("-------------------------------");
        }
    }

    private void druckeSpielfeld() {
        StringBuilder[][] feld = new StringBuilder[5][5];
        for (int x = 0; x < 5; x++)
            for (int y = 0; y < 5; y++)
                feld[x][y] = new StringBuilder();

        if (f1 == aktuelleFigur1 || f1 == aktuelleFigur2)
            feld[f1.x + 2][f1.y + 2].append(farbe1.toUpperCase().charAt(0));
        else
            feld[f1.x + 2][f1.y + 2].append(farbe1.toLowerCase().charAt(0));
        if (f2 == aktuelleFigur1 || f2 == aktuelleFigur2)
            feld[f2.x + 2][f2.y + 2].append(farbe1.toUpperCase().charAt(0));
        else
            feld[f2.x + 2][f2.y + 2].append(farbe1.toLowerCase().charAt(0));
        if (f3 == aktuelleFigur1 || f3 == aktuelleFigur2)
            feld[f3.x + 2][f3.y + 2].append(farbe2.toUpperCase().charAt(0));
        else
            feld[f3.x + 2][f3.y + 2].append(farbe2.toLowerCase().charAt(0));
        if (f4 == aktuelleFigur1 || f4 == aktuelleFigur2)
            feld[f4.x + 2][f4.y + 2].append(farbe2.toUpperCase().charAt(0));
        else
            feld[f4.x + 2][f4.y + 2].append(farbe2.toLowerCase().charAt(0));
        if (feld[2][2].toString().equals(""))
            feld[2][2] = new StringBuilder("HOME");
        System.out.print("    ------------------------");
        for (int y = 2; y >= -2; y--) {
            System.out.printf("\n%2s |", y);
            for (int x = -2; x <= 2; x++) {
                System.out.printf("%-4s|", feld[x + 2][y + 2]);
            }
            System.out.print("\n    ------------------------");
        }
        System.out.println("\n    -2   -1    0    1    2");
    }

    private void aktuelleFigurenAuswuerfeln() {
        Random r = new Random();
        int zahl1 = r.nextInt(4);
        int zahl2 = r.nextInt(4);
        if (zahl1 == 1)
            aktuelleFigur1 = f1;
        else if (zahl1 == 2)
            aktuelleFigur1 = f2;
        else if (zahl1 == 3)
            aktuelleFigur1 = f3;
        else
            aktuelleFigur1 = f4;

        if (zahl2 == 1)
            aktuelleFigur2 = f1;
        else if (zahl2 == 2)
            aktuelleFigur2 = f2;
        else if (zahl2 == 3)
            aktuelleFigur2 = f3;
        else
            aktuelleFigur2 = f4;
    }

    public void nachObenBewegen() {
        aktuelleFigur1.gehe(0);
        aktuelleFigur2.gehe(0);
    }

    public void nachRechtsBewegen() {
        aktuelleFigur1.gehe(1);
        aktuelleFigur2.gehe(1);
    }

    public void nachUntenBewegen() {
        aktuelleFigur1.gehe(2);
        aktuelleFigur2.gehe(2);
    }

    public void nachLinksBewegen() {
        aktuelleFigur1.gehe(3);
        aktuelleFigur2.gehe(3);
    }

    private void setzeNeuenSpieler() {
        if (aktuelleFarbe == farbe1)
            aktuelleFarbe = farbe2;
        else
            aktuelleFarbe = farbe1;
    }

    private boolean spielIstFertig() {
        if (f1.gewonnen() || f2.gewonnen() || f3.gewonnen() || f4.gewonnen())
            return true;
        return false;
    }

    public void neuenZugVorbereiten() {
        if (spielIstFertig()) {
            druckeGewinner();
        } else {
            System.out.print('\u000C');
            setzeNeuenSpieler();
            druckeAktuellenSpieler();
            aktuelleFigurenAuswuerfeln();
            druckeAktuelleFiguren();
            druckeSpielfeld();
        }
    }
}
