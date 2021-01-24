package choraleNoyau;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Element implements Serializable {
    private String name;
    private String sexe;
    private String lastname;
    private int ident;
    private String function;
    private int topay;
    private static int matricule=2018;
    private Date birthday;
    private long num;
    private String adresse;
    private int lastMonthPayed;
    private int lastYearPayed;
    private Date dateLast;

    public int getLastMonthPayed() {
        return lastMonthPayed;
    }

    public void setLastMonthPayed(int lastMonthPayed) {
        this.lastMonthPayed = lastMonthPayed;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getLastYearPayed() {
        return lastYearPayed;
    }

    public void setLastYearPayed(int lastYearPayed) {
        this.lastYearPayed = lastYearPayed;
    }

    public String getDateLast() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(dateLast);
        return strDate;
    }

    public String getStrBday() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(birthday);
        return strDate;
    }

    public Element(String name, String lastname, Date birthDay, String function, long num, String adresse, Date lastdate, String sexe) {
        this.name= name;
        this.sexe =sexe;
        this.lastname = lastname;
        this.function = function;
        this.adresse = adresse;
        this.num = num;
        this.birthday = birthDay;
        ident = matricule;
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        LocalDate localDate1 = lastdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        lastMonthPayed = localDate1.getMonthValue();
        lastYearPayed = localDate1.getYear();
        if (year > lastYearPayed){
            this.topay = (-500) * ((12-lastMonthPayed)+month);
        }
        if (year == lastYearPayed){
            this.topay = (-500)*(month-lastMonthPayed);
        }

        matricule++;
        dateLast = lastdate;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getMatricule() {
        return ident;
    }

    public int getTopay() {
        return topay;
    }

    public void AddTopay(int NbrMois) {
        this.topay = this.topay + (-500*NbrMois);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void SousTopay(int topay) {
        this.topay = this.topay + topay;
    }

    public String getFunction() {
        return function;
    }

}
