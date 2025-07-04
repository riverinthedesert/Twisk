package twisk.monde;

public class Guichet extends Etape{
    private int nbJetons ;
    private int numSemaphore ;

    /**
     * Constructeur d'un Guichet
     * @param nom le nom du guichet
     */
    public Guichet(String nom){
        super(nom);
        this.nbJetons = 1 ;

    }

    /**
     * Constructeur d'un guichet
     * @param nom le nom du guichet
     * @param nb le nombre de jeton disponible au guichet
     */
     public Guichet(String nom,int nb){
        super(nom);
        this.nbJetons = nb ;
     }

    /**
     * Fonction booleenne qui verifier si l'objet est un guichet
     * @return un booleen
     */
    @Override
     public boolean estUnGuichet(){
        return true ;
     }

    /**
     * Fonction return nombre jetons si l'objet est un guichet
     * @return nombre jetons
     */
    @Override
    public int getNbJetons(){
        return this.nbJetons ;
    }

    /**
     * fonction d'affichage d'un guichet
     * @return un stringbuilder
     */
     public String toC(){

        StringBuilder str = new StringBuilder();
        if(this.getGestionnaireSuccesseurs().nbEtapes()!=0) {
            if (this.getGestionnaireSuccesseurs().nbEtapes() > 1) {
           //     str.append("int nb"+this.getNom()+"=(int)(rand()/(float)RAND_MAX)*" + this.getGestionnaireSuccesseurs().nbEtapes() + ";\n");
                str.append("switch((int)(rand()/(float)RAND_MAX)*" + this.getGestionnaireSuccesseurs().nbEtapes()+"){\n");
                for (int i = 0; i < this.getGestionnaireSuccesseurs().nbEtapes(); i++) {
                    str.append("case " + i + ":\n");
                    if (this.getGestionnaireSuccesseurs().getSuccesseur().size() != 0) {
                        str.append("P(ids,num_sem_guichet" + (numSemaphore + 1) + ");\n");
                        str.append("transfert(" + this.getNom() + "," + this.getGestionnaireSuccesseurs().toC() + "\n");
                        str.append(this.getGestionnaireSuccesseurs().getSuccesseur().get(i).delais());
                        str.append("V(ids,num_sem_guichet" + (numSemaphore + 1) + ");\n");
                        str.append(this.getGestionnaireSuccesseurs().getSuccesseur().get(i).toC());
                    }
                    str.append("break;\n");
         //           str.append("}\n");
                }
                str.append("}\n");
            } else {
                if (this.getGestionnaireSuccesseurs().getSuccesseur().size() != 0) {
                    str.append("P(ids,num_sem_guichet" + (numSemaphore + 1) + ");\n");
                    str.append("transfert(" + this.getNom() + "," + this.getGestionnaireSuccesseurs().toC() + "\n");
                    str.append(this.getGestionnaireSuccesseurs().getSuccesseur().get(0).delais());
                    str.append("V(ids,num_sem_guichet" + (numSemaphore + 1) + ");\n");
                    str.append(this.getGestionnaireSuccesseurs().getSuccesseur().get(0).toC());
                }
            }
        }
         return str.toString();
     }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * fonction d'affichage delai mais pour guichet c'est P(ids,num_sem_guichet1)
     * @return un string builder
     */
    public String delais(){
        StringBuilder str = new StringBuilder();
        return str.toString();
    }

    /**
     * return Temps
     *@return Temps
     */
    public int getTemps() {
        return 5;
    }

    /**
     *return EcartTemps
     * @return EcartTemps
     */
    public int getEcartTemps() {
        return 5;
    }

    /**
     *return nom de guicher
     * @return String
     */
    public String getName(){
        return this.getNom();
    }

}
