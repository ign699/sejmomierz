import javax.lang.model.element.Name;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Jan on 03.01.2017.
 */
public class ArgumentsParser {
    private String[] arguments;
    private int kadencja;
    private String nazwa;
    private boolean reload = false;
    private boolean spendingSum = false;
    private boolean littlespendings = false;
    private boolean avgSpendings = false;
    private boolean mostTraveles = false;
    private boolean mostDaysAbroad = false;
    private boolean mostExpensiveTravels = false;
    private boolean beenToItaly = false;




    public ArgumentsParser(String[] arguments){
        this.arguments = arguments;
    }


    public void parseArguemnts() throws Exception {
        for(int i = 0 ; i < arguments.length; i++){
            switch (arguments[i]){
                case "-s" : spendingSum = true;
                break;
                case "-d" : littlespendings = true;
                break;
                case "-m" : avgSpendings = true;
                break;
                case "-p" : mostTraveles = true;
                break;
                case "-a" : mostDaysAbroad = true;
                break;
                case "-e" : mostExpensiveTravels = true;
                break;
                case "-i" : beenToItaly = true;
                break;
                case "-R" : reload();
                break;
            }
            getCadency(i);
            getName(i);

        }

    }
    private void reload() throws IOException, ParseException {
        NameToId names = new NameToId();
        names.updateIdList();
        TravelStatistcs trav7 = new TravelStatistcs(7);
        TravelStatistcs trav8 = new TravelStatistcs(8);
        trav7.reloadData();
        trav8.reloadData();
        AllPosels spendings = new AllPosels(8);
        spendings.reloadCadencyEarnings();
        System.exit(1);

    }
    public void run() throws Exception {
        NameToId names = new NameToId();
        TravelStatistcs travelStatistcs = new TravelStatistcs(kadencja);
        AllPosels spendings = new AllPosels(kadencja);

        if(spendingSum){
            int id;
            try{
                id = names.getId(nazwa, kadencja);
            }
            catch (Exception e){
                throw new Exception("No such posel in given cadency");
            }
            PoselSpendingsSummary wydatki = new PoselSpendingsSummary(id, kadencja);
            System.out.println(nazwa + " łącznie wydał " + wydatki.getTotalSpendings());
        }
        if(littlespendings){
            int id;
            try{
                id = names.getId(nazwa, kadencja);
            }
            catch (Exception e){
                throw new Exception("No such posel in given cadency");
            }
            PoselSpendingsSummary wydatki = new PoselSpendingsSummary(id, kadencja);
            System.out.println(nazwa + " wydał na drobne remonty " + wydatki.getTotalSpendings());
        }
        if(avgSpendings){
            System.out.println("łączne wydatki posłów kadencji " + kadencja + " to" + spendings.getAvgSpendings());
        }
        if(mostTraveles){
            System.out.println(travelStatistcs.getMostTravels());
        }
        if(mostDaysAbroad){
            System.out.println(travelStatistcs.getMostDaysAbroad());
        }
        if(mostExpensiveTravels){
            System.out.println(travelStatistcs.getMostSpent());
        }
        if(beenToItaly){
            System.out.println(travelStatistcs.getBeenToItaly());
        }
    }

    private void getName(int i){
        if(arguments[i].equals("-n")){
            nazwa = arguments[i+1];
        }
    }

    private void getCadency(int i) throws Exception {
        if(arguments[i].equals("-k")){
            try {
                kadencja = Integer.parseInt(arguments[i+1]);
                if(kadencja!=8 && kadencja!=7){
                    throw new Exception("Kadencja has to be either 7 or 8");
                }
            }catch (Exception e){
                throw  new Exception("Cadency has to be a number");
            }
        }
    }

}
