package streams;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class GettingStarted {

    @Test
    public void bringMeThanos() {
        List<String> blackOrder = Lists.list("Proxima Midnight", "Corvus Glaive", "Ebony Maw", "Thanos", "Cull Obsidian", "Supergiant");

        String actual = null;

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
                "The quotes:"+
                "On your left as said by Captain America in Winter Soldier"+
                        "On your left as said by Falcon in Endgame"+
                        "Peace in our time as said by Iron Man in Age of Ultron"+
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


