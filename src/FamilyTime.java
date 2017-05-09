
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by mdeshpande on 5/6/17.
 */
public class FamilyTime {

    public List<String> employeeList = new ArrayList<>();
    public HashMap<String, GroupInfo> groupInfoHashMap = new HashMap<>();

    public static void main(String args[]) {

        FamilyTime familyTime = new FamilyTime();
        List<String> employeeList = new ArrayList<>();

        int minmumPeople = Integer.parseInt(args[0]);
        int maximumPeople = Integer.parseInt(args[1]);

        int maxPeopleInGroup = maximumPeople;
        int minPeopleInGroup = minmumPeople;

        File dataFile = new File(args[2]);

        try{

            Scanner scan = new Scanner(dataFile);
            while(scan.hasNextLine()){
                employeeList.add(scan.nextLine());
            }
            scan.close();

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        familyTime.employeeList = employeeList;

        Collections.shuffle(employeeList);

        System.out.println(Arrays.toString(employeeList.toArray()));

        int numberOfPeople = employeeList.size();

        List<List<String>> listOfGroups = new ArrayList<>();
        List<String> group = new ArrayList<>();



        while (maxPeopleInGroup != minPeopleInGroup) {
            int numberOfGroups = (numberOfPeople % maxPeopleInGroup) % minPeopleInGroup;
            if (numberOfGroups != 0) {
                maxPeopleInGroup--;
            } else {
                break;
            }
        }

        int groupsOfMaxNumberOfPpl = numberOfPeople / maxPeopleInGroup;
        int groupsOfMinNumberOfPpl = (numberOfPeople % maxPeopleInGroup) / minPeopleInGroup;

        int totalPplinGroup = groupsOfMaxNumberOfPpl * maxPeopleInGroup;


        familyTime.addPeopleToGroup(0, totalPplinGroup, maxPeopleInGroup, groupsOfMaxNumberOfPpl, listOfGroups);
        familyTime.addPeopleToGroup(totalPplinGroup, employeeList.size(), minPeopleInGroup, groupsOfMinNumberOfPpl, listOfGroups);


        System.out.println(Arrays.toString(listOfGroups.toArray()));

    }

    private void addPeopleToGroup(int start, int end, int peopleInGroup, int numberOfGroups, List<List<String>> listOfGroups) {
        List<String> group = new ArrayList<>();
        while (numberOfGroups != 0) {
            for (int i = start; i < end; i++) {
                group.add(employeeList.get(i));
                GroupInfo grpInfo = new GroupInfo();
                if (group.size() == peopleInGroup) {

                    grpInfo.peoplePresentInGroup = group;
                    grpInfo.UUID = UUID.randomUUID().toString();
                    groupInfoHashMap.getOrDefault( grpInfo.UUID,  grpInfo);

                    listOfGroups.add(group);
                    group = new ArrayList<>();
                    numberOfGroups--;
                }
            }
        }
    }
}

class GroupInfo {
    String UUID;
    List<String> peoplePresentInGroup;
}
