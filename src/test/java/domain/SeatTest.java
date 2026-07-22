package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SeatTest {

  @Test
  @DisplayName("좌석을 생성할 수 있으며, 최초에는 AVAILABLE 상태다.")
  void createSeat() {
    //given
    String seatNo = "A-01";

    //when
    Seat seat = Seat.create(seatNo);

    //then
    assertEquals(seatNo, seat.getNo());
    assertEquals(SeatStatus.AVAILABLE, seat.getStatus());
  }

  @Test
  @DisplayName("좌석 번호는 비어잇을 수 없다.")
  void cannotSeatNoIsBlank() {
    //given
    String seatNoIsNull = null;
    String seatNoIsBlank = " ";

    //when & then
    assertThrows(IllegalArgumentException.class, () -> Seat.create(seatNoIsNull));
    assertThrows(IllegalArgumentException.class, () -> Seat.create(seatNoIsBlank));
  }

  @Test
  @DisplayName("사용자는 좌석을 선점하면 결제 대기 상태가 된다.")
  void reserveSeatThenWaitingPayment() {
    //given
    String seatNo = "A-01";
    Seat seat = Seat.create(seatNo);

    //when
    seat.reserve();

    //then
    assertEquals(SeatStatus.PAYMENT_WAIT, seat.getStatus());
  }

  @Test
  @DisplayName("이미 선점된 좌석은 다시 선점할 수 없다.")
  void alreadyReservedSeatCannotBeReservedAgain() {
    //given
    String seatNo = "A-01";
    Seat seat = Seat.create(seatNo);

    //when
    seat.reserve();

    // then
    assertThrows(IllegalStateException.class, seat::reserve);
  }

  @Test
  @DisplayName("결제가 완료되면 좌석은 예약 상태가 된다.")
  void reserveSeat() {
    //given
    String seatNo = "A-01";
    Seat seat = Seat.create(seatNo);

    //when
    seat.reserve();
    seat.purchase();

    //then
    assertEquals(SeatStatus.RESERVE, seat.getStatus());
  }

  @Test
  @DisplayName("이미 선정된 좌석은 예약할 수 없다.")
  void alreadySeat() {
    //given
    String seatNo = "A-01";
    Seat seat = Seat.create(seatNo);

    //when
    seat.reserve();
    seat.purchase();

    //then
    assertThrows(IllegalStateException.class, seat::reserve);
    assertThrows(IllegalStateException.class, seat::purchase);
  }

  @Test
  @DisplayName("선점하지 않은 좌석은 구매할 수 없다.")
  void cannotPurchaseSeat() {
    //given
    String seatNo = "A-01";
    Seat seat = Seat.create(seatNo);

    //when & then
    assertThrows(IllegalStateException.class, seat::purchase);
  }
}
