package com.inspirecoding.wayairlines.data.remote.services.wrapper

import com.inspirecoding.wayairlines.data.remote.services.responseapi.ResponseApi
import com.inspirecoding.wayairlines.data.util.exception.WayAirlinesException
import com.inspirecoding.wayairlines.extensions.string.containsTagHtml
import com.inspirecoding.wayairlines.extensions.string.toDefaultError
import retrofit2.Response
import java.io.IOException
import javax.net.ssl.HttpsURLConnection

class RequestWrapperImpl: RequestWrapper {

    override suspend fun <T : Any> wrapper(call: suspend () -> Response<T>): ResponseApi<T> {
        return try {
            val response = call.invoke()
            val errorBody = response.errorBody()?.string().orEmpty()

            when {
                response.isSuccessful -> {

                    ResponseApi.Success(
                        data = response.body()
                    )
                }

                response.code() == HttpsURLConnection.HTTP_FORBIDDEN && errorBody.containsTagHtml() -> {
                    ResponseApi.ErrorException(
                        WayAirlinesException.DefaultError(
                            errorBody,
                            "${response.code()}"
                        )
                    )
                }

                else -> {
                    val error = errorBody.toDefaultError()
                    ResponseApi.ErrorException(
                        WayAirlinesException.DefaultError(
                            message = error?.message.orEmpty(),
                            code = error?.code.orEmpty()
                        )
                    )
                }
            }
        } catch (exception: IOException) {
            ResponseApi.ErrorException(WayAirlinesException.ErrorNetworkException)
        } catch (exception: WayAirlinesException) {
            ResponseApi.ErrorException(exception)
        }
    }
}