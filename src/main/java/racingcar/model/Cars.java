package racingcar.model;

import racingcar.constants.CarConstant;
import racingcar.util.Util;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Cars {

    private List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(String[] carNames) {
        validateDuplicatedName(carNames);

        List<Car> cars = new ArrayList<>();
        for (String name : carNames) {
            validateNameLength(name);
            validateIsEmpty(name);
            cars.add(Car.from(name));
        }
        return new Cars(cars);
    }

    private static void validateDuplicatedName(String[] carNames) {
        Set<String> names = new HashSet<>(Arrays.asList(carNames));
        if (carNames.length != names.size()) {
            throw new IllegalArgumentException("자동차의 이름은 중복될 수 없습니다.");
        }
    }

    private static void validateNameLength(String name) {
        if (name.length() > CarConstant.MAX_NAME_LENGTH.getValue()) {
            throw new IllegalArgumentException("자동차의 이름은 5자 이하여야 합니다.");
        }
    }

    private static void validateIsEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("자동차의 이름에 공백은 포함될 수 없습니다.");
        }
    }

    public void playRound() {
        for (Car car : cars) {
            int randomNumber = Util.generateRandomNumber();
            car.moveForward(randomNumber);
        }
    }

    public List<String> getWinners() {
        List<String> winners = new ArrayList<>();
        int maxPosition = getMaxPosition();
        for (Car car : cars) {
            if (car.isWinner(maxPosition)) {
                winners.add(car.getName());
            }
        }
        return winners;
    }

    private int getMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = Math.max(maxPosition, car.getPosition());
        }
        return maxPosition;
    }

    public List<Car> getCars() {
        return cars;
    }
}
