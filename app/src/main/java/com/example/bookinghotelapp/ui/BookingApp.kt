package com.example.bookinghotelapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookinghotelapp.ui.screens.RoomDetailScreen
import com.example.bookinghotelapp.ui.screens.RoomListScreen
import com.example.bookinghotelapp.ui.screens.SummaryScreen

enum class Screen {
    LIST,
    DETAIL,
    SUMMARY
}

sealed class NavItem(val route: String) {
    object List : NavItem(Screen.LIST.name)
    object Detail : NavItem(Screen.DETAIL.name)
    object Summary : NavItem(Screen.SUMMARY.name)
}

@Composable
fun BookingApp(viewModel: BookingViewModel = viewModel()) {
    val navController = rememberNavController()
    val book by viewModel.rooms.collectAsState()
    var loi by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = NavItem.List.route) {
        composable(NavItem.List.route) {
            RoomListScreen(
                viewModel = viewModel,
                onRoomSelected = {
                    navController.navigate(NavItem.Detail.route)
                }
            )
        }

        composable(NavItem.Detail.route) {
            val room = book.room
            RoomDetailScreen(
                room = room,
                onBookingConfirmed = { quantity ->
                    viewModel.updateBooking(room, quantity)
                    if (viewModel.checkSL()) {
                        viewModel.confirmBooking()
                        navController.navigate(NavItem.Summary.route)
                    } else {
                        loi = true
                    }
                },
                onCancel = {
                    navController.popBackStack()
                },
            )
        }

        composable(NavItem.Summary.route) {
            SummaryScreen(
                booking = book,
                onCancel = {
                    navController.popBackStack(NavItem.List.route, inclusive = false)
                }
            )
        }
    }
}