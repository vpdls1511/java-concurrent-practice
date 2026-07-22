package domain;

public class Seat {

  private final String no;
  private SeatStatus status;

  public Seat(String no) {
    this.no = no;
    this.status = SeatStatus.AVAILABLE;
  }

  /**
   * 좌석을 생성할 수 있다.
   *
   * @param no
   * @return
   */
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

  public void payment() {
    if (!SeatStatus.PAYMENT_WAIT.equals(this.status)) {
      throw new IllegalStateException("선점한 좌석이 아닙니다.");
    }
    this.status = SeatStatus.RESERVE;
  }
}
