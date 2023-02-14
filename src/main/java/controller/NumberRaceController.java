package controller;

import dto.RacingRoundStateDto;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import model.RaceGame;
import view.InputView;
import view.OutputView;

public class NumberRaceController implements RaceController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RaceGame raceGame;
    private final Map<RaceState, Supplier<RaceState>> raceMappings = new EnumMap<>(RaceState.class);

    public NumberRaceController(InputView inputView, OutputView outputView, RaceGame raceGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.raceGame = raceGame;
        initMappings();
    }

    private void initMappings() {
        this.raceMappings.put(RaceState.INPUT_CARS_NAME, this::initCars);
        this.raceMappings.put(RaceState.INPUT_RACE_ROUND, this::initRaceRound);
        this.raceMappings.put(RaceState.RACE, this::race);
        this.raceMappings.put(RaceState.CALCULATE_WINNERS, this::calculateWinners);
        this.raceMappings.put(RaceState.APPLICATION_EXCEPTION, this::exit);
    }

    @Override
    public RaceState run(RaceState raceState) {
        return raceMappings.get(raceState).get();
    }

    private RaceState initCars() {
        Set<String> carsName = inputView.inputCarsName();

        raceGame.initCars(carsName);
        return RaceState.INPUT_RACE_ROUND;
    }

    private RaceState initRaceRound() {
        int round = inputView.inputRound();

        raceGame.initRound(round);
        return RaceState.RACE;
    }

    private RaceState race() {
        List<RacingRoundStateDto> racingTotalState = raceGame.race();

        outputView.printRacingTotalState(racingTotalState);
        return RaceState.CALCULATE_WINNERS;
    }

    private RaceState calculateWinners() {
        outputView.printWinners(raceGame.calculateWinners());

        return RaceState.APPLICATION_EXIT;
    }

    private RaceState exit() {
        return RaceState.APPLICATION_EXIT;
    }
}
