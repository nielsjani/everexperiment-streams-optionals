package streams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import streams.mocked.MindAlteration;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class Wandavision {

    @Mock
    private MindAlteration mindAlteration;

    @Test
    public void mindAlterations() {
        Mockito.when(mindAlteration.process(any()))
                .then(invocationOnMock -> {
                    String argument = invocationOnMock.getArgument(0);
                    if (argument.equals("50's") || argument.equals("60's") || argument.equals("70's")) {
                        return ":)";
                    } else {
                        throw new IllegalArgumentException(">:(");
                    }
                });

        List<String> timeperiods = List.of("Unaltered timeperiod",
                "70's",
                "50's",
                "70's",
                "60's",
                "70's",
                "80's",
                "70's",
                "90's",
                "70's",
                "00's"
        );

        //Make the test pass
        //Also make sure that the "70's" is only processed once
        timeperiods
                .stream()
                //implement here
                //No filtering, mapping or sorting ;)
                .forEach(timeperiod -> mindAlteration.process(timeperiod));

        Mockito.verify(mindAlteration, times(3)).process(any());
        Mockito.verify(mindAlteration, times(1)).process("50's");
        Mockito.verify(mindAlteration, times(1)).process("60's");
        Mockito.verify(mindAlteration, times(1)).process("70's");
    }


    @Test
    public void theBirthOfSpectrum() {
        List<TransformationStep> steps = List.of(
                new TransformationStep("acquiring visible light manipulation", false),
                new TransformationStep("acquiring infrared light manipulation", false),
                new TransformationStep("acquiring radio wave manipulation", false),
                new TransformationStep("acquiring gamma wave manipulation", false),
                new TransformationStep("acquiring cosmic ray manipulation", false),
                new TransformationStep("acquiring ultraviolet light manipulation", false),
                new TransformationStep("transformation complete", true),
                new TransformationStep("transformation complete", true),
                new TransformationStep("transformation complete", true),
                new TransformationStep("transformation complete", true),
                new TransformationStep("transformation complete", true),
                new TransformationStep("transformation complete", true),
                new TransformationStep("oh yeah, also appearance alteration, superhuman speed and flight", false),
                new TransformationStep("transformation complete", true)
        );

        steps
                //implement here: as soon as the transformation is complete, stop processing elements
                //dont re-use any code used to fix the mindAlterations test. Also, still not allowed to filter, map or sort
                .forEach(step -> mindAlteration.process(step.getDescription()));

        Mockito.verify(mindAlteration, times(6)).process(any());
        Mockito.verify(mindAlteration, times(0)).process("transformation complete");
    }

    public static class TransformationStep {
        private final String description;
        private final boolean transformationComplete;

        public TransformationStep(String description, boolean transformationComplete) {
            this.description = description;
            this.transformationComplete = transformationComplete;
        }

        public String getDescription() {
            return description;
        }

        public boolean isTransformationComplete() {
            return transformationComplete;
        }
    }
}
