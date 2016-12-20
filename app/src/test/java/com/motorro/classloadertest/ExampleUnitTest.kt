package com.motorro.classloadertest

import android.os.Build
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {
    companion object {
        private val PHONE: Phonenumber.PhoneNumber

        init {
            PHONE = Phonenumber.PhoneNumber()
            PHONE.countryCode = 7
            PHONE.nationalNumber = 4956360636
        }
    }


    @Test
    @Config(
            constants = BuildConfig::class,
            sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP_MR1)
    )
    fun withLollipop() {
        PhoneNumberUtil.getInstance().format(PHONE, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
    }

    @Test
    @Config(
            constants = BuildConfig::class,
            sdk = intArrayOf(Build.VERSION_CODES.M)
    )
    fun withM() {
        PhoneNumberUtil.getInstance().format(PHONE, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
    }
}