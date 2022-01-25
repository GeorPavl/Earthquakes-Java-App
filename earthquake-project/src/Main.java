public class Main {

    public static void main(String[] args) {
	// write your code here

//        Date date = new Date(1994, 6, 1, 15, 47, 13);
//        Date date1 = new Date(1995, 5, 10, 16, 3, 0);
//        Date date2 = new Date(1995, 5, 10, 16, 3, 0);
//        Date date3 = new Date(2022, 2, 25, 12);
//        Date date4 = new Date(2021, 1, 12);
//
//        System.out.println(date.toString());
//        System.out.println(date1.toString());
//        System.out.println(date3.toString());
//        System.out.println(date4.toString());
//
//        System.out.println(date.equals(date1));
//        System.out.println(date1.equals(date2));
//        System.out.println(date2.equals(date1));
//
//        System.out.println(date.hashCode());
//        System.out.println(date1.hashCode());
//        System.out.println(date2.hashCode());

        Location location = new Location(40.669453967131986, 22.898985644868066);
        Location location1 = new Location(40.674954776484576, 22.911860247093873);
        Location location2 = new Location(40.674954776484576, 22.911860247093873);

        System.out.println(location.toString());
        System.out.println(location1.toString());
        System.out.println(location2.toString());

        System.out.println(location1.equals(location2));
        System.out.println(location1.equals(location));

        System.out.println(location.distanceFrom(location1));
    }
}
