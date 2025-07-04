package twisk.monde;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class SasEntree extends Activite {
    private ArrayList<Etape> entree;
    /**
     * Constructeur d'un SasEntree
     */
    public SasEntree(){
        super("Entree",5,5);
        this.entree= new ArrayList<>();
    }

    /**
     * ajouter etapes dans Sasentree
     * @param etapes etapes
     */
    public  void ajouterEtapes(Etape ... etapes)
    {
        for (Etape etape : etapes) {
            this.entree.add(etape);
        }
    }

    /**
     * retourner etapes dans  le Sasentree
     * @return int la nombre de l'entree
     */
    public int  nbEtapes()
    {
        return this.entree.size();
    }

    /**
     * getter etape de index i
     * @param index index
     * @return un etape
     */
    public Etape getEtape(int index) {
        return this.entree.get(index);
    }



    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        for(Etape etape:entree){
            str2.append(etape.getNom());
        }
        str.append("entree: "+this.entree.size()+" successeur - "+str2.toString()+"\n");
        return str.toString();
    }

    /**
     * Fonction transforme a programme C
     * @return une chaîne de caractères contenant le code C
     */
    public String toC(){
        StringBuilder str = new StringBuilder();

        str.append("entrer(entree);\n");
        str.append("delai(5,5);\n");
        str.append("transfert(entree,").append(this.entree.get(0).getNom()).append(");\n");
        str.append(this.entree.get(0).delais());
        str.append(this.entree.get(0).toC());
        return str.toString();
    }
}
