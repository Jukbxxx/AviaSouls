import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AviaSoulsTest {

    AviaSouls avia = new AviaSouls();
    TicketTimeComparator timeComparator = new TicketTimeComparator();
    Ticket ticket1 = new Ticket("DME Москва", "AER Сочи", 7_953, 20, 24);//4 часа в пути
    Ticket ticket2 = new Ticket("SVO Москва", "AER Сочи", 18_432, 18, 23);//5 часов в пути
    Ticket ticket3 = new Ticket("SVO Москва", "LED Санкт-Петербург", 4_613, 22, 24);//2 часа в пути
    Ticket ticket4 = new Ticket("DME Москва", "LED Санкт-Петербург", 11_532, 19, 22);//3 часа в пути
    Ticket ticket5 = new Ticket("DME Москва", "AER Сочи", 5_214, 12, 16);//4 часа в пути
    Ticket ticket6 = new Ticket("SVO Москва", "AER Сочи", 5_282, 9, 13);//4 часа в пути


    @BeforeEach
    public void setup() {
        avia.add(ticket1);
        avia.add(ticket2);
        avia.add(ticket3);
        avia.add(ticket4);
        avia.add(ticket5);
        avia.add(ticket6);

    }

    @Test
    public void shouldSearchAndSortByPriceIfTwoMatches() {
        Ticket[] expected = {ticket5, ticket1};
        Ticket[] actual = avia.search("DME Москва", "AER Сочи");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByPriceIfManyMatches() {
        Ticket ticket7 = new Ticket("DME Москва", "AER Сочи", 10_350, 14, 18);
        Ticket ticket8 = new Ticket("DME Москва", "AER Сочи", 8_429, 9, 13);
        Ticket ticket9 = new Ticket("DME Москва", "AER Сочи", 7_689, 8, 12);
        Ticket ticket10 = new Ticket("DME Москва", "AER Сочи", 8_697, 18, 22);
        avia.add(ticket7);
        avia.add(ticket8);
        avia.add(ticket9);
        avia.add(ticket10);
        Ticket[] expected = {ticket5, ticket9, ticket1, ticket8, ticket10, ticket7};
        Ticket[] actual = avia.search("DME Москва", "AER Сочи");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindAnyIfZeroMatches() {
        Ticket[] expected = {};
        Ticket[] actual = avia.search("AER Сочи", "LED Санкт-Петербург");
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldSearchIfTheOnlyMatch() {
        Ticket ticket11 = new Ticket("AER Сочи", "LED Санкт-Петербург", 10_500, 11, 15);
        avia.add(ticket11);
        Ticket[] expected = {ticket11};
        Ticket[] actual = avia.search("AER Сочи", "LED Санкт-Петербург");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldCompareByTimeOfFlight() {
        Ticket[] tickets = {ticket1, ticket2, ticket3};
        Ticket[] expected = {ticket3, ticket1, ticket2};
        Arrays.sort(tickets, timeComparator);
        Assertions.assertArrayEquals(expected, tickets);
    }

    @Test
    public void shouldCompareIfTimeOfFlightISEqual() {
        Ticket[] tickets = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] expected = {ticket3, ticket4, ticket1, ticket5, ticket6, ticket2};
        Arrays.sort(tickets, timeComparator);
        Assertions.assertArrayEquals(expected, tickets);
    }

    @Test
    public void shouldSearchAndSortByTimeOfFlightIfTwoMatches() {
        Ticket[] expected = {ticket6, ticket2};
        Ticket[] actual = avia.searchAndSortBy("SVO Москва", "AER Сочи", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByTimeOfFlightIfManyMatches() {
        Ticket ticket7 = new Ticket("DME Москва", "AER Сочи", 10_350, 13, 18);
        Ticket ticket8 = new Ticket("DME Москва", "AER Сочи", 8_429, 9, 13);
        Ticket ticket9 = new Ticket("DME Москва", "AER Сочи", 7_689, 7, 12);
        Ticket ticket10 = new Ticket("DME Москва", "AER Сочи", 8_697, 18, 22);
        avia.add(ticket7);
        avia.add(ticket8);
        avia.add(ticket9);
        avia.add(ticket10);
        Ticket[] expected = {ticket1, ticket5, ticket8, ticket10, ticket7, ticket9};
        Ticket[] actual = avia.searchAndSortBy("DME Москва", "AER Сочи", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByTimeWithTheOnlyMatch() {
        Ticket[] expected = {ticket4};
        Ticket[] actual = avia.searchAndSortBy("DME Москва", "LED Санкт-Петербург", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindAndSortAnyIfZeroMatches() {
        Ticket[] expected = {};
        Ticket[] actual = avia.searchAndSortBy("AER Сочи", "LED Санкт-Петербург", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
