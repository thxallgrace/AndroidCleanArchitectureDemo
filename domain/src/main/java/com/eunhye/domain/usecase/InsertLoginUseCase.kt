package com.eunhye.domain.usecase

import com.eunhye.domain.repository.LoginRepository
import javax.inject.Inject

class InsertLoginUseCase @Inject constructor(private val repository: LoginRepository) {
    fun execute(success: Boolean) {
        repository.autoLogin = success
    }
}