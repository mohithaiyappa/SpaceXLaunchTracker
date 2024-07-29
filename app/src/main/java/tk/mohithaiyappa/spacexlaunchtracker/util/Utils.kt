package tk.mohithaiyappa.spacexlaunchtracker.util

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun TextInputEditText.textChanges(): Flow<CharSequence?> =
    callbackFlow {
        val listener =
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int,
                ) {
                    trySend(s)
                }

                override fun afterTextChanged(s: Editable?) {}
            }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }

fun <T> List<T>.concatenateStrings(
    getString: (T) -> String,
    separator: String = "",
): String {
    return joinToString(separator = separator) { getString(it) }
}
