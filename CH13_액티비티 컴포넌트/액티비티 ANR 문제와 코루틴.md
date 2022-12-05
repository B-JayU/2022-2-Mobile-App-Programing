1. [액티비티 ANR 문제와 코루틴](https://www.notion.so/Study-13-3-02da3dc5fdec40358a250fc1da0d542a)
    1. [ANR 문제란?](https://www.notion.so/Study-13-3-02da3dc5fdec40358a250fc1da0d542a)
    2. [코루틴으로 ANR 오류 해결](https://www.notion.so/Study-13-3-02da3dc5fdec40358a250fc1da0d542a)

### 액티비티 ANR 문제와 코루틴

**ANR 문제란**

- ANR(Activity Not Response)은 액티비티가 응답하지 않는 오류 상황
- 5초 이내 반응하지 않으면 ANR오류가 발생
- 시스템에서 액티비티를 실행하는 수행 흐름을 메인 스레드 또는 화면을 출력하는 수행 흐름이라는 의미에서 UI 스레드라고 함

- ANR 문제를 해결하는 방법
    - 액티비티를 실행한 메인 스레드 이외에 실행 흐름을 따로 들어서 시간이 오래 걸리는 작업을 담당하게 하면 됨
    - 이 방법의 경우, ANR오류는 해결되지만 화면을 변경할 수 없다는 또 다른 문제 야기

**코루틴으로 ANR 오류 해결**

- 코루틴이란?
    - 비동기 경량 스레드
    - 장점
        - 경량
        - 메모리 누수가 적음
        - 취소 등 다양한 기능을 제공
        - 많은 제트팩 라이브러리에 적용되어 있다
- 코루틴 사용 방법
    
    ```kotlin
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9'
    ```
    
- 코루틴이 어느 부분에서 동작하느냐에 따라, 다른 디스패처를 사용
- 디스패처는 범위에서 구동한 코루틴이 어디에서 동작해야 하는지 나타낸다
    - Dispatchers.Main : 메인 스레드에서 동작하는 코루틴을 만든다
    - [Dispatchers.IO](http://Dispatchers.IO) : 파일에 읽거나 쓰기 또는 네트워크 작업 등에 최적화
    - Dispatchers.Default : CPU를 많이 사용하는 작업을 백그라운드에서 실행
