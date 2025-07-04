package twisk.outils;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class KitC implements  Serializable {
    public KitC() {

    }

    /**
     * creer un Environement
     *
     * @throws IOException les exceptions repertorie
     */
    public void creerEnvironnement() {
        try {
            // création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà 
            Path directories = Files.createDirectories(Paths.get("/tmp/twisk"));
            // copie des deux fichiers programmeC.o et def.h depuis le projet sous /tmp/twisk 
            String[] liste = {"programmeC.o", "def.h","codeNatif.o"};
            for (String nom : liste) {
                // ici je trouve que la problem est dans window10 /D:/ n'est pas acceptable mais pour linux c'est possibl
                //java.nio.file.InvalidPathException: Illegal char <:> at index 2: /D:/study/L2_info/S4/Documents/S4/projet_synthese/Twisk/out/production/projet_synthese/codeC/programmeC.o
                // donc je ajoute subString(1) pour supprmier premier /
               // Path source = Paths.get(getClass().getResource("/codeC/" + nom).getPath());
             //   Path newdir = Paths.get("/tmp/twisk/");
              //  Files.copy(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
                InputStream source = getClass().getResource("/codeC/"+nom).openStream();


                File destination =new File("/tmp/twisk/"+nom);
                copier(source,destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * fonction copier
     * @param source le source
     * @param destination la destination
     * @throws IOException les exceptions répertoriées
     */
    private void copier(InputStream source, File destination)throws IOException {
        InputStream sourceFile=source;
        OutputStream destinationFile=new FileOutputStream(destination);

        byte buffer[]=new byte[512*1024];
        int nbLecture;
        while ((nbLecture=sourceFile.read(buffer))!=-1){
            destinationFile.write(buffer,0,nbLecture);
        }
        destinationFile.close();
        sourceFile.close();
    }

    /**
     * creer un fichier
     *
     * @throws IOException les exceptions
     */
    public void creerFichier(String codeC) {
        try {
            Path newdir = Paths.get("/tmp/twisk/");
            File ecritureNom = new File(newdir + "/" + "client.c");
            if (!ecritureNom.exists()) {
                try {
                    ecritureNom.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            BufferedWriter ecriture = new BufferedWriter(new FileWriter(ecritureNom));
            ecriture.write(codeC);
            ecriture.flush();
            ecriture.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *complier la commande
     * gcc   -Wall   -fPIC   -c   /tmp/twisk/client.c   -o   /tmp/twisk/client.o
     */
    public void compiler(){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec("gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o");
            p.waitFor();
            // récupération des messages sur la sortie standard et la sortie d’erreur;
            // résultat de la commande  lancée  à reprendre éventuellement et à adapter à votre code 
            BufferedReader output =new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error =new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne;
            while ((ligne = output.readLine())!=null){
                System.out.println(ligne);
            }
            while ((ligne = error.readLine())!=null){
                System.out.println(ligne);
            }
        }
        catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
    /**
     *constructer le libiraire
     * gcc   -shared   /tmp/twisk/programmeC.o     /tmp/twisk/client.o    -o   /tmp/twisk/​libTwisk​.so 
     */
    public void construireLaLibrairie(){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec("gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/codeNatif.o /tmp/twisk/client.o -o /tmp/twisk/libTwisk.so");
            p.waitFor();
            // récupération des messages sur la sortie standard et la sortie d’erreur;
            // résultat de la commande  lancée  à reprendre éventuellement et à adapter à votre code 
            BufferedReader output =new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error =new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne;
            while ((ligne = output.readLine())!=null){
                System.out.println(ligne);
            }
            while ((ligne = error.readLine())!=null){
                System.out.println(ligne);
            }
        }
        catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * tuer tous les processus C
     */
    public void killC(){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec("killall *");
            p.waitFor();
            Process p1 = runtime.exec("rm -rf /tmp/twisk/");
            p1.waitFor();
        }
        catch( IOException | InterruptedException e) {
                e.printStackTrace();
        }
    }
}