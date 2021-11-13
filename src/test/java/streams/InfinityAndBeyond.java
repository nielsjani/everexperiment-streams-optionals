package streams;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class InfinityAndBeyond {

    @Test
    public void bringMeThanos() {
        List<String> blackOrder = Lists.list("Proxima Midnight", "Corvus Glaive", "Ebony Maw", "Thanos", "Cull Obsidian", "Supergiant");

        String actual = null;

        assertThat(actual).isEqualTo("Thanos");
    }

    @Test
    public void timeTravelGoneWrong() {
        try {
            reverseThePolarityOfTheNeutronFlow();
        } catch (Exception e) {
            fail("fix the function");
        }
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

        List<String> actual = null;

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

        Set<String> actual = null;

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

        Integer actual = null;

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

        Double actual = null;

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

        String actual = null;

        assertThat(actual).isEqualTo(
                "The quotes:" +
                        "On your left as said by Captain America in Winter Soldier" +
                        "On your left as said by Falcon in Endgame" +
                        "Peace in our time as said by Iron Man in Age of Ultron" +
                        "Puny god as said by Hulk in Avengers"
        );
    }

    @Test
    public void canAnyoneOnTheTeamSurviveInfinityGems_Ultron_Visions_GalaxyDestroyingBomb() {
        List<PersonCondition> multiversalAvengers = List.of(
                new PersonCondition("Loki-Bromance Thor", false),
                new PersonCondition("Thanos-Gamora", false),
                new PersonCondition("Heartbroken Dr Strange", true),
                new PersonCondition("Age of Ultron Black Widow", false),
                new PersonCondition("Captain Carter", false),
                new PersonCondition("Star-Lord T'Challa", false),
                new PersonCondition("Black Panther KillMonger", false)
        );

        boolean canAnyoneSurvive = false;

        assertThat(canAnyoneSurvive).isTrue();
    }

    @Test
    public void didTheseVariantsSurvive() {
        List<PersonCondition> lokiVariants = List.of(
                new PersonCondition("Endgame Loki", true),
                new PersonCondition("Lokigator", true),
                new PersonCondition("Kid Loki", true)
        );

        boolean didTheyAllSurvive = false;

        assertThat(didTheyAllSurvive).isTrue();

        List<PersonCondition> lokiVariants2 = List.of(
                new PersonCondition("Endgame Loki", true),
                new PersonCondition("Lokigator", true),
                new PersonCondition("Kid Loki", true),
                new PersonCondition("President Loki", false)
        );

        Boolean didTheyAllSurviveNow = null;

        assertThat(didTheyAllSurviveNow).isFalse();
    }

    @Test
    public void theCollectorIndexesHisInventory() {
        List<Item> items = List.of(
                new Item("Species", "Human"),
                new Item("Animals", "Hit-Monkey"),
                new Item("Animals", "Cosmo"),
                new Item("Items", "Casket of Winter"),
                new Item("Animals", "Howard"),
                new Item("Items", "Hela's Crown"),
                new Item("Priceless", "Niels Jani's Autograph"),
                new Item("Species", "Sakaarian")
                );

        Map<String, List<String>> categorized = null;

        assertThat(categorized).hasSize(4);
        assertThat(categorized.get("Species")).containsExactlyInAnyOrder("Human", "Sakaarian");
        assertThat(categorized.get("Items")).containsExactlyInAnyOrder("Casket of Winter", "Hela's Crown");
        assertThat(categorized.get("Animals")).containsExactlyInAnyOrder("Hit-Monkey", "Cosmo", "Howard");
        assertThat(categorized.get("Priceless")).containsExactlyInAnyOrder("Niels Jani's Autograph");
    }

    public static void reverseThePolarityOfTheNeutronFlow() {
        List<Item> timetravelLocations = List.of(
                new Item("DANGEROUS", "Battle of New York"),
                new Item("UNKNOWN", "Vormir"),
                new Item("SAFE", "SHIELD Base"),
                new Item("SAFE", "Asgard"),
                new Item("DANGEROUS", "Morag")
        );

        Predicate<Item> dangerousPredicate = item -> item.getCategory().equals("DANGEROUS");
        Predicate<Item> safePredicate = item -> item.getCategory().equals("SAFE");
        Predicate<Item> unknownPredicate = item -> item.getCategory().equals("UNKNOWN");

        Stream<Item> stream = timetravelLocations.stream();

        List<Item> dangerousLocations = stream.filter(dangerousPredicate).collect(Collectors.toList());
        List<Item> safeLocations = stream.filter(safePredicate).collect(Collectors.toList());
        List<Item> unknownLocations = stream.filter(unknownPredicate).collect(Collectors.toList());
    }

    public static class Item {
        private final String category;
        private final String name;

        public Item(String category, String name) {
            this.category = category;
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public String getName() {
            return name;
        }
    }

    public static class PersonCondition {
        private final String person;
        private final boolean condition;

        public PersonCondition(String person, boolean condition) {
            this.person = person;
            this.condition = condition;
        }

        public String getPerson() {
            return person;
        }

        public boolean isCondition() {
            return condition;
        }
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


