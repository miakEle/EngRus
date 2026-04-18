package com.example.engrus.domain.review

import com.example.engrus.common.days
import com.example.engrus.common.hours
import com.example.engrus.common.minutes

object ReviewDelays {
    val delays = listOf(
        20.minutes,   // 0 from min to ms
        8.hours,      // 1 from hour to ms
        24.hours,     // 2
        3.days,       // 3 from day to ms
        7.days,       // 4
        30.days       // 5
    )
}