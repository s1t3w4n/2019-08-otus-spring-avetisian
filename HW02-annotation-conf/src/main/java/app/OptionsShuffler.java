package app;

import java.util.Map;
import java.util.Set;

public interface OptionsShuffler {

    RandomOptionsShufflerImpl shuffleAnswers(Set<String> answers);

    Map<Integer, String> getSequence();

    String getAnswer(int key);
}
