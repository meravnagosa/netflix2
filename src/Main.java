import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
public class Main {

    public static void main(String[] args) {
        ArrayList<Episode> friendsEpisods = new ArrayList<Episode>();
        friendsEpisods.add(new Episode("The one when everybody finds out", "everybody finds out", "01/09/2021"));
        friendsEpisods.add(new Episode("The one when nobody is ready", "nobody is ready", "05/10/2021"));
        friendsEpisods.add(new Episode("The one with the bullies", "bullies are coming", "10/11/2021"));
        Series friends = new Series("FRIENDS", friendsEpisods);

        ArrayList<Episode> jordanEpisods = new ArrayList<Episode>();
        jordanEpisods.add(new Episode("Chapter 1", "The team go to Paris", "01/03/2020"));
        jordanEpisods.add(new Episode("Chapter 2", "Scottie Pippen is getting famous", "02/03/2020"));
        Series jordan = new Series("Michael Jordan: The Last Dance", jordanEpisods);

        ArrayList<Episode> luciferEpisods = new ArrayList<Episode>();
        luciferEpisods.add(new Episode("Pilot", "Lucifer join the police", "21/08/2021"));
        luciferEpisods.add(new Episode("Little Sad Devil Guy", "Lucifer is getting sad", "21/05/2021"));

        Series lucifer = new Series("Lucifer", luciferEpisods);

        ArrayList<Series> series = new ArrayList<Series>();
        series.add(lucifer);
        series.add(friends);
        series.add(jordan);
        ArrayList<User> users = new ArrayList<User>();
        mainMenu(series,users);
    }
    public static void mainMenu(ArrayList<Series> series,ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 1 && choice != 2) {
            try {
                System.out.println("1. Sign up\n2. Sign in");
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Press only 1 or 2");
                sc.nextLine();
            }
        }
        switch(choice) {
            case 1:
                signUp(series,users);

            case 2:
                signIn(series,users);

        }
    }
    public static void signUp(ArrayList<Series> series,ArrayList<User> users) {
        User user=null;
        Scanner sc = new Scanner(System.in);
        String username;
        String password;
        System.out.println("Choose a user name");
        username = sc.next();
        boolean validUserName = isAvailable(username, users);
        while (!validUserName) {
            System.out.println("Choose a user name");
            username = sc.next();
            validUserName = isAvailable(username, users);
        }
        System.out.println("Choose password (Please choose password with at least: six chararters,one letter and one digit)");
        password = sc.next();
        boolean validPassword = isStrongPassword(password);
        while (!validPassword) {
            System.out.println("Weak password. Please choose password with at least: six chararters,one letter and one digit. ");
            password = sc.next();
            validPassword = isStrongPassword(password);
        }
        LocalDate date = LocalDate.now();

        users.add(new User(username, password,date));
        mainMenu(series,users);
    }
    public static void signIn(ArrayList<Series> series,ArrayList<User> users) {
        User user=null;
        Scanner sc = new Scanner(System.in);
        String username;
        String password;
        System.out.println("Enter user name");
        username = sc.next();
        if (users.isEmpty()) {


        }
        if (!users.isEmpty()) {
            boolean exist = false;
            for (int i = 0; i < users.size(); i++) {
                User current = users.get(i);
                if (current.getUsername().equals(username)) {
                    System.out.println("Enter password");
                    exist=true;
                    password = sc.next();
                    while (!password.equals(current.getPasssword())) {
                            System.out.println("Wrong password, Insert password again:");
                            password = sc.nextLine();

                    }
                    user=current;
                }
            }

            if (!exist) {
                System.out.println("User does not exist");
                mainMenu(series,users);
                return;
            }
            System.out.println("WELCOME! what would like to do? \n1.See list of all series \n2.See last series you watched\n"
                    + "3.See subscriber details \n4.Choose a series to watch \n5.Sign out");
            int subChoice = sc.nextInt();
            switch (subChoice) {
                case 1:
                    for (int j = 0; j < series.size(); j++) {
                        System.out.println(series.get(j).getName());
                    }
                    break;
                case 2:
                    if (user.getWatched().isEmpty()) {
                        System.out.println("There are no series you have watched yet");
                        break;
                    }
                    System.out.println("You're watching the series: ");
                    for (int k=0;k<user.getWatched().size();k++) {
                        System.out.println(user.getWatched().get(k).getName());

                    }
                    int numberOfEpisodes=0;
                    if (!user.getWatched().isEmpty()) {
                        for (int k=0;k<user.getWatched().size();k++) {
                            for (int j=0;j<user.getWatched().get(k).getEpisods().size();j++) {
                                if (user.getWatched().get(k).getEpisods().get(j).getWatchedBy().contains(user))
                                    numberOfEpisodes++;
                            }
                        }
                    }
                    System.out.println("You watched " + numberOfEpisodes+" episods" );
                    break;

                case 3:
                    registrationDetails(series,user);
                    break;
                case 4:
                    seriesDetails(series,users,user);
                    break;
                case 5:
                    mainMenu(series,users);
            }
        }
        mainMenu(series,users);

    }
    private static void registrationDetails(ArrayList<Series> series,User user){
        int counter=0;
        System.out.println("Your account details are: Creating date:\n" + user.getDate());
        System.out.println("Your friendship ends at:\n" + user.getDate().plusYears(1));
        System.out.println("Your History in Netflix:\n Number of series you watched: \n" + user.getWatched().size());

        for (int j=0;j<series.size();j++){
            if(user.isDoneWatching(series.get(j)))
                counter++;

        }
        System.out.println("You have done watching "+counter+" series");
    }
    private static void seriesDetails(ArrayList<Series> series, ArrayList<User> users,User user) {
        Scanner sc=new Scanner(System.in);
        String seriesName;
        int chosenEpisode;
        System.out.println("Enter a name of series to watch:");
        Scanner s = new Scanner(System.in);
        seriesName = s.nextLine();
        boolean exist=false;
        for (int i=0;i<series.size();i++) {
            if (seriesName.equals(series.get(i).getName())) {
                exist = true;
                Series chosenSeries = series.get(i);
                for (int j = 0; j < chosenSeries.getEpisods().size(); j++) {
                    System.out.println("Details: \nEpisodes names: " + chosenSeries.getEpisods().get(j).getName());
                    System.out.println("Episode number: " +( j + 1));
                    System.out.println("Episode Summary: " + chosenSeries.getEpisods().get(j).getSummary());
                    System.out.println("Next Episode Release date: " + chosenSeries.getEpisods().get(j).getReleaseDate());
                    System.out.println();
                }

                System.out.println("Which episode would you like to watch?");
                chosenEpisode = sc.nextInt();
                while (chosenEpisode > chosenSeries.getEpisods().size()) {
                    System.out.println("There are only " + chosenSeries.getEpisods().size() + " episodes");
                    System.out.println("Which episode would you like to watch?");
                    chosenEpisode = sc.nextInt();
                }
                chosenSeries.getEpisods().get(chosenEpisode-1).remarkAsWatched(user);
                System.out.println("Episode " + chosenEpisode + " remark as viewed!");

                if(!chosenSeries.getWatchedBy().contains(user)){
                    for ( int j=1;i<chosenSeries.getEpisods().size()-1;j++) {
                        if (chosenSeries.getEpisods().get(j-1).getWatchedBy().contains(user)) {
                            System.out.println("Last episode you watched: " + chosenSeries.getEpisods().get(j-1).getName());

                        }
                    }
                }
            }
        }

        if (!exist) {
            System.out.println("series doesn't exist in library");
            seriesDetails(series, users,user);
        }
    }

    public static boolean isAvailable (String username, ArrayList < User > users){
        if (!users.isEmpty()) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(username)) {
                    System.out.println("User name is taken, please choose another user name");
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isStrongPassword (String password){
        if (password.length() < 6)
            return false;
        boolean hasLetter = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length(); i++) {
            char current = password.charAt(i);
            if (Character.isLetter(current))
                hasLetter = true;
            if (Character.isDigit(current))
                hasDigit = true;
        }
        if (hasLetter && hasDigit) {
            System.out.println("Your account is created, sign in to enter: ");
            return true;
        }
        return false;
    }
}