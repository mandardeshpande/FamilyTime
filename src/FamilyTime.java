
import java.util.*;

/**
 * Created by mdeshpande on 5/6/17.
 */
public class FamilyTime {

    public List<String> employeeList = new ArrayList<>();

    public static void main(String args[]) {

        FamilyTime familyTime = new FamilyTime();
        List<String> employeeList = new ArrayList<>();

        employeeList.add("Happy");
        employeeList.add("Dopey");
        employeeList.add("Grumpy");
        employeeList.add("Sneezy");
        employeeList.add("Bashful");
        employeeList.add("Sleepy");
        employeeList.add("Doc");
        employeeList.add("Snow");
        employeeList.add("AK");
        employeeList.add("MD");
        employeeList.add("SD");

        familyTime.employeeList = employeeList;

        Collections.shuffle(employeeList);

        System.out.println(Arrays.toString(employeeList.toArray()));

        int numberOfPeople = employeeList.size();
        List<List<String>> listOfGroups = new ArrayList<>();
        List<String> group = new ArrayList<>();

        int maxPeopleInGroup = 5;
        int minPeopleInGroup = 3;

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
                if (group.size() == peopleInGroup) {
                    listOfGroups.add(group);
                    group = new ArrayList<>();
                    numberOfGroups--;
                }
            }
        }
    }
}
