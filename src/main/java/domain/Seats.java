package domain;

import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.management.openmbean.KeyAlreadyExistsException;

public class Seats {

  private Map<String, Seat> seats = new HashMap<>();

  public Seats() {
  }

  public void add(Seat seat) {
    if (seats.containsKey(seat.getNo())) {
      throw new KeyAlreadyExistsException("이미 존재하는 좌석 입니다.");
    }
    this.seats.put(seat.getNo(), seat);
  }

  public int size() {
    return seats.size();
  }

  public Seat findSeatNo(String seatNo) throws NoSuchObjectException {
    if (!seats.containsKey(seatNo)) {
      throw new NoSuchObjectException("존재하지 않는 좌석번호 입니다.");
    }
    return seats.get(seatNo);
  }

  public List<Seat> getAll() {
    return seats.values().stream().toList();
  }
}
