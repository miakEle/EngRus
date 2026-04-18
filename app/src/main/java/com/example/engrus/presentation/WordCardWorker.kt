package com.example.engrus.presentation

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.engrus.WordCardApplication
import com.example.engrus.domain.repository.WordCardRepository
import com.example.engrus.domain.review.ReviewDelays
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

class WordCardWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    init {
        (context.applicationContext as WordCardApplication).appComponent.inject(this)
    }

    @Inject
    lateinit var wordCardRepository: WordCardRepository


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {

        val cardId = inputData.getLong(KEY_CARD_ID, -1L)
        if (cardId == -1L) return Result.failure()

        val card = wordCardRepository.getWordCardById(cardId) ?: return Result.failure()

        val now = LocalDateTime.now()
        if (now.isBefore(card.nextReviewTime)) return Result.success()

        showNotification(card.word, card.translation)

        val nextAttempts = card.attempts + 1

        if (nextAttempts >= ReviewDelays.delays.size) {
            wordCardRepository.editWordCard(card.copy(attempts = nextAttempts))
            return Result.success()
        }

        val delay = ReviewDelays.delays[nextAttempts]

        val nextTime = now.plusNanos(delay * 1000000)

        wordCardRepository.editWordCard(
            card.copy(
                attempts = nextAttempts,
                nextReviewTime = nextTime
            )
        )

        scheduleNext(applicationContext, cardId, delay)

        return Result.success()
    }

    companion object {
        private const val KEY_CARD_ID = "cardId"

        fun scheduleFirst(context: Context, cardId: Long) {
            val delay = ReviewDelays.delays[0]

            val data = Data.Builder()
                .putLong(KEY_CARD_ID, cardId)
                .build()

            val request = OneTimeWorkRequestBuilder<WordCardWorker>()
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .setInputData(data)
                .build()

            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    "review_$cardId",
                    ExistingWorkPolicy.REPLACE,
                    request
                )
        }

        fun scheduleNext(context: Context, cardId: Long, delayMs: Long) {
            val data = Data.Builder()
                .putLong(KEY_CARD_ID, cardId)
                .build()

            val request = OneTimeWorkRequestBuilder<WordCardWorker>()
                .setInitialDelay(delayMs, TimeUnit.MILLISECONDS)
                .setInputData(data)
                .build()

            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    "review_$cardId",
                    ExistingWorkPolicy.REPLACE,
                    request
                )
        }
    }


    @SuppressLint("ServiceCast")
    private fun showNotification(title: String, message: String) {
        val channelId = "word_card_channel"
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Words",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle())
            .build()

        manager.notify(Random.nextInt(), notification)
    }
}
