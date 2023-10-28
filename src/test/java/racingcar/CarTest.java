package racingcar;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racingcar.model.Car;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CarTest {

    @ParameterizedTest
    @CsvSource({"5, 1", "2, 0", "4, 1"})
    void 자동차_전진_기능_테스트(int randomNumber, int expectedPosition) {
        // given
        Car car = Car.from("test");

        // when
        car.moveForward(randomNumber);

        // then
        assertThat(car.getPosition()).isEqualTo(expectedPosition);
    }
}
