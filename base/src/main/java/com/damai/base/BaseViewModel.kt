package com.damai.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * Created by damai007 on 18/July/2023
 */
open class BaseViewModel(
    app: Application
) : AndroidViewModel(application = app)