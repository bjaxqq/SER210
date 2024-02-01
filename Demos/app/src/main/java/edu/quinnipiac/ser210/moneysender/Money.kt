package edu.quinnipiac.ser210.moneysender

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
class Money( val amount: BigDecimal) : Parcelable {
}