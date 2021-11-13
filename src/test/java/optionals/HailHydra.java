package optionals;

import optionals.mocked.ArnimZolaDB;
import optionals.mocked.EscapeOptions;
import optionals.mocked.MindControlStatus;
import optionals.mocked.ShieldDisasterProtocols;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HailHydra {

    @Mock
    private ShieldDisasterProtocols shieldDisasterProtocols;
    @Mock
    private ArnimZolaDB arnimZolaDB;
    @Mock
    private EscapeOptions escapeOptions;
    @Mock
    private MindControlStatus mindControlStatus;

    @Test
    public void shieldClearanceLevel8RequiredToEnter() {
        List<ShieldPersonnel> shieldPersonnels = List.of(
                new ShieldPersonnel(null, "Pizza dog"),
                new ShieldPersonnel(new KeyCard(5), "Quake"),
                new ShieldPersonnel(new KeyCard(8), "Coulson")
        );

        List<String> accessReports = null;

        assertThat(accessReports)
                .containsExactlyInAnyOrder("Access granted to Coulson", "Access denied to Quake", "Access denied to Pizza dog");
    }

    @Test
    public void treatTriskelionCrashSurvivors() {
        //Empty optionals signify dead personnel -> Bury them
        // Living personnel without a secret agenda should be sent to the sickbay
        // Living personnel with a secret agenda should be sent to prison (they have a sickbay too)
        //The error in the test setup is intentional and needs to be fixed first
        //Always use getters when accessing fields
        List<Optional<ShieldPersonnel>> bodies = List.of(
                Optional.of(null),
                Optional.of(new ShieldPersonnel(new KeyCard(9), "Coulson")),
                Optional.of(new ShieldPersonnel(new KeyCard(8), "Crossbones", "World domination & Don't get face burned off"))
        );

        bodies.stream(); //implement further

        Mockito.verify(shieldDisasterProtocols, times(1)).sendToMedicalWard(any());
        Mockito.verify(shieldDisasterProtocols, times(1)).buryDeceased();
        Mockito.verify(shieldDisasterProtocols, times(1)).sendToPrison(any());
    }

    @Test
    public void capAndNatQueryArnimZolasDatabase() {
        //Arnim Zola has protected his systems against unwanted intruders.
        // A wrong query (aka empty optional) should trigger a self-destruct sequence (aka throw an exception)
        Mockito.when(arnimZolaDB.query("Red Skull")).thenReturn(Optional.of("Elrond"));
        Mockito.when(arnimZolaDB.query("Is Hydra still active")).thenReturn(Optional.empty());


        Optional<String> validQuery = arnimZolaDB.query("Red Skull");
        Assertions.assertThat(validQuery).isEqualTo("Elrond");

        IllegalArgumentException expectedException = assertThrows(
                IllegalArgumentException.class,
                () -> arnimZolaDB.query("Is Hydra still active"));
        Assertions.assertThat(expectedException).hasMessage("Goodbye Captain. Self-destructing in 5...4...3...");
    }

    @Test
    public void escapingZolasSelfDestructingUndergroundComplex() {
        //To exit Zolas complex you need to navigate a labyrinth of tunnels.
        // Luckily, Nick Fury is helping you find the right way.
        //If you reach a split and a red light is blinking, head left. Otherwise, head right.
        //It seems our heroes followed Fury's directions, but they still didn't manage to find the exit.
        // Can you help them? (Don't change the assertions)

        List<Optional<String>> lightSignals = List.of(
                Optional.of("Blinking"),
                Optional.empty(),
                Optional.of("Blinking"),
                Optional.empty(),
                Optional.empty(),
                Optional.of("Blinking"),
                Optional.of("Blinking"),
                Optional.empty(),
                Optional.empty()
        );

        lightSignals.forEach(signalOpt ->
                signalOpt.map(signal -> escapeOptions.headLeft())
                        .orElse(escapeOptions.headRight())
        );

        verify(escapeOptions, times(4)).headLeft();
        verify(escapeOptions, times(5)).headRight();
    }

    @Test
    public void bringingBackBucky() {
        //Bucky is battling his mind-control.
        // As long as his mind control status is still active (aka not returning an empty optional) he considers himself 'The Winter Soldier'
        //If his Mind control status is lifted, he becomes Bucky again

        //Expand this supplier so it return an optional with value 'Bucky Barnes' if the mindcontrolstatus returns an empty optional
        Supplier<Optional<String>> mindControlCheck = () -> mindControlStatus.getPersona();

        //mind control still in place
        when(mindControlStatus.getPersona()).thenReturn(Optional.of("The Winter Soldier"));
        Optional<String> persona1 = mindControlCheck.get();
        Assertions.assertThat(persona1).hasValue("The Winter Soldier");

        //mind control lifted
        when(mindControlStatus.getPersona()).thenReturn(Optional.empty());
        Optional<String> persona2 = mindControlCheck.get();
        Assertions.assertThat(persona2).hasValue("Bucky Barnes");
    }

    public static class ShieldPersonnel {
        private final KeyCard keyCard;
        private final String name;
        private final String secretHydraAgenda;

        public ShieldPersonnel(KeyCard keyCard, String name) {
            this.keyCard = keyCard;
            this.name = name;
            this.secretHydraAgenda = null;
        }

        public ShieldPersonnel(KeyCard keyCard, String name, String secretHydraAgenda) {
            this.keyCard = keyCard;
            this.name = name;
            this.secretHydraAgenda = secretHydraAgenda;
        }

        public Optional<KeyCard> getKeyCard() {
            return Optional.ofNullable(keyCard);
        }

        public String getName() {
            return name;
        }

        public Optional<String> getSecretHydraAgenda() {
            return Optional.ofNullable(secretHydraAgenda);
        }
    }

    public static class KeyCard {
        private final int level;

        public KeyCard(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }
}
