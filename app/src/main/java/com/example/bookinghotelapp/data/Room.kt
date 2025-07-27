package com.example.bookinghotelapp.data

import androidx.annotation.StringRes
import com.example.bookinghotelapp.R

data class Room(
    @StringRes val id: Int,
    @StringRes val loai: Int,
    val gia: Double,
    val tiennghi: List<Int>,
    var phongtrong: Int
)

val tiennghi1 = listOf(
    R.string.amenity1, R.string.amenity2, R.string.amenity3, R.string.amenity4, R.string.amenity5
)
val tiennghi2 = listOf(
    R.string.amenity1, R.string.amenity2, R.string.amenity3
)
val tiennghi3 = listOf(
    R.string.amenity2, R.string.amenity3, R.string.amenity4
)

// Changed to mutableListOf to allow updates
val rooms = mutableListOf(
    Room(R.string.id1, R.string.room1, 20.00, tiennghi1, 8),
    Room(R.string.id2, R.string.room2, 17.00, tiennghi1, 10),
    Room(R.string.id3, R.string.room3, 15.00, tiennghi2, 5),
    Room(R.string.id4, R.string.room4, 10.00, tiennghi3, 11),
    Room(R.string.id5, R.string.room5, 10.00, tiennghi3, 9)
)

fun updateRoomAvailability(roomId: Int, bookedQuantity: Int) {
    val room = rooms.find { it.id == roomId }
    room?.let {
        it.phongtrong -= bookedQuantity
    }
}

data class Booking(
    val room: Room =  Room(0,0,0.0, listOf(),0),
    val quantity: Int = 0,
    val totalPrice: Double = 0.0,
) {
    fun reset(): Booking {
        return Booking()
    }
}
