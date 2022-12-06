package com.example.study08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import com.example.study08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // button view 객체에 이벤트 핸들러
    var initTime = 0L // 뒤로 가기 버튼을 누른 시각을 저장하는 속성
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SystemClock.elapsedRealtime() : https://codechacha.com/ko/android-currentTimeMillis-vs-elapsedRealtime/
        binding.btnStart.setOnClickListener {

           // 시작 버튼을 눌렀을 때는, 기존에 타이머에 시간이 표시되어 있었다면, pauseTime을 더한 시간을 base에서 보여주어야 함
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()

            binding.btnStart.isEnabled = false
            binding.btnReset.isEnabled = true
            binding.btnStop.isEnabled = true
        }

        binding.btnStop.setOnClickListener {
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.btnStop.isEnabled = false
            binding.btnReset.isEnabled = true
            binding.btnStart.isEnabled = true
        }

        binding.btnReset.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.btnReset.isEnabled = false
            binding.btnStart.isEnabled = true
            binding.btnStop.isEnabled = true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - initTime > 3000) {
                Toast.makeText(this, "종료하려면 한 번 더 누르세요!!", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}