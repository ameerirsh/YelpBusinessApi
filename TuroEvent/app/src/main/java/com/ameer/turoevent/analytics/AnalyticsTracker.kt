package com.ameer.turoevent.analytics

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

class AnalyticsTracker {
    var analyticsQueue: Queue<Events> = LinkedList<Events>()
    fun addEvent(eventName: String, eventMessage: String) {
        var events = Events(eventName, eventMessage)
        analyticsQueue.add(events)
    }

    fun clearAnalyticsQueue() {
        analyticsQueue.clear()
    }

    fun flushAnalyticsQueue() {
        CoroutineScope(Dispatchers.IO).launch {
            //send analytics to backend
        }
    }
}