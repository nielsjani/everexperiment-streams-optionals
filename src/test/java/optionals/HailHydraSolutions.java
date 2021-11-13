package optionals;

import optionals.HailHydra.KeyCard;
import optionals.HailHydra.ShieldPersonnel;
import optionals.mocked.ArnimZolaDB;
import optionals.mocked.EscapeOptions;
import optionals.mocked.ShieldDisasterProtocols;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HailHydraSolutions {

    @Mock
    private ShieldDisasterProtocols shieldDisasterProtocols;
    @Mock
    private ArnimZolaDB arnimZolaDB;
    @Mock
    private EscapeOptions escapeOptions;

    @Test
    public void shieldClearanceLevel8RequiredToEnter() {
        List<ShieldPersonnel> shieldPersonnels = List.of(
                new ShieldPersonnel(null, "Pizza dog"),
                new ShieldPersonnel(new KeyCard(5), "Quake"),
                new ShieldPersonnel(new KeyCard(8), "Coulson")
        );

        List<String> accessReports = shieldPersonnels.stream()
                .map(personnel -> personnel.getKeyCard()
                        .filter(keyCard -> keyCard.getLevel() >= 8)
                        .map(keycard -> "Access granted to " + personnel.getName())
                        .orElse("Access denied to " + personnel.getName())
                )
                .collect(Collectors.toList());

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
                Optional.empty(),
                Optional.of(new ShieldPersonnel(new KeyCard(9), "Coulson")),
                Optional.of(new ShieldPersonnel(new KeyCard(8), "Crossbones", "World domination & Don't get face burned off"))
        );

        bodies.forEach(optionalPerson ->
                optionalPerson
                        .ifPresentOrElse(person ->
                                        person.getSecretHydraAgenda()
                                                .ifPresentOrElse(agenda -> shieldDisasterProtocols.sendToPrison(person),
                                                        () -> shieldDisasterProtocols.sendToMedicalWard(person)),
                                () -> shieldDisasterProtocols.buryDeceased()));

        Mockito.verify(shieldDisasterProtocols, times(1)).sendToMedicalWard(any());
        Mockito.verify(shieldDisasterProtocols, times(1)).buryDeceased();
        Mockito.verify(shieldDisasterProtocols, times(1)).sendToPrison(any());
    }

    @Test
    public void capAndNatQueryArnimZolasDatabase() {
        //Arnim Zola has protected his systems against unwanted intruders.
        // A wrong query (aka empty optional) should trigger a self-destruct sequence (aka throw an exception)
        when(arnimZolaDB.query("Red Skull")).thenReturn(Optional.of("Elrond"));
        when(arnimZolaDB.query("Is Hydra still active")).thenReturn(Optional.empty());

        String validQuery = doQuery("Red Skull");
        Assertions.assertThat(validQuery).isEqualTo("Elrond");

        IllegalArgumentException expectedException = assertThrows(
                IllegalArgumentException.class,
                () -> doQuery("Is Hydra still active"));

        Assertions.assertThat(expectedException).hasMessage("Goodbye Captain. Self-destructing in 5...4...3...");
    }

    @Test
    public void escapingZolasSelfDestructingUndergroundComplex() {
        //To exit Zolas complex you need to navigate a labyrinth of tunnels.
        // Luckily, Nick Fury is helping you find the right way.
        //If you reach a split and a red light is blinking, head left. Otherwise, head right.
        //It seems our heroes followed Fury's directions, but they still didn't manage to find the exit.
        // Can you help them? (Don't change the assertions)

        when(escapeOptions.headLeft()).thenReturn("L");
        when(escapeOptions.headRight()).thenReturn("R");

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
                        .orElseGet(escapeOptions::headRight)
        );

        verify(escapeOptions, times(4)).headLeft();
        verify(escapeOptions, times(5)).headRight();
    }

    private String doQuery(String arg) {
       return arnimZolaDB.query(arg)
                .orElseThrow(() -> new IllegalArgumentException("Goodbye Captain. Self-destructing in 5...4...3..."));
    }
}
