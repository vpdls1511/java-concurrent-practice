package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Seats {

  private final Map<String, Seat> seats = new HashMap<>();

  public Seats() {
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
