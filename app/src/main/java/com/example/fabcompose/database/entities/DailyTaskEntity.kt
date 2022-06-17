package com.example.fabcompose.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dailyTasks")
data class DailyTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "task_date")
    val taskDate: String,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean,
    @ColumnInfo(name = "comments")
    val comments: String?
)