package domain;

public class Seat {

  private final String no;
  private SeatStatus status;

  private Seat(String no) {
    if (no == null || no.isBlank()) {
      throw new IllegalArgumentException("좌석 번호는 비어있을 수 없습니다.");
    }
    this.no = no;
    this.status = SeatStatus.AVAILABLE;
  }

  public static Seat create(String no) {
    return new Seat(no);
  }

  public String getNo() {
    return this.no;
  }

  public SeatStatus getStatus() {
    return this.status;
  }

  public void reserve() {
    if (!SeatStatus.AVAILABLE.equals(this.status)) {
      throw new IllegalStateException("이미 선점된 좌석 입니다.");
    }
    this.status = SeatStatus.PAYMENT_WAIT;
  }

  public void purchase() {
    if (!SeatStatus.PAYMENT_WAIT.equals(this.status)) {
      throw new IllegalStateException("선점한 좌석이 아닙니다.");
    }
    this.status = SeatStatus.RESERVE;
  }
}
