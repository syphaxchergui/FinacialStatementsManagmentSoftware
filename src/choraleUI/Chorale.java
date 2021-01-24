package choraleUI;

import choraleNoyau.Element;
import choraleNoyau.Historique;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.Era;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.util.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Chorale implements Initializable {

    static ArrayList<Element> arrayListElements = new ArrayList<Element>();
    public Button BtnAffichage;
    public TableView<Element> tableAffichage;
    public TableColumn<Element, String> columnNom;
    public TableColumn<Element, String> columnPrenom;
    public TableColumn<Element, String> columnRegistre;
    public TableColumn<Element, Integer> ColumnDettes;
    public TextField textNom;
    public TextField textPrenom;
    public DatePicker birthday;
    public Button BtnAjout;
    public ComboBox<String> registre;
    public TextField textAdresse;
    public TextField textNumero;
    public DatePicker lastMonth;
    public Button btnAdd;
    public Button BtnRecherche;
    public Button BtnApropos;
    public Pane paneDebut;
    public PasswordField textMdp;
    public CheckBox chekRegistre;
    public ComboBox<String> comboBoxRechRegistre;
    public CheckBox chekSexe;
    public ComboBox<String> comboBoxRechSexe;
    public ComboBox<String> comboBoxRechDettes;
    public CheckBox chekDettes;
    public TableView<Element> tableAffichage1;
    public TableColumn<Element, String> columnNom1;
    public TableColumn<Element, String> columnPrenom1;
    public TableColumn<Element, String> columnRegistre1;
    public TableColumn<Element, Integer> ColumnDettes1;
    public ComboBox<String> comboBoxCreeSexe;
    public TextField textResultat;
    public TextField textNumeroDet;
    public TextField textAdrDet;
    public TextField textBdayDet;
    public TextField textSexeDet;
    public TextField textFonctionDet;
    public TextField textLastDateDet;
    public TextField textPrenomDet;
    public TextField textNomDet;
    public TextField Dettes;
    public Pane paneApropos;
    public Label ErreurLabel;
    public Pane paneHistorique;
    public TableColumn<Historique, String> columnNomHist;
    public TableColumn<Historique, Integer> columnSommeHist;
    public TableColumn<Historique, Date> columnDateHist;
    public Button BtnHistorique;
    public TableView<Historique> tableHistorique;
    public DatePicker datepickerHist;
    public Button rechercheHist;
    public Button initHist;
    public Button supprimerHist;
    public Pane paneModifierMDP;
    public PasswordField texFeildAncienMdp;

    public Label textPasDeCorre;
    public Label textErreurChamps;
    public Label textErreurMpd;
    public TextField textFieldNvMdp11;
    public Button btnDebutMois;
    public Button BtnValider;
    public TextField textRechercheAffichage;
    public DatePicker datePikerDetteTable;
    public TextField textDetteTable;
    public Label labelNom;
    public Pane paneAffichage1;
    public Button btnAnnulerDetteTable;
    public Button btnValiderDetteTable;
    public Label labelTotalDettes;
    @FXML private Pane paneDemarrer;
    @FXML private Pane paneAjout;
    @FXML private Pane paneMenu;
    @FXML private Pane paneRecherche;
    @FXML private Pane paneAffichage;
    @FXML private Pane paneDetails;
    private String motDePasse = "admin";
    private int debutDumois = 20;
    private String moneySound1 = "./src/SoundEffects/moneySound1.mp3";
    private String moneySound2 = "./src/SoundEffects/moneySound2.mp3";
    private String basseSound = "./src/SoundEffects/basseSound.mp3";
    private String tenorSound = "./src/SoundEffects/tenorSound.mp3";
    private String altoSound = "./src/SoundEffects/altoSound.mp3";
    private String sopranoSound = "./src/SoundEffects/sopranoSound.mp3";

    static ArrayList<Historique> arrayListeHistoriques = new ArrayList<Historique>();



    private boolean almostKifkif(String str1, String str2){
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        char[] complet = str1.toCharArray();
        char[] result = str2.toCharArray();


        for (int i = 0; i < result.length ; i++) {
            if (complet.length < result.length) return false;
            if (complet[i] != result[i]) return false;
        }
        return true;
    }

    @FXML
    private void rechercherAffichage(){
        if(textRechercheAffichage.getText().isEmpty()){
            tableAffichage.getItems().clear();
            tableAffichage.getItems().addAll(arrayListElements);
        }
        else {
            String result = "";
            try {
                result = textRechercheAffichage.getText();
            } catch (Exception ignored) {
            }
            finally {
                String result2 = textRechercheAffichage.getText();
                tableAffichage.getItems().clear();
                for (Element arrayListElement : arrayListElements) {
                    if (almostKifkif(arrayListElement.getName(), result) || almostKifkif(arrayListElement.getLastname(), result2)) {
                        tableAffichage.getItems().add(arrayListElement);
                    }
                }
            }


        }

    }

    @FXML
    private void menu(){
        paneDebut.toFront();
        BtnAffichage.setStyle("Style.css");
        BtnAjout.setStyle("Style.css");
        BtnApropos.setStyle("Style.css");
        BtnRecherche.setStyle("Style.css");
        BtnHistorique.setStyle("Style.css");

    }

    @FXML
    private void setPaneMDP(){
        paneModifierMDP.toFront();
    }

    @FXML
    private void setPremierPane(){
        paneDemarrer.toFront();
    }

    @FXML
    private void modifierMotDePasse(){
        if (!textFieldNvMdp11.getText().isEmpty() && !texFeildAncienMdp.getText().isEmpty() ){
            if (!texFeildAncienMdp.getText().equals(motDePasse)){
                textErreurMpd.setStyle("-fx-text-fill : red");
            }
            else{
                textErreurMpd.setStyle("-fx-text-fill : white");
                motDePasse = textFieldNvMdp11.getText();
                paneDemarrer.toFront();
                    try
                    {
                        FileOutputStream fos = new FileOutputStream("mdp");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(motDePasse);
                        oos.close();
                        fos.close();
                    }
                    catch (IOException ioe)
                    {
                        ioe.printStackTrace();
                    }

            }
        }
        else{
            textErreurChamps.setStyle("-fx-text-fill : red");
        }

    }
    @FXML
    private void aPropos(){
        paneApropos.toFront();
        BtnAffichage.setStyle("Style.css");
        BtnAjout.setStyle("Style.css");
        BtnApropos.setStyle("-fx-text-fill: white;-fx-font-family : Verdana;-fx-font-size: 17; -fx-font-weight: bold;");
        BtnRecherche.setStyle("Style.css");
        BtnHistorique.setStyle("Style.css");

    }

    @FXML
    private void retourAffichage(){
        paneAffichage.toFront();
        BtnValider.setDisable(false);
    }

    @FXML
    private void afficherHistorique(){
        paneHistorique.toFront();
        BtnAffichage.setStyle("Style.css");
        BtnAjout.setStyle("Style.css");
        BtnHistorique.setStyle("-fx-text-fill: white;-fx-font-family : Verdana;-fx-font-size: 17; -fx-font-weight: bold;");
        BtnRecherche.setStyle("Style.css");
        BtnApropos.setStyle("Style.css");
        tableHistorique.getItems().clear();
        tableHistorique.getItems().addAll(arrayListeHistoriques);
    }

    @FXML
    private void initHist(){
        tableHistorique.getItems().clear();
        tableHistorique.getItems().addAll(arrayListeHistoriques);
    }

    @FXML
    private void rechercherHist(){
       if(datepickerHist.getValue() != null){
           Historique h = tableHistorique.getSelectionModel().getSelectedItem();
           Historique hist = h;

           ArrayList<Historique> arrayCopy = new ArrayList<>(arrayListeHistoriques);

           LocalDate localDate = datepickerHist.getValue();
           Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
           Date dateHist = Date.from(instant);
           SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");


           for (Historique arrayListeHistorique : arrayListeHistoriques) {
               if (!fmt.format(dateHist).equals(fmt.format(arrayListeHistorique.getDate()))) {
                   arrayCopy.remove(arrayListeHistorique);
               }
           }

           tableHistorique.getItems().clear();
           tableHistorique.getItems().addAll(arrayCopy);
       }
    }

    @FXML
    private void validerDettes() throws IOException {
        if (!Dettes.getText().isEmpty()){
            Element e = tableAffichage.getSelectionModel().getSelectedItem();
            Element element = e;
            int topay = Integer.parseInt(Dettes.getText());
            element.SousTopay(topay);
            Dettes.clear();
            textLastDateDet.setText(element.getTopay() +" DA");
            int month = 0;
            month = topay / 500;
            element.setLastMonthPayed(element.getLastMonthPayed()+month);
            textLastDateDet.setText(element.getTopay() +" DA");
            tableAffichage.getItems().clear();
            tableAffichage.getItems().addAll(arrayListElements);
            Historique historique = new Historique(element.getName()+" "+element.getLastname(), new Date(), topay);
            arrayListeHistoriques.add(historique);
            SauvgarderHistorique();
            SauvgarderElements();
            BtnValider.setDisable(true);
            long som = 0;
            for (Element arrayListElement : arrayListElements) {
                som = som + arrayListElement.getTopay();
            }
            labelTotalDettes.setText("Total des dettes = " +som + "DZD");
        }
    }

    @FXML
    private void afficherDetails(){
        paneDetails.toFront();

        Element e = tableAffichage.getSelectionModel().getSelectedItem();
        Element element = e;

        textAdrDet.setText(element.getAdresse());
        textNomDet.setText(element.getName());
        textPrenomDet.setText(element.getLastname());
        textNumeroDet.setText(Long.toString(element.getNum()));
        textLastDateDet.setText((element.getTopay())+" DA");
        textBdayDet.setText(element.getStrBday());
        textSexeDet.setText(element.getSexe());
        textFonctionDet.setText(element.getFunction());

    }

    @FXML
    private void addElement() throws IOException {
        if(textAdresse.getText().isEmpty() || textNom.getText().isEmpty() || textPrenom.getText().isEmpty() || textNumero.getText().isEmpty() || birthday.getValue() == null || lastMonth.getValue() == null || registre.getValue() == null || comboBoxCreeSexe.getValue() == null ){
            ErreurLabel.setStyle("-fx-text-fill: red");
            int i = 0;
            if(textAdresse.getText().isEmpty()) {
                textAdresse.setStyle("-fx-border-color: #FF4C4C");
                ErreurLabel.setText("Veuillez remplir le champ ADRESSE !");
                i++;
            } else textAdresse.setStyle("-fx-border-color: transparent");
            if(textNom.getText().isEmpty()) {
                textNom.setStyle("-fx-border-color: #FF4C4C");
                ErreurLabel.setText("Veuillez remplir le champ NOM !");
                i++;
            } else textNom.setStyle("-fx-border-color: transparent");
            if(textPrenom.getText().isEmpty()) {
                textPrenom.setStyle("-fx-border-color: #FF4C4C");
                ErreurLabel.setText("Veuillez remplir le champ PRENOM !");
                i++;
            } else textPrenom.setStyle("-fx-border-color: transparent");
            if(textNumero.getText().isEmpty() ) {
                textNumero.setStyle("-fx-border-color: #FF4C4C");
                ErreurLabel.setText("Veuillez remplir le champ NUMERO !");
                i++;
            } else textNumero.setStyle("-fx-border-color: transparent");
            if(birthday.getValue() == null) {
                birthday.setStyle("-fx-border-color: #FF4C4C");
                ErreurLabel.setText("Veuillez remplir le champ DATE DE NAISSANCE !");
                i++;
            } else birthday.setStyle("-fx-border-color: transparent");
            if(lastMonth.getValue() == null) {
                lastMonth.setStyle("-fx-border-color: #FF4C4C");
                ErreurLabel.setText("Veuillez remplir le champ DETTE !");
                i++;
            } else lastMonth.setStyle("-fx-border-color: transparent");
            if(registre.getValue() == null) {
                registre.setStyle("-fx-border-color: #FF4C4C");
                ErreurLabel.setText("Veuillez remplir le champ REGISTRE !");
                i++;
            } else registre.setStyle("-fx-border-color: transparent");
            if(comboBoxCreeSexe.getValue() == null) {
                comboBoxCreeSexe.setStyle("-fx-border-color: #FF4C4C");
                ErreurLabel.setText("Veuillez remplir le champ SEXE !");
                i++;
            } else comboBoxCreeSexe.setStyle("-fx-border-color: transparent");

            if(i>1){
                ErreurLabel.setText("Veuillez remplir les "+ i +" champs vides !");
            }

        }
        else {
            LocalDate localDate = birthday.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date bday = Date.from(instant);

            LocalDate localDate1 = lastMonth.getValue();
            Instant instant1 = Instant.from(localDate1.atStartOfDay(ZoneId.systemDefault()));
            Date lastDate = Date.from(instant1);

            String fonction = registre.getSelectionModel().getSelectedItem();
            String sexe = comboBoxCreeSexe.getSelectionModel().getSelectedItem();

            Boolean continu = true;

            try {
                long num = Long. parseLong(textNumero.getText());
                Element element = new Element(textNom.getText(), textPrenom.getText(), bday, fonction,num, textAdresse.getText(), lastDate, sexe);
                arrayListElements.add(element);
            }
            catch (Exception e){
                textNumero.setStyle("-fx-border-color: #FF4C4C");
                ErreurLabel.setText("Veuillez saisir un numero valide !");
                continu = false;
            }
            if(continu) {
                ErreurLabel.setStyle("-fx-text-fill: white");
                textNumero.clear();
                textPrenom.clear();
                textNom.clear();
                textAdresse.clear();
                registre.cancelEdit();
                SauvgarderElements();
                textAdresse.setStyle("-fx-border-color: transparent ");
                textNom.setStyle("-fx-border-color: transparent");
                textPrenom.setStyle("-fx-border-color: transparent");
                registre.setStyle("-fx-border-color: transparent");
                birthday.setStyle("-fx-border-color: transparent");
                lastMonth.setStyle("-fx-border-color: transparent");
                comboBoxCreeSexe.setStyle("-fx-border-color: transparent");
                textNumero.setStyle("-fx-border-color: transparent");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ErreurLabel.setStyle("-fx-text-fill: green");
                        ErreurLabel.setText("Membre ajouté avec succès !");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ErreurLabel.setStyle("-fx-text-fill: white");
                    }
                }).start();
            }
        }
    }
    @FXML
    private void demarrer(){
        if(textMdp.getText().equals(motDePasse)){
            paneMenu.toFront();
            paneDebut.toFront();
        }
        else {
            textMdp.clear();
            textMdp.setPromptText("Incorrect");
        }
    }

    @FXML
    private void supprimer() throws IOException {
        Element e = tableAffichage.getSelectionModel().getSelectedItem();
        arrayListElements.remove(e);
        tableAffichage.getItems().clear();
        tableAffichage.getItems().addAll(arrayListElements);
        SauvgarderElements();
        long som = 0;
        for (Element arrayListElement : arrayListElements) {
            som = som + arrayListElement.getTopay();
        }
        labelTotalDettes.setText("Total des dettes = " +som + "DZD");
    }

    @FXML
    private void reset(){
        tableAffichage1.getItems().clear();
        tableAffichage1.getItems().addAll(arrayListElements);
    }

    @FXML
    private void retour(){
        paneDebut.toFront();
        BtnAjout.setStyle("Style.css");
    }

    @FXML
    private void setPaneRecherche(){
        paneRecherche.toFront();

        BtnAjout.setStyle("Style.css");
        BtnApropos.setStyle("Style.css");
        BtnHistorique.setStyle("Style.css");
        BtnAffichage.setStyle("Style.css");
        BtnRecherche.setStyle("-fx-text-fill: white;-fx-font-family : Verdana;-fx-font-size: 17; -fx-font-weight: bold;");
        tableAffichage1.getItems().clear();
        tableAffichage1.getItems().addAll(arrayListElements);
    }

    @FXML
    private void resetDate(){
        LocalDate localDate = LocalDate.now();
        datepickerHist.setValue(localDate);
    }


    @FXML
    private void recherche(){
        ArrayList<Element> copy = new ArrayList<Element>(arrayListElements);

            if(chekRegistre.isSelected()){
                String registre = comboBoxRechRegistre.getSelectionModel().getSelectedItem();
                for (Element arrayListElement : arrayListElements) {
                    if (!arrayListElement.getFunction().equals(registre)) {
                        copy.remove(arrayListElement);
                    }
                }
            }

        if(chekSexe.isSelected()){
            String sexe = comboBoxRechSexe.getSelectionModel().getSelectedItem();
            for (Element arrayListElement : arrayListElements) {
                if (!arrayListElement.getSexe().equals(sexe)) {
                    copy.remove(arrayListElement);
                }
            }
        }

        if(chekDettes.isSelected()){
            String dettes = comboBoxRechDettes.getSelectionModel().getSelectedItem();
            for (Element arrayListElement : arrayListElements) {
                    if(dettes.equals("< 1000 DA")){
                        if (arrayListElement.getTopay() <= -1000 ){
                            copy.remove(arrayListElement);
                        }
                    }
                    if(dettes.equals(">= 1000 DA")) {
                        if (arrayListElement.getTopay() > -1000 ){
                            copy.remove(arrayListElement);
                        }
                    }


            }
        }

        textResultat.setText(copy.size()+ " Element(s)");

        tableAffichage1.getItems().clear();
        tableAffichage1.getItems().addAll(copy);



    }

    @FXML
    private void ajouter(){
        paneAjout.toFront();
        ErreurLabel.setStyle("-fx-text-fill: white");
        textNumero.clear(); textPrenom.clear(); textNom.clear(); textAdresse.clear(); registre.cancelEdit();
        textAdresse.setStyle("-fx-border-color: transparent ");
        textNom.setStyle("-fx-border-color: transparent");
        textPrenom.setStyle("-fx-border-color: transparent");
        registre.setStyle("-fx-border-color: transparent");
        birthday.setStyle("-fx-border-color: transparent");
        lastMonth.setStyle("-fx-border-color: transparent");
        comboBoxCreeSexe.setStyle("-fx-border-color: transparent");
        textNumero.setStyle("-fx-border-color: transparent");
        BtnAffichage.setStyle("Style.css");
        BtnApropos.setStyle("Style.css");
        BtnHistorique.setStyle("Style.css");
        BtnRecherche.setStyle("Style.css");
        BtnAjout.setStyle("-fx-text-fill: white;\n" +
                "    -fx-font-family : Verdana;\n" +
                "    -fx-font-size: 17;\n" +
                "    -fx-font-weight: bold;");
    }

    @FXML
    private void affichage(){
        paneAffichage.toFront();
        BtnAjout.setStyle("Style.css");
        BtnApropos.setStyle("Style.css");
        BtnRecherche.setStyle("Style.css");
        BtnHistorique.setStyle("Style.css");
        BtnAffichage.setStyle("-fx-text-fill: white;\n" +
                "    -fx-font-family : Verdana;\n" +
                "    -fx-font-size: 17;\n" +
                "    -fx-font-weight: bold;");
        tableAffichage.getItems().clear();
        tableAffichage.getItems().addAll(arrayListElements);
        long som = 0;
        for (Element arrayListElement : arrayListElements) {
            som = som + arrayListElement.getTopay();
        }
        labelTotalDettes.setText("Total des dettes = " +som + "DZD");
    }

    @FXML
    private void nouveauMois() throws IOException {
        for (Element arrayListElement : arrayListElements) {
            arrayListElement.AddTopay(1);
        }
        tableAffichage.getItems().clear();
        tableAffichage.getItems().addAll(arrayListElements);
        SauvgarderElements();
        btnDebutMois.setDisable(true);
        Historique hist = new Historique("Admin (Début mois)", new Date(), -500);
        arrayListeHistoriques.add(hist);
        SauvgarderHistorique();
        long som = 0;
        for (Element arrayListElement : arrayListElements) {
            som = som + arrayListElement.getTopay();
        }
        labelTotalDettes.setText("Total des dettes = " +som + "DZD");
    }

   /* private void checkDate(){
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();

        for (Element arrayListElement : arrayListElements) {
            if (day == 20 && (month - arrayListElement.getLastMonthPayed() > 0)) {
                if (year> arrayListElement.getLastYearPayed()){
                    arrayListElement.AddTopay((12-arrayListElement.getLastMonthPayed())+month);
                }
                if (year == arrayListElement.getLastYearPayed()){
                    arrayListElement.AddTopay(month - arrayListElement.getLastMonthPayed());
                }
            }
        }

    }*/

    private void SauvgarderElements() throws IOException {
        try
        {
            FileOutputStream fos = new FileOutputStream("employeeData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayListElements);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    private void SauvgarderHistorique() throws IOException {
        try
        {
            FileOutputStream fos = new FileOutputStream("Historique");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayListeHistoriques);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    private void restaurerHistorique(){
        try
        {
            FileInputStream fis = new FileInputStream("Historique");
            ObjectInputStream ois = new ObjectInputStream(fis);

            arrayListeHistoriques= (ArrayList<Historique>) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }

    private void restaurerElements(){
        try
        {
            FileInputStream fis = new FileInputStream("employeeData");
            ObjectInputStream ois = new ObjectInputStream(fis);

            arrayListElements= (ArrayList<Element>) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }

    private void restaurerMDP(){
        try
        {
            FileInputStream fis = new FileInputStream("mdp");
            ObjectInputStream ois = new ObjectInputStream(fis);

            motDePasse = (String) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    @FXML
    private void validerDetteTable(Element rowData){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        registre.getItems().addAll("Basse","Tenor", "Alto", "Soprano","Musicien");
        comboBoxCreeSexe.getItems().addAll("Homme", "Femme");

        btnDebutMois.setDisable(false);

        comboBoxRechRegistre.getItems().addAll("Basse","Tenor", "Alto", "Soprano","Musicien");
        comboBoxRechDettes.getItems().addAll(">= 1000 DA", "< 1000 DA");
        comboBoxRechSexe.getItems().addAll("Homme", "Femme");

        textMdp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    demarrer();
                    event.consume();
                }
            }
        });

        chekRegistre.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(chekRegistre.isSelected()){
                            comboBoxRechRegistre.setDisable(false);
                        }else comboBoxRechRegistre.setDisable(true);
                    }
                });
        chekDettes.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(chekDettes.isSelected()){
                            comboBoxRechDettes.setDisable(false);
                        }else comboBoxRechDettes.setDisable(true);
                    }
                });
        chekSexe.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(chekSexe.isSelected()){
                            comboBoxRechSexe.setDisable(false);
                        }else comboBoxRechSexe.setDisable(true);
                    }
                });

        comboBoxRechRegistre.setDisable(true);
        comboBoxRechDettes.setDisable(true);
        comboBoxRechSexe.setDisable(true);

        comboBoxRechRegistre.valueProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) {
                recherche();
            }
        });

        comboBoxRechSexe.valueProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) {
                recherche();
            }
        });
        comboBoxRechDettes.valueProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) {
                recherche();
            }
        });

        LocalDate localDate = LocalDate.now();
        datepickerHist.setValue(localDate);



        tableAffichage.setRowFactory(tv -> {
            TableRow<Element> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    textDetteTable.setStyle("-fx-border-color: transparent");
                    Element rowData = row.getItem();
                    paneAffichage1.toFront();
                    datePikerDetteTable.setValue(localDate);
                    labelNom.setText(rowData.getName() + " "+rowData.getLastname());
                    btnValiderDetteTable.setOnAction(e -> {
                        if(textDetteTable.getText().isEmpty()){
                            textDetteTable.setStyle("-fx-border-color: #FF4C4C");
                        }
                        else{
                            textDetteTable.setStyle("-fx-border-color: transparent");
                            try{
                                int montant = Integer.valueOf(textDetteTable.getText());
                                LocalDate localDate1 = datePikerDetteTable.getValue();
                                Instant instant = Instant.from(localDate1.atStartOfDay(ZoneId.systemDefault()));
                                Date date = Date.from(instant);
                                rowData.SousTopay(montant);
                                int month = 0;
                                month = montant / 500;
                                rowData.setLastMonthPayed(rowData.getLastMonthPayed()+month);
                                tableAffichage.getItems().clear();
                                tableAffichage.getItems().addAll(arrayListElements);
                                Historique historique = new Historique(rowData.getName()+" "+rowData.getLastname(), date, montant);
                                arrayListeHistoriques.add(historique);
                                SauvgarderHistorique();
                                SauvgarderElements();
                                paneAffichage1.toBack();
                                textDetteTable.clear();
                                long som = 0;
                                for (Element arrayListElement : arrayListElements) {
                                    som = som + arrayListElement.getTopay();
                                }
                                labelTotalDettes.setText("Total des dettes = " +som + "DZD");


                            }
                            catch (Exception ex){
                                textDetteTable.setStyle("-fx-border-color: #FF4C4C");
                            }
                        }
                    });

                    btnAnnulerDetteTable.setOnAction(e -> {
                        paneAffichage1.toBack();
                        textDetteTable.clear();
                    });

                    textDetteTable.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.ENTER) {
                                if(textDetteTable.getText().isEmpty()){
                                    textDetteTable.setStyle("-fx-border-color: #FF4C4C");
                                }
                                else{
                                    textDetteTable.setStyle("-fx-border-color: transparent");
                                    try{
                                        int montant = Integer.valueOf(textDetteTable.getText());
                                        LocalDate localDate1 = datePikerDetteTable.getValue();
                                        Instant instant = Instant.from(localDate1.atStartOfDay(ZoneId.systemDefault()));
                                        Date date = Date.from(instant);
                                        rowData.SousTopay(montant);
                                        int month = 0;
                                        month = montant / 500;
                                        rowData.setLastMonthPayed(rowData.getLastMonthPayed()+month);
                                        tableAffichage.getItems().clear();
                                        tableAffichage.getItems().addAll(arrayListElements);
                                        Historique historique = new Historique(rowData.getName()+" "+rowData.getLastname(), date, montant);
                                        arrayListeHistoriques.add(historique);
                                        SauvgarderHistorique();
                                        SauvgarderElements();
                                        paneAffichage1.toBack();
                                        textDetteTable.clear();
                                        long som = 0;
                                        for (Element arrayListElement : arrayListElements) {
                                            som = som + arrayListElement.getTopay();
                                        }
                                        labelTotalDettes.setText("Total des dettes = " +som + "DZD");
                                    }
                                    catch (Exception ex){
                                        textDetteTable.setStyle("-fx-border-color: #FF4C4C");
                                    }
                                }
                                event.consume();
                            }
                        }
                    });
                }
            });
            return row;
        });


            tableAffichage.getColumns().addAll(columnNom, columnPrenom, columnRegistre, ColumnDettes);
            columnNom.setCellValueFactory(new PropertyValueFactory<Element, String>("name"));
            columnPrenom.setCellValueFactory(new PropertyValueFactory<Element, String>("lastname"));
            columnRegistre.setCellValueFactory(new PropertyValueFactory<Element, String>("function"));
            ColumnDettes.setCellValueFactory(new PropertyValueFactory<Element, Integer>("topay"));

            tableAffichage1.getColumns().addAll(columnNom1, columnPrenom1, columnRegistre1, ColumnDettes1);
            columnNom1.setCellValueFactory(new PropertyValueFactory<Element, String>("name"));
            columnPrenom1.setCellValueFactory(new PropertyValueFactory<Element, String>("lastname"));
            columnRegistre1.setCellValueFactory(new PropertyValueFactory<Element, String>("function"));
            ColumnDettes1.setCellValueFactory(new PropertyValueFactory<Element, Integer>("topay"));

            tableHistorique.getColumns().addAll(columnNomHist, columnSommeHist, columnDateHist);
            columnNomHist.setCellValueFactory(new PropertyValueFactory<Historique, String>("name"));
            columnSommeHist.setCellValueFactory(new PropertyValueFactory<Historique, Integer>("money"));
            columnDateHist.setCellValueFactory(new PropertyValueFactory<Historique, Date>("date"));

            restaurerElements();
            restaurerHistorique();
            restaurerMDP();


    }

}
