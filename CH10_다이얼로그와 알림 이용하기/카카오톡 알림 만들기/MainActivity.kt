package com.example.ch10_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.ch10_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.notificationButton.setOnClickListener{

            // 알림 빌더 작성
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )

                channel.description = "My Channel One Description"
                channel.setShowBadge(true)
                var uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()

                channel.setSound(uri, audioAttributes)
                channel.enableLights(true)
                channel.lightColor = Color.RED
                channel.enableVibration(true)
                channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

                manager.createNotificationChannel(channel)

                builder = NotificationCompat.Builder(this, channelId)
            } else {
                builder = NotificationCompat.Builder(this)
            }

            // 빌더에 알림 객체 설정하기
            builder.run{
                setSmallIcon(R.drawable.small)
                setWhen(System.currentTimeMillis())
                setContentTitle("홍길동")
                setContentText("안녕하세요")
                setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.big))
            }

            // 원격 입력 알림 띄우기
            val KEY_TEXT_REPLY = "key_text_reply"
            var replyLabel: String = "답장"
            var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }

            // pedingIntent 준비하기
            val replyIntent = Intent(this, ReplyReceiver::class.java)
            val replyPendingIntent = PendingIntent.getBroadcast(this, 30, replyIntent,
                PendingIntent.FLAG_MUTABLE)

            // 알림에 원격 입력 액션을 등록하기
            builder.addAction(
                NotificationCompat.Action.Builder(
                    R.drawable.send,
                    "답장",
                    replyPendingIntent
                ).addRemoteInput(remoteInput).build()
            )

            // 알림 매니저에 builder 객체의 실행을 알리기
            manager.notify(11, builder.build())
        }
    }
}