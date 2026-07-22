package domain;

public enum SeatStatus {
  AVAILABLE, // 구매 가능
  RESERVED,  // 선점됨(결제 대기)
  SOLD       // 판매 완료
}
