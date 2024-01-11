package ba.unsa.etf.rpr.lv10lv11;

import javafx.beans.property.SimpleObjectProperty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {
    private SimpleObjectProperty<Drzava> trenutnaDrzava = new SimpleObjectProperty<>(new Drzava());
    private SimpleObjectProperty<Grad> trenutniGrad = new SimpleObjectProperty<>(new Grad());
    public Drzava getTrenutnaDrzava() {
        return trenutnaDrzava.get();
    }

    public SimpleObjectProperty<Drzava> trenutnaDrzavaProperty() {
        return trenutnaDrzava;
    }

    public void setTrenutnaDrzava(Drzava trenutnaDrzava) {
        this.trenutnaDrzava.set(trenutnaDrzava);
    }

    public Grad getTrenutniGrad() {
        return trenutniGrad.get();
    }

    public SimpleObjectProperty<Grad> trenutniGradProperty() {
        return trenutniGrad;
    }

    public void setTrenutniGrad(Grad trenutniGrad) {
        this.trenutniGrad.set(trenutniGrad);
    }

    private static GeografijaDAO instance = null;
    private static Connection conn;

    private GeografijaDAO(){
        String url = "jdbc:sqlite:identifier.sqlite";
        try{
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM grad");
        }
        catch (SQLException e) {
            regenerisiBazu();
        }
    }
    public static GeografijaDAO getInstance(){
        if(instance == null) instance = new GeografijaDAO();
        return instance;
    }
    public static void removeInstance() {
        try{
            instance.conn.close();
            instance = null;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void vratiNaDefault() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE grad SET drzava = NULL;");
        stmt.executeUpdate("DELETE FROM drzava;");
        stmt.executeUpdate("DELETE FROM grad;");
        regenerisiBazu();
    }
    private void regenerisiBazu(){
        try{
            Scanner ulaz = new Scanner(new FileInputStream("geografijabaza.db.sql"));
            StringBuilder sqlUpit = new StringBuilder();
            while(ulaz.hasNext()){
                sqlUpit.append(ulaz.nextLine());
                if(sqlUpit.length() > 1 && sqlUpit.charAt(sqlUpit.length() - 1) == ';'){
                    try{
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit.toString());
                        sqlUpit.setLength(0);
                    }
                    catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    private int zadnjiIndeksGrada(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT id FROM grad ORDER BY id DESC;");
            return result.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    private int zadnjiIndeksDrzave(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT id FROM drzava ORDER BY id DESC;");
            return result.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public ArrayList<Grad> gradovi(){
        ArrayList<Grad> g = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT g.naziv, g.broj_stanovnika, d.naziv " +
                    "FROM grad g, drzava d " +
                    "WHERE g.drzava = d.id;");
            while(result.next()){
                g.add(new Grad(result.getString(1), result.getInt(2), result.getString(3)));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }
    public ArrayList<Drzava> drzave(){
        ArrayList<Drzava> d = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT d.naziv, g.naziv " +
                    "FROM drzava d, grad g " +
                    "WHERE d.glavni_grad = g.id;");
            while(result.next()){
                d.add(new Drzava(result.getString(1), result.getString(2)));
            }
            result = stmt.executeQuery("SELECT d.naziv " +
                    "FROM drzava d " +
                    "WHERE d.glavni_grad IS NULL;");
            while(result.next()){
                d.add(new Drzava(result.getString(1), null));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }
    public Grad glavniGrad(String drzava){
        try{
            PreparedStatement psGlavniGrad = conn.prepareStatement("SELECT g.naziv, g.broj_stanovnika " +
                    "FROM grad g, drzava d " +
                    "WHERE d.naziv = ? AND d.glavni_grad = g.id;");
            psGlavniGrad.setString(1, drzava);
            ResultSet result = psGlavniGrad.executeQuery();

            if(!result.next()) return null;
            else return new Grad(result.getString(1), result.getInt(2), drzava);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void obrisiGrad(String grad){
        try{
            PreparedStatement ps1 = conn.prepareStatement("SELECT drzava FROM grad WHERE naziv = ?;");
            ps1.setString(1, grad);
            ResultSet rs = ps1.executeQuery();

            PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM grad WHERE drzava = ?;");
            ps2.setInt(1, rs.getInt(1));
            ResultSet rs2 = ps2.executeQuery();

            if(!rs2.next()){
                PreparedStatement ps3 = conn.prepareStatement("UPDATE drzava SET naziv = NULL " +
                        "WHERE glavni_grad = (SELECT g.id FROM grad g WHERE g.naziv = ?);");
                ps3.setString(1, grad);
                ps3.executeUpdate();
            }

            PreparedStatement ps = conn.prepareStatement("UPDATE drzava SET glavni_grad = NULL " +
                    "WHERE glavni_grad = (SELECT g.id FROM grad g WHERE g.naziv = ?);");
            ps.setString(1, grad);
            ps.executeUpdate();

            PreparedStatement ps4 = conn.prepareStatement("DELETE FROM grad WHERE naziv = ?;");
            ps4.setString(1, grad);
            ps4.executeUpdate();

            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM drzava WHERE naziv IS NULL;");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void obrisiDrzavu(String drzava){
        try{
            PreparedStatement ps2 = conn.prepareStatement("UPDATE grad SET drzava = NULL " +
                    "WHERE drzava = (SELECT d.id FROM drzava d WHERE d.naziv = ?);");
            ps2.setString(1, drzava);
            ps2.executeUpdate();

            PreparedStatement ps1 = conn.prepareStatement("DELETE FROM drzava WHERE naziv = ?;");
            ps1.setString(1, drzava);
            ps1.executeUpdate();

            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM grad WHERE drzava IS NULL;");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void dodajGrad(Grad grad){
        try{
            PreparedStatement ps1 = conn.prepareStatement("INSERT INTO grad VALUES (?, ?, ?, NULL);");
            int j = zadnjiIndeksGrada()+1;
            ps1.setInt(1, j);
            ps1.setString(2, grad.getNaziv());
            ps1.setInt(3, grad.getBrojStanovnika());
            ps1.executeUpdate();

            if(!(grad.getDrzava().isEmpty())){
                int index = 0;

                if(nadjiDrzavu(grad.getDrzava()) == null){
                    PreparedStatement ps2 = conn.prepareStatement("INSERT INTO drzava VALUES (?, ?, NULL);");
                    int i = zadnjiIndeksDrzave()+1;
                    ps2.setInt(1, i);
                    ps2.setString(2, grad.getDrzava());
                    ps2.executeUpdate();

                    index = i;
                }
                else{
                    PreparedStatement ps2 = conn.prepareStatement("SELECT id FROM drzava WHERE naziv = ?;");
                    ps2.setString(1, grad.getDrzava());
                    ResultSet res = ps2.executeQuery();

                    index = res.getInt(1);
                }

                PreparedStatement ps3 = conn.prepareStatement("UPDATE grad SET drzava = ? WHERE id = ?;");
                ps3.setInt(1, index);
                ps3.setInt(2, j);
                ps3.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void dodajDrzavu(Drzava drzava){
        try{
            PreparedStatement ps1 = conn.prepareStatement("INSERT INTO drzava VALUES (?, ?, NULL);");
            int i = zadnjiIndeksDrzave()+1;
            ps1.setInt(1, i);
            ps1.setString(2, drzava.getNaziv());
            ps1.executeUpdate();

            if(!(drzava.getGlavni_grad().isEmpty())){
                int index = 0;

                if(nadjiGrad(drzava.getGlavni_grad()) == null){
                    PreparedStatement ps2 = conn.prepareStatement("INSERT INTO grad VALUES (?, ?, NULL, ?);");
                    int j = zadnjiIndeksGrada()+1;
                    ps2.setInt(1, j);
                    ps2.setString(2, drzava.getGlavni_grad());
                    ps2.setInt(3, i);
                    ps2.executeUpdate();

                    index = j;
                }
                else{
                    PreparedStatement ps2 = conn.prepareStatement("SELECT id FROM grad WHERE naziv = ?;");
                    ps2.setString(1, drzava.getGlavni_grad());
                    ResultSet res = ps2.executeQuery();

                    index = res.getInt(1);
                }

                PreparedStatement ps3 = conn.prepareStatement("UPDATE drzava SET glavni_grad = ? WHERE id = ?;");
                ps3.setInt(1, index);
                ps3.setInt(2, i);
                ps3.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void izmijeniGrad(Grad grad){
        try{
            PreparedStatement ps1 = conn.prepareStatement("SELECT d.naziv FROM grad g, drzava d WHERE g.naziv = ? AND g.drzava = d.id;");
            ps1.setString(1, grad.getNaziv());
            ResultSet res = ps1.executeQuery();
            if(!res.next()) return;
            String oldDrzava = res.getString(1);

            if(nadjiDrzavu(grad.getDrzava()) == null){
                dodajDrzavu(new Drzava(grad.getDrzava(), ""));
            }

            PreparedStatement ps = conn.prepareStatement("UPDATE grad SET (broj_stanovnika, drzava) = (SELECT ?, d.id FROM drzava d WHERE d.naziv = ?) " +
                    "WHERE naziv = ?;");
            ps.setInt(1, grad.getBrojStanovnika());
            ps.setString(2, grad.getDrzava());
            ps.setString(3, grad.getNaziv());
            ps.executeUpdate();

            if(!oldDrzava.equals(grad.getDrzava())){
                PreparedStatement ps3 = conn.prepareStatement("SELECT * FROM grad WHERE drzava = (SELECT d.id FROM drzava d WHERE naziv = ?);");
                ps3.setString(1, oldDrzava);
                ResultSet result = ps3.executeQuery();

                if(!result.next()){
                    PreparedStatement ps2 = conn.prepareStatement("DELETE FROM drzava WHERE naziv = ?;");
                    ps2.setString(1, oldDrzava);
                    ps2.executeUpdate();
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void izmijeniDrzavu(Drzava drzava){
        try{
            if(nadjiGrad(drzava.getGlavni_grad()) == null){
                dodajGrad(new Grad(drzava.getGlavni_grad(), 0, drzava.getNaziv()));
            }

            PreparedStatement ps = conn.prepareStatement("UPDATE drzava SET (naziv, glavni_grad) = (SELECT ?, g.id FROM grad g WHERE g.naziv = ?) " +
                    "WHERE naziv = ?;");
            ps.setString(1, drzava.getNaziv());
            ps.setString(2, drzava.getGlavni_grad());
            ps.setString(3, drzava.getNaziv());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Drzava nadjiDrzavu(String drzava){
        try{
            PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM drzava WHERE naziv = ?;");
            ps1.setString(1, drzava);
            ResultSet res = ps1.executeQuery();
            if(!res.next()) return null;

            PreparedStatement ps = conn.prepareStatement("SELECT g.naziv FROM drzava d, grad g WHERE d.naziv = ? AND d.glavni_grad = g.id;");
            ps.setString(1, drzava);
            ResultSet result = ps.executeQuery();
            return new Drzava(drzava, result.getString(1));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Grad nadjiGrad(String grad){
        try{
            PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM grad WHERE naziv = ?;");
            ps1.setString(1, grad);
            ResultSet res = ps1.executeQuery();
            if(!res.next()) return null;

            PreparedStatement ps = conn.prepareStatement("SELECT d.naziv, g.broj_stanovnika FROM drzava d, grad g WHERE g.naziv = ? AND g.drzava = d.id;");
            ps.setString(1, grad);
            ResultSet result = ps.executeQuery();
            return new Grad(grad, result.getInt(1), result.getString(2));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}