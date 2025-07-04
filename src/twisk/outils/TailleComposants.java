package twisk.outils;

public class TailleComposants {

    private static TailleComposants ourInstance = new TailleComposants ();

    /**
     * Fonction tailleComposant
     * @return l'instance de l'objet
     */
    public static  TailleComposants getInstance(){
        return ourInstance;
    }

    /**
     * Fonction get largeur qui permet de definir la largeur du composant
     * @return la largeur du composant
     */
    public int getLargeurActivite(){
        return 150;
    }

    /**
     * Fonction get hauteur qui definie la hauteur du compposant
     * @return la hauteur du composant
     */
    public int getHauteurActivite(){
        return 80;
    }

    /**
     * Fonction qui défini la largeur d'une case de guichet
     * @return la largeur du guichet
     */
    public int getLargeurGuichet(){
        return 150;
    }

    /**
     * Fonction qui défini la hauteur d'une case de guichet
     * @return la hauteur
     */
    public int getHauteurGuichet(){
        return 30;
    }

    /**
     * fonction get qui permet de recuperer la largeur de
     * @return la largeur
     */
    public int getLargeurEtape(){
        return 160;
    }

    /**
     * fonction get qui permet de recuperer la hauteur
     * @return la hauteur
     */
    public int getHauteurEtape(){
        return 100;
    }
}


