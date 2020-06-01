package com.example.jawwla

import androidx.lifecycle.ViewModel
import com.example.jawwla.Activities.Filters


/**
 * ViewModel for [com.google.firebase.example.fireeats.MainActivity].
 */

class MainActivityViewModel : ViewModel() {

    var isSigningIn: Boolean = false
    var filters: Filters = Filters.default
}
