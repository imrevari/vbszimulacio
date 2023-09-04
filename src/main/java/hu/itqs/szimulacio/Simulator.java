package hu.itqs.szimulacio;

import hu.itqs.szimulacio.domain.*;

import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Simulator implements AutoCloseable{

    private EntityManager em;
    private EntityTransaction etx;

    private static Simulator simulator = null;

    private Simulator() {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("simulator");

        this.em = factory.createEntityManager();
        this.etx = em.getTransaction();

    }

    public static Simulator getInstance(){
        if(Objects.isNull(simulator)){
            simulator = new Simulator();
            simulator.uploadSQL();
            simulator.createCouples();
        }
        return simulator;
    }

    public List<FootballTeam> listAllTeams(){
        return em.createQuery("SELECT b FROM FootballTeam b").getResultList();
    }

    public List<Husband> listAllHusbands(){
        return em.createQuery("SELECT h FROM Husband h").getResultList();
    }

    public List<Wife> listAllWives(){
        return em.createQuery("SELECT w FROM Wife w").getResultList();
    }

    public List<Couple> listAllCouples(){
        return em.createQuery("SELECT c FROM Couple c").getResultList();
    }

    public List<Match> listAllMatches(){
        return em.createQuery("SELECT m From Match m").getResultList();
    }

    public void saveMatch(Match match){
        etx.begin();
        em.persist(match);
        etx.commit();
    }

    public void updateCouple(Couple couple){
        etx.begin();
        if (em.find(Couple.class, couple.getId()) != null) {
            em.merge(couple);
        }else{
            System.out.println("Sorry, couple ID do not exist");
        }
        etx.commit();
    }

    public void updateHusband(Husband husband){
        etx.begin();
        if (em.find(Husband.class, husband.getId()) != null) {
            em.merge(husband);
        }else{
            System.out.println("Sorry, husband ID do not exist");
        }
        etx.commit();
    }

    public void updateWife(Wife wife){
        etx.begin();
        if (em.find(Wife.class, wife.getId()) != null) {
            em.merge(wife);
        }else{
            System.out.println("Sorry, wife ID do not exist");
        }
        etx.commit();
    }

    public Match createAndSaveRandomMatch(){
        Match match = createRandomMatch();
        saveMatch(match);
        return match;
    }

    public void addMatchToRandomCouples(Match match){
        List<Couple> list = listAllCouples();
        List<Couple> randomList = listOfRandomCouples(list);

        for (Couple couple: randomList) {
            couple.watchAMatch(match);
            updateHusband(couple.getHusband());
            updateWife(couple.getWife());
            updateCouple(couple);
        }
    }

    public Double avarageBearConsumed(){
        List<Husband> husbands = listAllHusbands();
        Double totalBear = 0D;
        for (Husband husband: husbands) {
            totalBear += husband.getConsumedBear();
        }
        return new Double(totalBear/husbands.size());
    }

    public Integer totalSpareTime(){
        List<Wife> wives = listAllWives();
        Integer total = 0;
        for (Wife wife: wives) {
            total += wife.getSpareTime();
        }
        return total;
    }

    @Override
    public void close() throws Exception {
        em.close();
    }

    private void uploadSQL(){
        String filePath = "./src/main/resources/data-h2.sql";
        etx.begin();
        try {
            InputStream inputStream = new FileInputStream(new File(filePath));
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sql = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null){
                sql.append(line).append(' ');
            }

            Query q = em.createNativeQuery(sql.toString());
            q.executeUpdate();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        etx.commit();
    }

    private void createCouples() {
        List<Husband> husbands = listAllHusbands();
        List<Wife> wives = listAllWives();
        for (int i = 0; i < husbands.size(); i++){
            etx.begin();
            em.persist(new Couple(husbands.get(i), wives.get(i)));
            etx.commit();
        }
    }

    private Match createRandomMatch(){
        Random rand = new Random();
        List<FootballTeam> listOfTeams = listAllTeams();
        Integer randomExtraTime = rand.nextInt(11);
        Integer randomRateOfMatchSuccess = rand.nextInt((100 ) + 1);
        int[] indexes = twoRandomIndexes(listOfTeams.size() - 1);
        return new Match(randomExtraTime, randomRateOfMatchSuccess, listOfTeams.get(indexes[0]), listOfTeams.get(indexes[1]));
    }

    private int[] twoRandomIndexes(Integer maxIndex){
        Random rand = new Random();
        int index1 = rand.nextInt(maxIndex  + 1);
        int index2 = rand.nextInt( maxIndex + 1);
        while (index1 == index2){
            index2 = rand.nextInt( maxIndex + 1);
        }
        int[] toReturn = {index1, index2};
        return toReturn;
    }

    private List<Couple> listOfRandomCouples(List<Couple> couples){
        List<Couple> listToReturn = new ArrayList<>();
        for (Couple couple: couples) {
            if(Math.round( Math.random()) == 1){
                listToReturn.add(couple);
            }
        }
        return listToReturn;
    }
}
