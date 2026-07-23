package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class Seats {

  private final Map<String, Seat> seats = new HashMap<>();
  private static final String DEFAULT_SEAT_PREFIX = "A-";

  public Seats() {
  }

  public static Seats initialize(int size) {
    Seats staticSeats = new Seats();

    IntStream.range(1, size + 1)
             .forEach(no -> staticSeats.add(Seat.create(DEFAULT_SEAT_PREFIX + no)));

    return staticSeats;
  }

  public void add(Seat seat) {
    if (seats.containsKey(seat.getNo())) {
      throw new IllegalArgumentException("이미 존재하는 좌석 입니다.");
    }
    this.seats.put(seat.getNo(), seat);
  }

  public int size() {
    return seats.size();
  }

  public Seat findByNo(String seatNo) throws NoSuchElementException {
    Seat seat = seats.get(seatNo);
    if (seat == null) {
      throw new NoSuchElementException("존재하지 않는 좌석번호 입니다.");
    }
    return seat;
  }

  public List<Seat> getAll() {
    return List.copyOf(seats.values());
  }
}
