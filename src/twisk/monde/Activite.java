package twisk.monde;

public class Activite extends Etape {
    private int temps ;
    private int ecartTemps ;

    /**
     * Constructeur d'une activite
     * @param nom le nom de l'activite
     */
    public Activite(String nom){
        super(nom);
        this.temps = 5 ;
        this.ecartTemps = 5 ;
    }

    /**
     * Constructeur d'une activite
     * @param nom le nom de l'activite
     * @param t le temps que dure l'activite
     * @param e l'ecart temps
     */
    public Activite(String nom, int t , int e){
        super(nom);
        this.temps = t ;
        this.ecartTemps = e ;

    }
     /**
      * return Temps
      *@return Temps
      */
    public int getTemps() {
        return temps;
    }

    /**
     *return EcartTemps
     * @return EcartTemps
     */
    public int getEcartTemps() {
        return ecartTemps;
    }

    /**
     * fonction d'affichage d'une activite
     * @return un string builder
     */
    public String toC(){
        StringBuilder str = new StringBuilder();
        if(this.getGestionnaireSuccesseurs().nbEtapes()!=0) {
            if (this.getGestionnaireSuccesseurs().nbEtapes() > 1) {
                str.append("int nb"+this.getNom()+"=(int)(rand()/(float)RAND_MAX)*" + this.getGestionnaireSuccesseurs().nbEtapes() + ";\n");
                str.append("switch(nb"+this.getNom()+"){\n");
                for (int i = 0; i < this.getGestionnaireSuccesseurs().nbEtapes(); i++) {
                    str.append("case " + i + " :\n");
                    str.append("transfert(" + this.getNom() + "," + this.getGestionnaireSuccesseurs().toC() + "\n");
                    str.append(this.getGestionnaireSuccesseurs().getSuccesseur().get(i).delais());
                    if (this.getGestionnaireSuccesseurs().getSuccesseur().get(i).getGestionnaireSuccesseurs().nbEtapes() != 0) {
                        str.append(this.getGestionnaireSuccesseurs().getSuccesseur().get(i).toC() + "\n");
                    }
                    str.append("break;\n");
           //         str.append("}\n");
                }
                str.append("}\n");
            } else {
                str.append("transfert(" + this.getNom() + "," + this.getGestionnaireSuccesseurs().toC() + "\n");
                str.append(this.getGestionnaireSuccesseurs().getSuccesseur().get(0).delais());
                if (this.getGestionnaireSuccesseurs().getSuccesseur().get(0).getGestionnaireSuccesseurs().nbEtapes() != 0) {
                    str.append(this.getGestionnaireSuccesseurs().getSuccesseur().get(0).toC() + "\n");
                }
            }
        }
        return str.toString();
    }

    /**
     * fonction d'affichage delai du succeseur
     * @return un string builder
     */
    public String delais(){
        StringBuilder str = new StringBuilder();
        str.append("delai("+this.temps+","+this.ecartTemps+");\n");
        return str.toString();
    }


    /**
     * Fonction booleenne
     * @return un booleen
     */
    @Override
    public boolean estUneActivite(){
        return true ;
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
