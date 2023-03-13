package com.dev.bayan_ibrahim.compose_course_2.ui.view_model

import androidx.lifecycle.ViewModel
import com.dev.bayan_ibrahim.compose_course_2.domain.MenuItem
import com.dev.bayan_ibrahim.compose_course_2.domain.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel: ViewModel() {

    private val _orderUiState = MutableStateFlow<OrderUiState>(OrderUiState())
    val orderUiState: StateFlow<OrderUiState> = _orderUiState

    private var entreePrice: Double = 0.0
    private var sideDishPrice: Double = 0.0
    private var accompanimentPrice: Double = 0.0


    fun setEntree(entree: MenuItem.EntreeItem) {
        entreePrice = entree.price
        _orderUiState.update {
            it.copy(
                entree = entree,
                itemTotalPrice = calculateTotalPrice()
            )
        }
    }
    fun setSideDish(sideDish: MenuItem.SideDishItem) {
        sideDishPrice = sideDish.price
        _orderUiState.update {
            it.copy(
                sideDish = sideDish,
                itemTotalPrice = calculateTotalPrice()
            )
        }
    }
    fun setAccompaniment(accompaniment: MenuItem.AccompanimentItem) {
        accompanimentPrice = accompaniment.price
        _orderUiState.update {
            it.copy(
                accompaniment = accompaniment,
                itemTotalPrice = calculateTotalPrice()
            )
        }
    }
    fun resetOrder(): Unit {
        _orderUiState.value = OrderUiState()
    }
    fun submitOrder() {
        // do nothing now
        // TODO do something
        resetOrder()
    }
    private fun calculateTotalPrice(): Double {
        return  entreePrice + sideDishPrice + accompanimentPrice
    }
}
