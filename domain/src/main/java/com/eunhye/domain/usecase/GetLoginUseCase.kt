package com.eunhye.domain.usecase

import com.eunhye.domain.repository.LoginRepository
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(private val repository: LoginRepository) {
    fun execute(): Boolean = repository.autoLogin
}