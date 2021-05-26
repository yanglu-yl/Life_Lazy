package com.example.module_notice.bean

class PushBean {
data class Push(
    val messageList: List<Message>,
    val nextPageUrl: String,
    val updateTime: Long
)

data class Message(
    val actionUrl: String,
    val content: String,
    val date: Long,
    val icon: String,
    val id: Int,
    val ifPush: Boolean,
    val pushStatus: Int,
    val title: String,
    val uid: Any,
    val viewed: Boolean
)
}