package com.example.customnotif

import android.app.*
import android.content.Intent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_FOO = "com.example.customnotif.action.FOO"
private const val ACTION_BAZ = "com.example.customnotif.action.BAZ"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "com.example.customnotif.extra.PARAM1"
private const val EXTRA_PARAM2 = "com.example.customnotif.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class NotificationService : IntentService("NotificationService") {

    companion object {
        private const val KEY_REPLY = "key_reply_message"
        const val REPLY_ACTION = "com.dicoding.notification.directreply.REPLY_ACTION"
        const val CHANNEL_ID = "channel_01"
        val CHANNEL_NAME: CharSequence = "dicoding channel"

        fun getReplyMessage(intent: Intent): CharSequence? {
            val remoteInput = RemoteInput.getResultsFromIntent(intent)
            return remoteInput?.getCharSequence(KEY_REPLY)
        }
    }

    private var mNotificationId: Int = 0
    private var mMessageId: Int = 0

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            showNotification()
        }
    }

    private fun showNotification() {
        mNotificationId = 1
        mMessageId = 123

        // Tambahkan channel id, channel name , dan tingkat importance

        val replyLabel = getString(R.string.notif_action_reply)
        val remoteInput = RemoteInput.Builder(KEY_REPLY)
            .setLabel(replyLabel)
            .build()

        val replyAction = NotificationCompat.Action.Builder(
            R.drawable.ic_reply_black_24px, replyLabel, getReplyPendingIntent())
            .addRemoteInput(remoteInput)
            .setAllowGeneratedReplies(true)
            .build()

        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_white_48px)
            .setContentTitle(getString(R.string.notif_title))
            .setContentText(getString(R.string.notif_content))
            .setShowWhen(true)
            .addAction(replyAction)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            val channel = NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)

            mBuilder.setChannelId(CHANNEL_ID)

            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = mBuilder.build()

        mNotificationManager.notify(mNotificationId, notification)
    }

    private fun getReplyPendingIntent(): PendingIntent {
        val intent: Intent
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = NotificationBroadcastReceiver.getReplyMessageIntent(this, mNotificationId, mMessageId)
            PendingIntent.getBroadcast(applicationContext, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        } else {
            intent = ReplyActivity.getReplyMessageIntent(this, mNotificationId, mMessageId)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }
}
