
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    static int [][] arrays;
    static String[][][] thirdArrays;
    static   String[] timeShift = {"# Hall - Morning","# Hall - Afternoon","# Hall - Night"};
    static String[] result = {"AV","BO"};
    static String[] seatElements = {};
    static String[] studentId = new String[0];
    static final Character[] hall = {'a','b','c'};
    static  Character[] hallHistory = new Character[0];
    static String[] dateTime = new String[0];
    public static void main(String[] args) {
        homePage();
        arrays = configHall();
        setArrays(arrays);
//        System.out.println(Arrays.deepToString(thirdArrays));
        applicationMenu();
        mainService(inputOptionMenu());

    }

    public static void homePage(){
        signBorder();
        System.out.println("cstad hall booking system".toUpperCase());
        signBorder();
    }
    public static void signBorder(){
        System.out.println("-+".repeat(25));
    }
    public static int[][] configHall(){
        Scanner scanner = new Scanner(System.in);
//  User Input
        System.out.print("Config total rows in hall : ");
        int rows = scanner.nextInt();
        System.out.print("Config total seats per row in hall : "); int columns = scanner.nextInt();

        int[][] arrays = new int[rows][columns];

        for (int i=0,j; i<rows;i++){
            for (j=0;j<columns;j++){
                arrays[i][j] = j;
            }
        }
//        System.out.println(Arrays.deepToString(arrays));
        return arrays;
    }
    public static void setArrays(int[][] arrays){
        // initialize a new 3d array to store string
        thirdArrays = new String[timeShift.length][arrays.length][arrays[0].length];
        for (int t=0;t<timeShift.length;t++) {
            for (int i = 0; i < arrays.length; i++) {
                for (int j = 0; j < arrays[i].length; j++) {
                    char alphabet = (char) ('a' + arrays[i][j]);
                    String element = "|" + Character.toUpperCase(alphabet) + "-" + i + "::" + result[0] + "| \t";
                    // store the generated string in 3D array
                    thirdArrays[t][i][j] = element;
                }
            }
        }
    }
    public static void applicationMenu(){
        System.out.println();
        System.out.print("""
                [[ Application Menu ]]
                <A> Booking
                <B> Hall
                <C> Showtime
                <D> Reboot Showtime
                <E> History
                <F> Exit
                """);
    }

    public static char inputOptionMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("> Please select menu no: ");
        return scanner.nextLine().charAt(0);
    }

    public static void mainService(char option){
        switch (option){
            case 'A','a' -> {
                char element = getIndexOfHall(serviceBooking(bookingPage()));
                availableSeat(element);

            }
            case 'B','b' ->{
                optionHallShift();
                applicationMenu();
                mainService(inputOptionMenu());

            }
            case 'C','c'->{
                showTime();
                applicationMenu();
                mainService(inputOptionMenu());

            }
            case 'D','d'->{
                setArrays(arrays);
                seatElements = new String[0];
                studentId = new String[0];
                hallHistory = new Character[0];
                dateTime = new String[0];
                applicationMenu();
                mainService(inputOptionMenu());

            }
            case 'E','e'->{
                bookingHistory(seatElements.length);
                applicationMenu();
                mainService(inputOptionMenu());
            }
            case 'F','f'->{
            System.out.println("-".repeat(35));
            System.out.println("=".repeat(5).concat("> ").concat("Shutdown System").concat(" <").concat("=".repeat(5)));
            System.out.println("-".repeat(35));
            System.exit(0);
            }
        default -> {
        applicationMenu();
        message();
        mainService(inputOptionMenu());
        }
        };
        }
public static char bookingPage(){
    Scanner scanner = new Scanner(System.in);
    signBorder();
    System.out.println("# Start booking process");
    showTime();
    System.out.print("> Please select show time (A | B | C) : ");
    return scanner.next().charAt(0);
}

public static void showTime(){
    signBorder();
    System.out.println("""
                # Showtime information
                # A) Morning (10:00AM - 12:30PM)
                # B) Afternoon (03:00PM - 05:30PM)
                # C) Night (07:00PM - 09:30PM)
                """);
    signBorder();
}
public static char serviceBooking(char option){
    switch (option){
        case 'a','A', 'b', 'B', 'c', 'C' ->{
            iteratorSeatOfHall(option);

        }
        default -> {
            message();
            serviceBooking(bookingPage());
        }
    }
    return option;
}
public static void iteratorSeatOfHall(char hall){
    if ((hall == 97) || (hall == 65)){

        String[][] array2D = thirdArrays[0];
        signBorder();
        System.out.println(timeShift[0]);
        for (String[] array1D : array2D){
            for (String item : array1D){
                System.out.print(item);
            }
            System.out.println();
        }
        signBorder();

    }else if ((hall == 98) || (hall == 66)){
        String[][] array2D = thirdArrays[1];
        signBorder();
        System.out.println(timeShift[1]);
        for (String[] array1D : array2D){
            for (String item : array1D){
                System.out.print(item);
            }
            System.out.println();
        }
        signBorder();
    }else if ((hall == 99) || (hall == 67)){
        String[][] array2D = thirdArrays[2];
        signBorder();
        System.out.println(timeShift[2]);
        for (String[] array1D : array2D){
            for (String item : array1D){
                System.out.print(item);
            }
            System.out.println();
        }
        signBorder();
    }
}
public static void message(){
    System.out.println("=".repeat(5).concat("> ").concat("Your input is not correct! Please try again!").concat(" <").concat("=".repeat(5)));
}
public static void bookingHistory(int booked){
    signBorder();
    System.out.println("# Booking History");
    signBorder();
    System.out.println("-".repeat(60));
//        System.out.println(Arrays.deepToString(seatElements));
//        System.out.println(Arrays.toString(studentId));

    for (int i=0; i<seatElements.length;i++){

        System.out.printf("#NO: %d%n",i+1);
        System.out.println("#SEATS: ["+seatElements[i].toUpperCase().concat("]"));
        var record = """
                    #HALL       #STU.ID              #CREATE AT
                    HALL %c     %s                   %s
                    """;

        String format = String.format(record,hallHistory[i],studentId[i],dateTime[i]);
        System.out.print(format);
        System.out.println("-".repeat(60));

    }
}
public static void optionHallShift(){
    signBorder();
    System.out.println("# Hall Information");
    signBorder();
    iteratorElement();
}
public static void iteratorElement(){
    // initialize a new 3d array to store string
    for (int i=0; i< thirdArrays.length; i++){
        String[][] array2D = thirdArrays[i];
        System.out.println(timeShift[i]);
        for (String[] array1D : array2D){
            for (String item : array1D){
                System.out.print(item);
            }
            System.out.println();
        }
        signBorder();
    }
}
public static char getIndexOfHall(char userInput) {
    for (int i = 0; i < hall.length; i++) {
        if (hall[i] == userInput) {
            return hall[i];
        }
    }
    return (char) -1; // If userInput doesn't match any element in hall
}
public static void availableSeat(char element){
    System.out.print("""
                # INSTRUCTION
                # Single: C-1
                # Multiple (separate by comma): C-1,C-2
                """);
    System.out.print("> Please select available seat: ");
    String seat = new Scanner(System.in).nextLine();
    System.out.print("Please enter student ID: ");
    String student = new Scanner(System.in).nextLine();
    signBorder();
    System.out.print("> Are you sure to book? (Y/n) : ");

    switch (new Scanner(System.in).nextLine().charAt(0)){
        case 'Y','y' -> {
            String[] splitSeats = seat.split(",");
            boolean isBookingSuccessful = bookSeats(element, seat);
            if (isBookingSuccessful) {
                System.out.println("Booking successful!");
            } else {
                System.out.println("Booking failed!");
            }

            // Calculate the new length of arrays
            int newLength = seatElements.length + splitSeats.length;
            // Resize arrays
            seatElements = Arrays.copyOf(seatElements, newLength);
            studentId = Arrays.copyOf(studentId, newLength);
            hallHistory = Arrays.copyOf(hallHistory, newLength);
            dateTime = Arrays.copyOf(dateTime, newLength);

            // Copy split seat numbers into arrays
            System.arraycopy(splitSeats, 0, seatElements, seatElements.length - splitSeats.length, splitSeats.length);
            Arrays.fill(studentId, newLength - splitSeats.length, newLength, student);

            // Fill hallHistory and dateTime arrays
            for (int i = newLength - splitSeats.length; i < newLength; i++) {
                hallHistory[i] = element;
                dateTime[i] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        }
        case 'N','n' -> {
            System.out.println("Booking cancelled!");
        }
        default ->
                throw new IllegalStateException("Unexpected value: " + new Scanner(System.in).nextLine().charAt(0));
    };
    applicationMenu();
    mainService(inputOptionMenu());
}
public static boolean bookSeats(char element, String seat) {
    String[] splitSeats = seat.split(",");
    int arrayIndex = -1;
    if (element >= 'a' && element <= 'c') {
        arrayIndex = element - 'a';
    } else if (element >= 'A' && element <= 'C') {
        arrayIndex = element - 'A';
    } else {
        System.out.println("Invalid element code.");
        return false;
    }

    for (String s : splitSeats) {
        int seatIndex = Integer.parseInt(s.split("-")[1]);
        if (thirdArrays[arrayIndex][seatIndex][s.charAt(0) - 'a'].contains("BO")) {
            // Print error if the seat is already booked
            System.out.println("The seat " + s + " is already booked. Please choose another seat.");
            return false;
        } else {
            thirdArrays[arrayIndex][seatIndex][s.charAt(0) - 'a'] = thirdArrays[arrayIndex][seatIndex][s.charAt(0) - 'a'].replace("AV", "BO");
        }
    }
    return true; // Booking successful
}
}