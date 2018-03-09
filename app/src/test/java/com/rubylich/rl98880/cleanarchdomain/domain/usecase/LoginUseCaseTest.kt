package com.rubylich.rl98880.cleanarchdomain.domain.usecase

import com.rubylich.cleanarchdomain.domain.services.AuthenticationService
import com.rubylich.cleanarchdomain.domain.services.UserService
import com.rubylich.cleanarchdomain.domain.services.ValidationService
import com.rubylich.cleanarchdomain.domain.usecase.LoginUseCase
import com.rubylichtenstein.rxtest.assertions.should
import com.rubylichtenstein.rxtest.assertions.shouldEmit
import com.rubylichtenstein.rxtest.extentions.test
import com.rubylichtenstein.rxtest.matchers.complete
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by rl98880 on 11/02/2018.
 */
@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseTest {
    /* Given */
    @Mock
    lateinit var authenticationServiceMock: AuthenticationService

    @Mock
    lateinit var validationServiceMock: ValidationService

    @Mock
    lateinit var userServiceMock: UserService;

    lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        loginUseCase = LoginUseCase(
            authenticationServiceMock,
            validationServiceMock,
            userServiceMock,
            threadExecutor = Schedulers.trampoline(),
            postExecutionThread = Schedulers.trampoline()
        )
    }


    @Test
    fun buildUseCase_email_not_valid() {
        val token = "some_token"
        val email = "ab@an.com"
        val password = "password"

        Mockito.`when`(validationServiceMock.validateEmail(email))
            .thenReturn(Maybe.just(ValidationService.Error.InvalidEmail))

        Mockito.`when`(validationServiceMock.validatePassword(password))
            .thenReturn(Maybe.empty())

        Mockito.`when`(
            authenticationServiceMock.login(
                AuthenticationService.LoginRequest(
                    email,
                    password
                )
            )
        )
            .thenReturn(Single.just(com.rubylich.cleanarchdomain.rxerror.Success(token)))

        Mockito.`when`(userServiceMock.setToken(token))
            .thenReturn(Maybe.empty())

        loginUseCase.execute(LoginUseCase.Param(email, password))
            .test {
                it shouldEmit  LoginUseCase.Error.InvalidEmail
                it should complete()
            }
    }
    fun buildUseCase_name_not_valid() {
        val token = "some_token"
        val email = "ab@an.com"
        val password = "password"

        Mockito.`when`(validationServiceMock.validateEmail(email))
            .thenReturn(Maybe.just(ValidationService.Error.InvalidEmail))

        Mockito.`when`(validationServiceMock.validatePassword(password))
            .thenReturn(Maybe.empty())

        Mockito.`when`(
            authenticationServiceMock.login(
                AuthenticationService.LoginRequest(
                    email,
                    password
                )
            )
        )
            .thenReturn(Single.just(com.rubylich.cleanarchdomain.rxerror.Success(token)))

        Mockito.`when`(userServiceMock.setToken(token))
            .thenReturn(Maybe.empty())

        loginUseCase.execute(LoginUseCase.Param(email, password))
            .test {
                it shouldEmit  LoginUseCase.Error.InvalidEmail
                it should complete()
            }
    }
    fun buildUseCase_happy() {
        val token = "some_token"
        val email = "ab@an.com"
        val password = "password"

        Mockito.`when`(validationServiceMock.validateEmail(email))
            .thenReturn(Maybe.just(ValidationService.Error.InvalidEmail))

        Mockito.`when`(validationServiceMock.validatePassword(password))
            .thenReturn(Maybe.empty())

        Mockito.`when`(
            authenticationServiceMock.login(
                AuthenticationService.LoginRequest(
                    email,
                    password
                )
            )
        )
            .thenReturn(Single.just(com.rubylich.cleanarchdomain.rxerror.Success(token)))

        Mockito.`when`(userServiceMock.setToken(token))
            .thenReturn(Maybe.empty())

        loginUseCase.execute(LoginUseCase.Param(email, password))
            .test {
                it shouldEmit  LoginUseCase.Error.InvalidEmail
                it should complete()
            }
    }

}