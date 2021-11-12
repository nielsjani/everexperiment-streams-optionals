package streams;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GettingStartedSolutions {

    @Test
    public void bringMeThanos() {
        List<String> blackOrder = Lists.list("Proxima Midnight", "Corvus Glaive", "Ebony Maw", "Thanos", "Cull Obsidian", "Supergiant");

        String actual = blackOrder.stream().filter(x -> x.equals("Thanos")).findFirst().orElseThrow();

        assertThat(actual).isEqualTo("Thanos");
    }

    @Test
    public void gatheringTheInfinityStones() {
        Map<String, String> stonesLocations = Map.of(
                "Space Stone", "Tessaract",
                "Mind Stone", "Loki's Sceptre",
                "Reality Stone", "The Aether",
                "Power Stone", "Morag",
                "Time Stone", "Eye of Agamotto",
                "Soul Stone", "Vormir"
        );

        List<String> actual = stonesLocations.keySet()
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        //results should be sorted alphabetically descending
        assertThat(actual).containsExactly(
                "Time Stone",
                "Space Stone",
                "Soul Stone",
                "Reality Stone",
                "Power Stone",
                "Mind Stone"
        );
    }

    @Test
    public void battleOfEarthParticipants() {
        List<String> originalAvengers = List.of("Captain America", "Hawkeye", "Thor", "Iron Man", "Hulk");
        List<String> guardiansOfTheGalaxy = List.of("Star-Lord", "Mantis", "Nebula-Main", "Groot2", "Rocket Raccoon", "Drax", "Kraglin");
        List<String> infinityGemPoweredIndividuals = List.of("Captain Marvel", "Scarlet Witch");
        List<String> anthropomorphicAnimals = List.of("Howard the Duck");
        List<String> wakanda = List.of("Black Panther", "Shuri", "Okoye", "MBaku");
        List<String> mastersOfTheMysticArts = List.of("Dr Strange", "Wong");
        List<String> sizeChangers = List.of("Ant-man2", "Wasp2");
        List<String> goopSellers = List.of("Rescue");
        List<String> others = List.of("Korg", "Miek", "Valkyrie", "Spider-Man");

        List<List<String>> participants = List.of(originalAvengers, goopSellers, infinityGemPoweredIndividuals, anthropomorphicAnimals, wakanda, guardiansOfTheGalaxy, mastersOfTheMysticArts, sizeChangers, others);

        Set<String> actual = participants
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        assertThat(actual).containsExactlyInAnyOrder(
                "Captain America", "Hawkeye", "Thor", "Iron Man", "Hulk",
                "Star-Lord", "Mantis", "Nebula-Main", "Groot2", "Rocket Raccoon", "Drax", "Kraglin",
                "Captain Marvel", "Scarlet Witch",
                "Howard the Duck",
                "Black Panther", "Shuri", "Okoye", "MBaku",
                "Dr Strange", "Wong",
                "Ant-man2", "Wasp2",
                "Rescue",
                "Korg", "Miek", "Valkyrie", "Spider-Man"
        );
    }

    @Test
    public void battleOfNewYorkTotalAvengersAge() {
        Map<String, Integer> avengerBirthYear = Map.of(
                "Captain America", 1918,
                "Iron Man", 1970,
                "Black Widow", 1984,
                "Hulk", 1969,
                "Thor", 964,
                "Hawkeye", 1982
        );

        Integer actual = avengerBirthYear.values()
                .stream().map(v -> 2012 - v)
                .mapToInt(v -> v)
                .sum();

        //The battle took place in 2012
        assertThat(actual).isEqualTo(1285);
    }

    @Test
    public void battleOfNewYorkAverageAvengersAge() {
        Map<String, Integer> avengerBirthYear = Map.of(
                "Captain America", 1918,
                "Iron Man", 1970,
                "Black Widow", 1984,
                "Hulk", 1969,
                "Thor", 964,
                "Hawkeye", 1982
        );

        Double actual = avengerBirthYear.values()
                .stream().map(v -> 2012 - v)
                .mapToDouble(v -> v)
                .summaryStatistics()
                .getAverage();

        //The battle took place in 2012
        assertThat(actual).isEqualTo(214.16666666666666);
    }

    @Test
    public void quoteCollector() {
        List<Quote> quotes = List.of(
                new Quote("On your left", "Captain America", "Winter Soldier"),
                new Quote("On your left", "Falcon", "Endgame"),
                new Quote("Peace in our time", "Iron Man", "Age of Ultron"),
                new Quote("Puny god", "Hulk", "Avengers")
        );

        String actual = quotes.stream()
                .map(qoute -> String.format("%s as said by %s in %s", qoute.getText(), qoute.getSpeaker(), qoute.getMovie()))
                .reduce("The quotes:", (q1, q2) -> q1 + q2);

        assertThat(actual).isEqualTo(
                "The quotes:" +
                        "On your left as said by Captain America in Winter Soldier" +
                        "On your left as said by Falcon in Endgame" +
                        "Peace in our time as said by Iron Man in Age of Ultron" +
                        "Puny god as said by Hulk in Avengers"
        );
    }

    public static class Quote {
        private final String text;
        private final String speaker;
        private final String movie;

        public Quote(String text, String speaker, String movie) {
            this.text = text;
            this.speaker = speaker;
            this.movie = movie;
        }

        public String getText() {
            return text;
        }

        public String getSpeaker() {
            return speaker;
        }

        public String getMovie() {
            return movie;
        }
    }
}

//avengerBirthYear.values()
//                .stream().map(v -> 2012 - v)
//                .mapToDouble(v -> v)
//                .summaryStatistics()
//                .getAverage()
