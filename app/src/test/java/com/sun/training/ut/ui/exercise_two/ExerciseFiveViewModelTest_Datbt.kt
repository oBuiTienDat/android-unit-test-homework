package com.sun.training.ut.ui.exercise_two

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.ui.exercise_five.ExerciseFiveViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.jvm.Throws

@RunWith(MockitoJUnitRunner::class)
class ExerciseFiveViewModelTest_Datbt {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseFiveViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseFiveViewModel()
    }

    @Test
    fun calculateFee_1() {
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(false)
        viewModel.totalPrice = 1499
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.REGULAR_FEE.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun calculateFee_2() {
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(false)
        viewModel.totalPrice = 1501
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.REGULAR_FEE.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun calculateFee_3() {
        viewModel.onChangedDelivery(false)
        viewModel.onChangedVoucher(false)
        viewModel.totalPrice = 1499
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.PIZZA_SECOND_FREE.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun calculateFee_4() {
        viewModel.onChangedDelivery(false)
        viewModel.onChangedVoucher(false)
        viewModel.totalPrice = 1501
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.PIZZA_SECOND_FREE.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun calculateFee_5() {
        viewModel.onChangedDelivery(false)
        viewModel.onChangedVoucher(true)
        viewModel.totalPrice = 1499
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.PIZZA_SECOND_FREE.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun calculateFee_6() {
        viewModel.onChangedDelivery(false)
        viewModel.onChangedVoucher(true)
        viewModel.totalPrice = 1501
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.POTATO_PROMOTION.coupon +  Constant.Coupon.PIZZA_SECOND_FREE.coupon, viewModel.discountLiveData.value)
    }


    @Test
    fun calculateFee_7() {
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(true)
        viewModel.totalPrice = 1501
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.OFF_20.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun calculateFee_8() {
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(true)
        viewModel.totalPrice = 1499
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.OFF_20.coupon, viewModel.discountLiveData.value)
    }
}