package com.example.bookinghotelapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.bookinghotelapp.R
import com.example.bookinghotelapp.data.Booking
import com.example.bookinghotelapp.data.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.bookinghotelapp.data.rooms
import com.example.bookinghotelapp.data.updateRoomAvailability


class BookingViewModel : ViewModel() {
    private var _rooms = MutableStateFlow(Booking())
    val rooms: StateFlow<Booking> = _rooms.asStateFlow()

    fun updateRoom(room: Room) {
        _rooms.value = _rooms.value.copy(
            room = room
        )
    }

    fun updateBooking(room: Room, quantity: Int) {
        _rooms.value = _rooms.value.copy(
            room = room,
            quantity = quantity
        )
    }

    fun resetBooking() {
        _rooms.value = _rooms.value.reset()
    }

    fun tinhTen() {
        val roomPrice = _rooms.value.room.gia
        val quantity = _rooms.value.quantity
        _rooms.value = _rooms.value.copy(
            totalPrice = quantity * roomPrice
        )
    }

    fun checkSL(): Boolean {
        val quantity = _rooms.value.quantity
        val phongtrong = _rooms.value.room.phongtrong
        return phongtrong >= quantity
    }

    fun confirmBooking() {
        if (checkSL()) {
            updateRoomAvailability(_rooms.value.room.id, _rooms.value.quantity)
            tinhTen()
        }
    }
}