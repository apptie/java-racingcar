package controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class RaceStateTest {

    @ParameterizedTest
    @EnumSource(names = {"APPLICATION_EXIT", "APPLICATION_EXCEPTION"})
    @DisplayName("RaceState의 값이 APPLICATION_EXIT, APPLICATION_EXCEPTION이라면 false를 반환한다.")
    void exitRace(RaceState raceState) {
        boolean result = raceState.isRacing();

        Assertions.assertThat(result).isFalse();
    }

    @ParameterizedTest
    @EnumSource(names = {"INPUT_CARS_NAME", "INPUT_RACE_ROUND", "RACE", "CALCULATE_WINNERS"})
    @DisplayName("RaceState의 값이 APPLICATION_EXIT가 아니라면 true를 반환한다.")
    void continueRace(RaceState raceState) {
        boolean result = raceState.isRacing();

        Assertions.assertThat(result).isTrue();
    }
}