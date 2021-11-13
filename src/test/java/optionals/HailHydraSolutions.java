package optionals;

import optionals.HailHydra.KeyCard;
import optionals.HailHydra.ShieldPersonnel;
import optionals.mocked.ShieldDisasterProtocols;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class HailHydraSolutions {

    @Mock
    private ShieldDisasterProtocols shieldDisasterProtocols;

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
}
