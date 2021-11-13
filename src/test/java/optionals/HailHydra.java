package optionals;

import optionals.mocked.ShieldDisasterProtocols;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class HailHydra {

    @Mock
    private ShieldDisasterProtocols shieldDisasterProtocols;

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
