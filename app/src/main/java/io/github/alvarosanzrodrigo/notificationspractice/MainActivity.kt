package io.github.alvarosanzrodrigo.notificationspractice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.view.View
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {

    var notificationId: Int = 0
    lateinit var mBuilder: NotificationCompat.Builder
    lateinit var submitButton: LottieAnimationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitButton = findViewById(R.id.submit_button)
        createNotificationChannel()

        mBuilder = NotificationCompat.Builder(this, "io.github.alvarosanzrodrigo")
            .setSmallIcon(R.drawable.notification_template_icon_low_bg)
            .setContentTitle("Holi")
            .setContentText("Que tal")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "io.github.alvarosanzrodrigo"
            val descriptionText = "io.github.alvarosanzrodrigo"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("io.github.alvarosanzrodrigo", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun notify(view: View){
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            submitButton.playAnimation()
            notify(1, mBuilder.build())
        }
    }
}
