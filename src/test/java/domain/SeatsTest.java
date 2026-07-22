package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SeatsTest {

  @Test
  @DisplayName("좌석을 추가할 수 있다.")
  void canAddSeat() {
    // given
    Seat seat = Seat.create("A-01");
    Seats seats = new Seats();

    // when
    seats.add(seat);

    // then
    assertEquals(1, seats.size());
  }

  @Test
  @DisplayName("좌석 번호로 좌석을 조회할 수 있다.")
  void canFindSeat() {
    // given
    String seatNo = "A-01";
    Seat seat = Seat.create(seatNo);
    Seats seats = new Seats();

    // when
    seats.add(seat);
    Seat existSeat = seats.findSeatNo(seatNo);

    // then
    assertEquals(seatNo, existSeat.getNo());
  }

  @Test
  @DisplayName("모든 좌석을 조회할 수 있다.")
  void canGetAllSeats() {
    // given
    Seats seats = new Seats();
    List<Seat> seatList;

    // when
    seats.add(Seat.create("A-01"));
    seats.add(Seat.create("A-02"));
    seats.add(Seat.create("A-03"));
    seats.add(Seat.create("A-04"));

    seatList = seats.getAll();

    // then
    assertEquals(4, seatList.size());
  }

  @Test
  @DisplayName("존재하지 않는 좌석을 조회하면 예외가 발생한다.")
  void cannotFindSeat() {
    // given
    Seats seats = new Seats();
    seats.add(Seat.create("A-01"));

    // when & then
    assertThrows(NoSuchElementException.class,
                 () -> seats.findSeatNo("A-02"));

  }

  @Test
  @DisplayName("동일한 좌석 번호는 중복 등록할 수 없다.")
  void cannotAddDuplicateSeat() {
    // given
    Seats seats = new Seats();
    seats.add(Seat.create("A-01"));

    // when & then
    assertThrows(IllegalArgumentException.class,
                 () -> seats.add(Seat.create("A-01")));

  }

}
