import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlightAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество рейсов: ");
        int numFlights = scanner.nextInt();
        scanner.nextLine(); // Считываем символ новой строки

        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < numFlights; i++) {
            System.out.print("Введите данные рейса (откуда, куда, перевозчик, время полета, цена) через пробел: ");
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts.length != 5) {
                System.err.println("Неверный формат ввода данных. Попробуйте снова.");
                continue;
            }
            flights.add(new Flight(
                    parts[0],
                    parts[1],
                    parts[2],
                    Integer.parseInt(parts[3]),
                    Double.parseDouble(parts[4])
            ));
        }

        // Фильтрация по маршруту (Владивосток - Тель-Авив)
        List<Flight> filteredFlights = filterFlights(flights, "Владивосток", "Тель-Авив");

        // Минимальное время полета для каждого перевозчика
        Map<String, Integer> minFlightTimes = calculateMinFlightTimes(filteredFlights);
        System.out.println("Минимальное время полета между Владивостоком и Тель-Авивом для каждого авиаперевозчика:");
        for (Map.Entry<String, Integer> entry : minFlightTimes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " минут");
        }

        // Разница между средней ценой и медианой
        double averagePrice = calculateAveragePrice(filteredFlights);
        double medianPrice = calculateMedianPrice(filteredFlights);
        double priceDifference = averagePrice - medianPrice;
        System.out.println("Разница между средней ценой и медианой для полета Владивосток - Тель-Авив: " + priceDifference);

        scanner.close();
    }

    private static List<Flight> filterFlights(List<Flight> flights, String fromCity, String toCity) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getFrom().equals(fromCity) && flight.getTo().equals(toCity)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    private static Map<String, Integer> calculateMinFlightTimes(List<Flight> flights) {
        Map<String, Integer> minFlightTimes = new HashMap<>();
        for (Flight flight : flights) {
            String carrier = flight.getCarrier();
            if (!minFlightTimes.containsKey(carrier) || flight.getFlightTime() < minFlightTimes.get(carrier)) {
                minFlightTimes.put(carrier, flight.getFlightTime());
            }
        }
        return minFlightTimes;
    }

    private static double calculateAveragePrice(List<Flight> flights) {
        if (flights.isEmpty()) {
            return 0;
        }
        double totalPrice = 0;
        for (Flight flight : flights) {
            totalPrice += flight.getPrice();
        }
        return totalPrice / flights.size();
    }

    private static double calculateMedianPrice(List<Flight> flights) {
        if (flights.isEmpty()) {
            return 0;
        }
        List<Double> prices = new ArrayList<>();
        for (Flight flight : flights) {
            prices.add(flight.getPrice());
        }
        Collections.sort(prices);
        int middleIndex = prices.size() / 2;
        if (prices.size() % 2 == 0) {
            return (prices.get(middleIndex - 1) + prices.get(middleIndex)) / 2;
        } else {
            return prices.get(middleIndex);
        }
    }
}

class Flight {
    private String from;
    private String to;
    private String carrier;
    private int flightTime;
    private double price;

    public Flight(String from, String to, String carrier, int flightTime, double price) {
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.flightTime = flightTime;
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public double getPrice() {
        return price;
    }
}
