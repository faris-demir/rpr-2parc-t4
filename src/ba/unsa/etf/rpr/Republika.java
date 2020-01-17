package ba.unsa.etf.rpr;

public class Republika extends Drzava {
    public Republika(int id, String naziv, Grad glavniGrad) {
        super(id, naziv, glavniGrad);
    }

    public Republika() {
    }

    @Override
    public String getNaziv() {
        if (super.getNaziv().contains("Republika ")) return super.getNaziv();
        return "Republika " + super.getNaziv();
    }

//    @Override
//    public String toString() {
//        return "Republika " + super.toString();
//    }
}
