package lotto;

import lotto.util.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.MasterDetailListProcessor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    static int money = 2002;
    static int lottoPrice = 1000;
    static int remainder = money % lottoPrice;
    static int neededAmount = lottoPrice - remainder;

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 로또를_사고_잔돈이_있을때_예외가_발생한다() {
        String expectedMessage = String.format("%d원 더 주세요! %d원으로는 구매가 불가능합니다.", neededAmount, remainder);

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)).buyLotto(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedMessage);

    }

    @Test
    void 금액만큼의_로또를_산다() {
        assertThat(new Lotto(List.of(12, 3, 4, 5, 23, 1)).buyLotto(10000))
                .isEqualTo(10);
    }
}
