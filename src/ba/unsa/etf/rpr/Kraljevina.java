package ba.unsa.etf.rpr;

public class Kraljevina extends Drzava {
    public Kraljevina(int id, String naziv, Grad glavniGrad) {
        super(id, naziv, glavniGrad);
    }

    public Kraljevina() {
    }

    @Override
    public String getNaziv() {
        if (super.getNaziv().contains("Kraljevina ")) return super.getNaziv();
        return "Kraljevina " + super.getNaziv();
    }

//    @Override
//    public String toString() {
//        return "Kraljevina " + super.toString();
//    }
}
